/*
 * Copyright 2016 Phil Shadlyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physphil.android.unitconverterultimate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.iab.IabHelper;
import com.physphil.android.unitconverterultimate.iab.IabResult;
import com.physphil.android.unitconverterultimate.iab.Inventory;
import com.physphil.android.unitconverterultimate.iab.Purchase;
import com.physphil.android.unitconverterultimate.ui.DonateListAdapter;
import com.physphil.android.unitconverterultimate.ui.RecyclerViewItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DonateActivity extends BaseActivity implements RecyclerViewItemClickListener {

    private static final int DONATE_REQUEST_CODE = 6996;

    private List<String> mDonationOptions;
    private IabHelper mHelper;
    private Inventory mInventory;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private String mPurchasePayload;

    public static void start(Context context) {
        context.startActivity(new Intent(context, DonateActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        mProgressBar = (ProgressBar) findViewById(R.id.billing_progress_spinner);
        mRecyclerView = (RecyclerView) findViewById(R.id.billing_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        setupToolbar();
        setToolbarHomeNavigation(true);
        setupBilling();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Shut down IAB
        if (mHelper != null) {
            mHelper.dispose();
        }
        mHelper = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!mHelper.handleActivityResult(requestCode, resultCode, data)) {
            // Not related to in-app billing, handle normally
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setupBilling() {
        mDonationOptions = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.donation_options)));

        // Setup google play billing
        StringBuilder sb = new StringBuilder().append(getString(R.string.license_key_p1))
                .append(getString(R.string.license_key_p2))
                .append(getString(R.string.license_key_p3));

        mHelper = new IabHelper(this, sb.toString());
        try {
            mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                @Override
                public void onIabSetupFinished(IabResult result) {
                    if (result.isSuccess()) {
                        // Try to get available inventory
                        mHelper.queryInventoryAsync(true, mDonationOptions, new IabHelper.QueryInventoryFinishedListener() {
                            @Override
                            public void onQueryInventoryFinished(IabResult result, Inventory inv) {
                                if (result.isSuccess()) {
                                    mInventory = inv;
                                    consumeExistingPurchases();
                                    displayDonationOptions();
                                }
                                else {
                                    shutdown(false);
                                }
                            }
                        });
                    }
                    else {
                        shutdown(false);
                    }
                }
            });
        }
        catch (Exception ex) {
            Toast.makeText(this, R.string.toast_error_billing_general, Toast.LENGTH_SHORT).show();
            mHelper = null;
            finish();
        }
    }

    /**
     * Display donation options to user
     */
    private void displayDonationOptions() {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setAdapter(new DonateListAdapter(mInventory, getResources().getStringArray(R.array.donation_options), this));
    }

    /**
     * Consume any existing purchases
     */
    private void consumeExistingPurchases() {
        List<Purchase> purchases = new ArrayList<Purchase>();

        // Check each sku, consume if owned
        for (String sku : mDonationOptions) {
            Purchase p = mInventory.getPurchase(sku);
            if (p != null) {
                purchases.add(p);
            }
        }

        mHelper.consumeAsync(purchases, new IabHelper.OnConsumeMultiFinishedListener() {
            @Override
            public void onConsumeMultiFinished(List<Purchase> purchases, List<IabResult> results) {}
        });
    }

    /**
     * Start Google Play billing flow, with specified product id (sku)
     *
     * @param productId product id of donation
     */
    private void donate(String productId) {
        mPurchasePayload = UUID.randomUUID().toString();
        mHelper.launchPurchaseFlow(this, productId, DONATE_REQUEST_CODE, mPurchaseFinishedListener, mPurchasePayload);
    }

    /**
     * Shutdown activity and display user confirmation
     *
     * @param success if the donation was successful
     */
    private void shutdown(boolean success) {
        if (success) {
            Toast.makeText(this, R.string.toast_donation_successful, Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, R.string.toast_error_billing_internet, Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    // Listener called when purchase has completed
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, Purchase info) {

            if (result.isFailure()) {
                switch (result.getResponse()) {
                    case IabHelper.IABHELPER_USER_CANCELLED:
                        break;

                    default:
                        shutdown(false);
                        break;
                }
            }
            else {
                // Consume purchase so it can be done again and thank user
                mHelper.consumeAsync(info, mConsumeFinishedListener);
            }
        }
    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener = new IabHelper.OnConsumeFinishedListener() {
        @Override
        public void onConsumeFinished(Purchase purchase, IabResult result) {
            // Purchase is successful, thank user and shutdown activity
            shutdown(true);
        }
    };

    // RecyclerView item click listener
    @Override
    public void onListItemClicked(Object sku, int position) {
        donate((String) sku);
    }
}
