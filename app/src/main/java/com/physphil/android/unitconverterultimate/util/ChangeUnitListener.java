package com.physphil.android.unitconverterultimate.util;

import android.app.Activity;
import android.widget.RadioGroup;

public class ChangeUnitListener implements RadioGroup.OnCheckedChangeListener {

	private Activity activity;
	private boolean isFromUnit;
	private boolean isTemperature;
	
	public ChangeUnitListener(Activity activity, boolean isFromUnit, boolean isTemperature){
		this.activity = activity;
		this.isFromUnit = isFromUnit;
		this.isTemperature = isTemperature;
	}
	
	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
		//Convert
		int radioGroupId = radioGroup.getId();
		int complimentaryId = Util.getComplimentaryId(radioGroupId);

		//Call appropriate conversion function based on visible fragment and specific unit requested
		if(isTemperature){
			Convert.convertTempValue(activity);
		}
		else if(isFromUnit){
		    Convert.convertValue(activity, radioGroupId, complimentaryId);
		}
		else{
			Convert.convertValue(activity, complimentaryId, radioGroupId);
		}
	}
}
