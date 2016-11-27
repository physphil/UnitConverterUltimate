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

package com.physphil.android.unitconverterultimate.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.iab.Inventory;
import com.physphil.android.unitconverterultimate.iab.SkuDetails;

/**
 * Adapter to hold list of donation options
 * Created by Phizz on 15-08-09.
 */
public final class DonateListAdapter extends RecyclerView.Adapter<DonateListAdapter.ViewHolder> {

    private Inventory mInventory;
    private String[] mDonationOptions;
    private RecyclerViewItemClickListener mListener;

    public final class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView description;
        public TextView price;

        public ViewHolder(View v) {
            super(v);
            description = (TextView) v.findViewById(R.id.billing_donation_description);
            price = (TextView) v.findViewById(R.id.billing_donation_price);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onListItemClicked(mDonationOptions[getAdapterPosition()], getAdapterPosition());
            }
        }
    }

    public DonateListAdapter(Inventory inventory, String[] donationOptions, RecyclerViewItemClickListener listener) {
        this.mInventory = inventory;
        this.mDonationOptions = donationOptions;
        this.mListener = listener;
    }

    @Override
    public DonateListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_donation, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DonateListAdapter.ViewHolder vh, int position) {
        SkuDetails details = mInventory.getSkuDetails(mDonationOptions[position]);
        vh.description.setText(details.getDescription());
        vh.price.setText(details.getPrice());
    }

    @Override
    public int getItemCount() {
        return mDonationOptions.length;
    }
}
