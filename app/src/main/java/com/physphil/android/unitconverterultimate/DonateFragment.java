package com.physphil.android.unitconverterultimate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.iab.IabHelper;
import com.physphil.android.unitconverterultimate.iab.IabResult;
import com.physphil.android.unitconverterultimate.iab.Inventory;
import com.physphil.android.unitconverterultimate.ui.DonationListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DonateFragment extends Fragment {

    private List<String> mDonationOptions;
    private IabHelper mHelper;
    private Inventory mInventory;
    private ListView mListview;
    private ProgressBar mProgressBar;

    public DonateFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_donate, container, false);

        mListview = (ListView) v.findViewById(R.id.billing_listview);
        mProgressBar = (ProgressBar) v.findViewById(R.id.billing_progress_spinner);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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

    private void setupBilling(){

        mDonationOptions = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.donation_options)));

        // Setup google play billing
        StringBuilder sb = new StringBuilder().append(getString(R.string.license_key_p1))
                .append(getString(R.string.license_key_p2))
                .append(getString(R.string.license_key_p3));

        mHelper = new IabHelper(getActivity(), sb.toString());
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
                                displayDonationOptions();
                            }
                            else{
                                shutdown();
                            }
                        }
                    });
                }
                else{
                    shutdown();
                }
            }
        });
    }

    /**
     * Display donation options to user
     */
    private void displayDonationOptions(){
        mProgressBar.setVisibility(View.GONE);
        mListview.setAdapter(new DonationListAdapter(getActivity(), mInventory));
    }

    /**
     * Shutdown activity due to error in setting up in-app billing
      */
    private void shutdown(){
        Toast.makeText(getActivity(), R.string.toast_error_billing, Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }
}
