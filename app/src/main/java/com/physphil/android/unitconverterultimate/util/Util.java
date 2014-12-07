package com.physphil.android.unitconverterultimate.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.physphil.android.unitconverterultimate.R;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;


public class Util {	
	
	//This class contains several common methods which are used repeatedly throughout the app
	
	public static void onFragmentVisible(Activity activity, int fromButtonGroupId, int toButtonGroupId){
		//Convert numbers as soon as fragment is loaded and ready to interact with user
		EditText fromValueView = (EditText) activity.findViewById(R.id.fromValue);
		
		//Remove minus sign as negative numbers are not allowed. US = Android numeric keyboard default locale
		DecimalFormat currentFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
		DecimalFormatSymbols currentSymbols = currentFormat.getDecimalFormatSymbols();
		String minus = Character.toString(currentSymbols.getMinusSign());
		String fromValueString = fromValueView.getText().toString();
		
		if(Convert.isStringNumeric(fromValueString)){
			//take absolute value of fromValue in case coming from temperature, which allows negative values
			//listener on change of fromValue will call convert 
			double fromValue = Math.abs(Double.parseDouble(fromValueString));
					
			//Add formatting to remove trailing .0 if number is an integer
			currentFormat.setMaximumFractionDigits(activity.getResources().getInteger(R.integer.fromMaxNoDigits));
			currentFormat.setGroupingUsed(false);
			fromValueView.setText(currentFormat.format(fromValue));
			fromValueView.setSelection(fromValueView.getText().length());
		}
		else if(fromValueString.contains(minus)){
			//Remove minus sign as negative numbers are not allowed. Change of fromValue will trigger listener to call Convert
			fromValueView.setText(fromValueString.replaceAll(minus, ""));
			fromValueView.setSelection(fromValueView.getText().length());
		}
		else{
			//fromValue not changed, so convert as listener will not call it. This handles when input starts with a negative sign or decimal and no leading 0
			Convert.convertValue(activity, fromButtonGroupId, toButtonGroupId);
		}
	}
	
	//Save currently selected unit to shared preferences file
	public static void setButtonState(Activity activity, int fromButtonGroupId, int toButtonGroupId, String fromTag, String toTag){
		//Save state of radio buttons for next use
		RadioGroup fromButtons = (RadioGroup) activity.findViewById(fromButtonGroupId);
		RadioGroup toButtons = (RadioGroup) activity.findViewById(toButtonGroupId);
		String fromIdString = activity.getResources().getResourceName(fromButtons.getCheckedRadioButtonId());
		String toIdString = activity.getResources().getResourceName(toButtons.getCheckedRadioButtonId());
				
		//Save ID string instead of resource ID, as integer ID values can change when new resources are added 
		SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(fromTag, fromIdString);
		editor.putString(toTag, toIdString);
		editor.commit();		
	}
	
	//Restore previously selected unit from shared preferences file
	public static int getButtonState(Activity activity, String buttonTag){
		int checkedId = 0;
		
		//Get checked radio button
		SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
		String checkedIdString = preferences.getString(buttonTag, null);
		
		//Only restore previous checked state if the saved values exist
		if (checkedIdString != null){
			checkedId = activity.getResources().getIdentifier(checkedIdString, "string", activity.getPackageName());
		}
		
		return checkedId;
	}
	
	//Add onCheckChanged listeners to the From/To radio button groups
	public static void addCheckChangedListeners(Activity activity, int fromRadioGroupId, int toRadioGroupId, boolean isTemperature){
		
		RadioGroup fromButtons = (RadioGroup) activity.findViewById(fromRadioGroupId);
		fromButtons.setOnCheckedChangeListener(new ChangeUnitListener(activity, true, isTemperature));
		
		RadioGroup toButtons = (RadioGroup) activity.findViewById(toRadioGroupId);
		toButtons.setOnCheckedChangeListener(new ChangeUnitListener(activity, false, isTemperature));
	}
	
	public static int getComplimentaryId(int radioGroupId){
		int complimentaryId = 0;
		
		if (radioGroupId == R.id.radioFromArea) {
			complimentaryId = R.id.radioToArea;
		} 
		else if (radioGroupId == R.id.radioFromCooking) {
			complimentaryId = R.id.radioToCooking;
		} 
		else if (radioGroupId == R.id.radioFromEnergy) {
			complimentaryId = R.id.radioToEnergy;
		} 
		else if (radioGroupId == R.id.radioFromFuel) {
			complimentaryId = R.id.radioToFuel;
		} 
		else if (radioGroupId == R.id.radioFromLength) {
			complimentaryId = R.id.radioToLength;
		}
		else if (radioGroupId == R.id.radioFromMass) {
			complimentaryId = R.id.radioToMass;
		}
		else if (radioGroupId == R.id.radioFromPower) {
			complimentaryId = R.id.radioToPower;
		}
		else if (radioGroupId == R.id.radioFromPressure) {
			complimentaryId = R.id.radioToPressure;
		}
		else if (radioGroupId == R.id.radioFromSpeed) {
			complimentaryId = R.id.radioToSpeed;
		}
		else if (radioGroupId == R.id.radioFromStorage) {
			complimentaryId = R.id.radioToStorage;
		}
		else if (radioGroupId == R.id.radioFromTemp) {
			complimentaryId = R.id.radioToTemp;
		}
		else if (radioGroupId == R.id.radioFromTime) {
			complimentaryId = R.id.radioToTime;
		}
        else if (radioGroupId == R.id.radioFromTorque) {
            complimentaryId = R.id.radioToTorque;
        }
        else if (radioGroupId == R.id.radioFromVolume) {
            complimentaryId = R.id.radioToVolume;
        }
		else if (radioGroupId == R.id.radioToArea) {
			complimentaryId = R.id.radioFromArea;
		}
		else if (radioGroupId == R.id.radioToCooking) {
			complimentaryId = R.id.radioFromCooking;
		}
		else if (radioGroupId == R.id.radioToEnergy) {
			complimentaryId = R.id.radioFromEnergy;
		} 
		else if (radioGroupId == R.id.radioToFuel) {
			complimentaryId = R.id.radioFromFuel;
		}
		else if (radioGroupId == R.id.radioToLength) {
			complimentaryId = R.id.radioFromLength;
		}
		else if (radioGroupId == R.id.radioToMass) {
			complimentaryId = R.id.radioFromMass;
		}
		else if (radioGroupId == R.id.radioToPower) {
			complimentaryId = R.id.radioFromPower;
		}
		else if (radioGroupId == R.id.radioToPressure) {
			complimentaryId = R.id.radioFromPressure;
		}
		else if (radioGroupId == R.id.radioToSpeed) {
			complimentaryId = R.id.radioFromSpeed;
		}
		else if (radioGroupId == R.id.radioToStorage) {
			complimentaryId = R.id.radioFromStorage;
		} 
		else if (radioGroupId == R.id.radioToTemp) {
			complimentaryId = R.id.radioFromTemp;
		} 
		else if (radioGroupId == R.id.radioToTime) {
			complimentaryId = R.id.radioFromTime;
		}
        else if (radioGroupId == R.id.radioToTorque) {
            complimentaryId = R.id.radioFromTorque;
        }
        else if (radioGroupId == R.id.radioToVolume) {
            complimentaryId = R.id.radioFromVolume;
        }
		
		return complimentaryId;
	}

}
