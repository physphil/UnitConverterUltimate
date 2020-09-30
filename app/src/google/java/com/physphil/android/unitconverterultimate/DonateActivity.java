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

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.billingclient.api.SkuDetails;
import com.physphil.android.unitconverterultimate.ui.DonateListAdapter;
import com.physphil.android.unitconverterultimate.ui.RecyclerViewItemClickListener;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DonateActivity extends BaseActivity implements RecyclerViewItemClickListener<SkuDetails> {

    private BillingManager billingManager;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

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

        if (billingManager != null) {
            billingManager.disconnect();
        }
    }

    private void setupBilling() {
        billingManager = new BillingManager();
        billingManager.connect(this, new BillingManager.ConnectionStateListener() {
            @Override
            public void onConnected() {
                billingManager.queryDonationOptions(new BillingManager.QueryDonationsListener() {
                    @Override
                    public void onComplete(@NotNull QueryDonationsResult result) {
                        if (result instanceof QueryDonationsResult.Success) {
                            displayDonationOptions(((QueryDonationsResult.Success) result).getDonations());
                        } else {
                            shutdown(false);
                        }
                    }
                });
            }

            @Override
            public void onConnectionError(@NotNull String message) {
                shutdown(false);
            }

            @Override
            public void onDisconnected() {
                shutdown(false);
            }
        });
    }

    /**
     * Display donation options to user
     */
    private void displayDonationOptions(List<SkuDetails> donations) {
        mProgressBar.setVisibility(View.GONE);
        DonateListAdapter adapter = new DonateListAdapter(donations, this);
        mRecyclerView.setAdapter(adapter);
    }

    private void donate(SkuDetails donation) {
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        billingManager.donate(this, donation, new BillingManager.DonationResultListener() {
            @Override
            public void onDonationSuccess() {
                shutdown(true);
            }

            @Override
            public void onDonationFailed(@Nullable String message) {
                shutdown(false);
            }

            @Override
            public void onUserCanceled() {
                finish();
            }
        });
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

    // RecyclerView item click listener
    @Override
    public void onListItemClicked(SkuDetails donation, int position) {
        donate(donation);
    }
}
