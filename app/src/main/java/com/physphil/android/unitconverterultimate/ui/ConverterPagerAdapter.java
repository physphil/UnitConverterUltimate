package com.physphil.android.unitconverterultimate.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.util.Constants;
import com.physphil.android.unitconverterultimate.util.Conversions;

public class ConverterPagerAdapter extends FragmentPagerAdapter{
	
	private final int PAGE_COUNT = 12;
	private Context mContext;
	private String[] mConversions;

	public ConverterPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		mContext = context;
		mConversions = mContext.getResources().getStringArray(R.array.conversions);
	}
	
	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

	@Override
	public Fragment getItem(int position) {
		Bundle data = new Bundle();

		//Get info required to create appropriate fragment
		String[] fragmentNames = mContext.getResources().getStringArray(R.array.fragmentNames);
		String[] fromButtonTags = mContext.getResources().getStringArray(R.array.fromButtonTags);
		String[] toButtonTags = mContext.getResources().getStringArray(R.array.toButtonTags);
		TypedArray fromButtonGroupIds = mContext.getResources().obtainTypedArray(R.array.fromRadioGroupIds);
		TypedArray toButtonGroupIds = mContext.getResources().obtainTypedArray(R.array.toRadioGroupIds);
		TypedArray fragmentLayouts = mContext.getResources().obtainTypedArray(R.array.fragmentLayouts);
		
		ConversionFragment conversionFragment = new ConversionFragment();
		data.putInt(fragmentNames[position], position+1);
		data.putInt(Constants.FRAGMENT_LAYOUT, fragmentLayouts.getResourceId(position, 0));
		data.putString(Constants.FRAGMENT_FROM_BUTTON_TAG, fromButtonTags[position]);
		data.putString(Constants.FRAGMENT_TO_BUTTON_TAG, toButtonTags[position]);
		data.putInt(Constants.FRAGMENT_FROM_BUTTONS_ID, fromButtonGroupIds.getResourceId(position, 0));
		data.putInt(Constants.FRAGMENT_TO_BUTTONS_ID, toButtonGroupIds.getResourceId(position, 0));
		
		if(position == Conversions.TEMPERATURE){
			data.putBoolean(Constants.FRAGMENT_IS_TEMPERATURE, true);
		}
		else{
			data.putBoolean(Constants.FRAGMENT_IS_TEMPERATURE, false);
		}
		
		//Pass arguments to fragment
		conversionFragment.setArguments(data);
		
		//Recycle typed arrays
		fromButtonGroupIds.recycle();
		toButtonGroupIds.recycle();
		fragmentLayouts.recycle();
		
		//Return newly-created fragment
		return conversionFragment;
	}
	
	@Override
	public CharSequence getPageTitle(int position){
		return mConversions[position];
	}

}
