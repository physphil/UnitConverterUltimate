package com.physphil.android.unitconverterultimate.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.iab.Inventory;
import com.physphil.android.unitconverterultimate.iab.SkuDetails;

/**
 * Created by pshadlyn on 7/17/2014.
 */
public class DonationListAdapter extends BaseAdapter {

    private Inventory mInventory;
    private String[] mDonationOptions;
    private Context mContext;

    private static class ViewHolder{
        TextView description;
        TextView price;
    }

    public DonationListAdapter(Context context, Inventory inventory){
        mContext = context;
        mInventory = inventory;
        mDonationOptions = mContext.getResources().getStringArray(R.array.donation_options);
    }

    @Override
    public int getCount() {
        return mDonationOptions.length;
    }

    @Override
    public Object getItem(int position) {
        return mInventory.getSkuDetails(mDonationOptions[position]);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_donation, parent, false);

            ViewHolder vh = new ViewHolder();
            vh.description = (TextView) convertView.findViewById(R.id.billing_donation_description);
            vh.price = (TextView) convertView.findViewById(R.id.billing_donation_price);
            convertView.setTag(vh);
        }

        SkuDetails details = mInventory.getSkuDetails(mDonationOptions[position]);
        ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.description.setText(details.getDescription());
        vh.price.setText(details.getPrice());

        return convertView;
    }
}
