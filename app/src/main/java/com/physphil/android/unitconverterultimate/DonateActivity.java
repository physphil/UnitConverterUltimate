package com.physphil.android.unitconverterultimate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.iab.IabHelper;
import com.physphil.android.unitconverterultimate.iab.IabResult;
import com.physphil.android.unitconverterultimate.iab.Inventory;
import com.physphil.android.unitconverterultimate.iab.Purchase;
import com.physphil.android.unitconverterultimate.ui.DonationListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DonateActivity extends ActionBarActivity {

    private static final int DONATE_REQUEST_CODE = 6996;

    private List<String> mDonationOptions;
    private IabHelper mHelper;
    private Inventory mInventory;
    private ListView mListview;
    private ProgressBar mProgressBar;
    private String mPurchasePayload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_donate);

        mListview = (ListView) findViewById(R.id.billing_listview);
        mProgressBar = (ProgressBar) findViewById(R.id.billing_progress_spinner);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupBilling();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Shut down IAB
        if(mHelper != null){
            mHelper.dispose();
        }
        mHelper = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("PS", "onActivityResult(" + requestCode + "," + resultCode + "," + data);
        if(!mHelper.handleActivityResult(requestCode, resultCode, data)) {

            // Not related to in-app billing, handle normally
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setupBilling(){

        mDonationOptions = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.donation_options)));

        // Setup google play billing
        StringBuilder sb = new StringBuilder().append(getString(R.string.license_key_p1))
                .append(getString(R.string.license_key_p2))
                .append(getString(R.string.license_key_p3));

        mHelper = new IabHelper(this, sb.toString());
        mHelper.enableDebugLogging(true, "PS");
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {

                if(result.isSuccess()){
                    // Try to get available inventory
                    mHelper.queryInventoryAsync(true, mDonationOptions, new IabHelper.QueryInventoryFinishedListener() {
                        @Override
                        public void onQueryInventoryFinished(IabResult result, Inventory inv) {

                            if(result.isSuccess()){
                                mInventory = inv;
                                consumeExistingPurchases();
                                displayDonationOptions();
                            }
                            else{
                                shutdown(false);
                            }
                        }
                    });
                }
                else{
                    shutdown(false);
                }
            }
        });
    }

    /**
     * Display donation options to user
     */
    private void displayDonationOptions(){
        mProgressBar.setVisibility(View.GONE);
        mListview.setAdapter(new DonationListAdapter(this, mInventory));
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("PS", "spending " + mInventory.getSkuDetails(mDonationOptions.get(position)).getPrice());
                donate(mInventory.getSkuDetails(mDonationOptions.get(position)).getSku());
            }
        });
    }

    /**
     * Consume any existing purchases
     */
    private void consumeExistingPurchases(){

        List<Purchase> purchases = new ArrayList<Purchase>();

        // Check each sku, consume if owned
        for(String sku : mDonationOptions){
            Purchase p = mInventory.getPurchase(sku);
            if(p != null){
                purchases.add(p);
            }
        }

        mHelper.consumeAsync(purchases, new IabHelper.OnConsumeMultiFinishedListener() {
            @Override
            public void onConsumeMultiFinished(List<Purchase> purchases, List<IabResult> results) {
                for(int i = 0; i < results.size(); i++){

                    if(results.get(i).isSuccess()){
                        Log.d("PS", "successfully consumed " + purchases.get(i).getSku());
                    }
                    else{
                        Log.d("PS", "error consuming " + purchases.get(i).getSku());
                    }
                }
            }
        });
    }

    /**
     * Start Google Play billing flow, with specified product id (sku)
     * @param productId product id of donation
     */
    private void donate(String productId){

        mPurchasePayload = UUID.randomUUID().toString();
        mHelper.launchPurchaseFlow(this, productId, DONATE_REQUEST_CODE, mPurchaseFinishedListener, mPurchasePayload);
    }

    /**
     * Shutdown activity and display user confirmation
     * @param success if the donation was successful
     */
    private void shutdown(boolean success){

        if(success){
            Toast.makeText(this, R.string.toast_donation_successful, Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, R.string.toast_error_billing, Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    /*
    Listener called when purchase has completed
     */
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, Purchase info) {
            Log.d("PS", "in onIabPurchaseFinished");
            if(result.isFailure()){
                shutdown(false);
            }
            else{
                Log.d("PS", "purchase successful!");
                // Consume the purchase and thank user
                if(info.getDeveloperPayload().equals(mPurchasePayload)){
                    Log.d("PS", "payloads match!");
                }
                shutdown(true);
            }
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
