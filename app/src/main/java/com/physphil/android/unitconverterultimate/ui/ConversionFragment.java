package com.physphil.android.unitconverterultimate.ui;

import com.physphil.android.unitconverterultimate.util.Constants;
import com.physphil.android.unitconverterultimate.util.Convert;
import com.physphil.android.unitconverterultimate.util.Util;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;


public class ConversionFragment extends Fragment{
	
	private Activity activity;
	private int fragmentLayout;
	private String fromButtonTag;
	private String toButtonTag;
	private int fromButtonsId;
	private int toButtonsId;
	private boolean isTemperature;
	private boolean justCreated;
	
	public ConversionFragment() {}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		activity = getActivity();
		
		//Define arguments which will customize fragment to specific conversion required
		fragmentLayout = getArguments().getInt(Constants.FRAGMENT_LAYOUT);
		fromButtonTag = getArguments().getString(Constants.FRAGMENT_FROM_BUTTON_TAG);
		toButtonTag = getArguments().getString(Constants.FRAGMENT_TO_BUTTON_TAG);
		fromButtonsId = getArguments().getInt(Constants.FRAGMENT_FROM_BUTTONS_ID);
		toButtonsId = getArguments().getInt(Constants.FRAGMENT_TO_BUTTONS_ID);
		isTemperature = getArguments().getBoolean(Constants.FRAGMENT_IS_TEMPERATURE);
		
		//Set justCreated to true, as fragment was just created
		justCreated = true;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		//Inflate appropriate layout based on selected conversion
		return inflater.inflate(fragmentLayout, container, false);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		
		//Restore state of radio buttons from previous use. If no values are stored, defaults from the fragment layout will be used
		int fromId = Util.getButtonState(activity, fromButtonTag);
		if(fromId != 0){
			RadioButton fromButton = (RadioButton) activity.findViewById(fromId);
			fromButton.setChecked(true);
		}
		
		int toId = Util.getButtonState(activity, toButtonTag);
		if(toId != 0){
			RadioButton toButton = (RadioButton) activity.findViewById(toId);
			toButton.setChecked(true);
		}
		
		//Add check changed listeners to radio button groups to convert when a new unit is selected
		Util.addCheckChangedListeners(activity, fromButtonsId, toButtonsId, isTemperature);

		//If fragment was just created, and it's visible to user, call conversion.
		//This was added to handle case where views are created by viewpager after they are selected by drawerListener, usually on app open
		if(justCreated && getUserVisibleHint()){
			
			justCreated = false;
			
			if(isTemperature){
				Convert.convertTempValue(activity);
			}
			else{
				Util.onFragmentVisible(activity, fromButtonsId, toButtonsId);
			}
		}
	}

	@Override
	public void onPause(){
		super.onPause();
		
		//Save state of radio buttons for next use
		Util.setButtonState(activity, fromButtonsId, toButtonsId, fromButtonTag, toButtonTag);
	}
	
}
