package com.physphil.android.unitconverterultimate.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.physphil.android.unitconverterultimate.R;

public class Convert {
	
    public static void convertValue(Activity activity, int fromRadioId, int toRadioId){

        RadioGroup radioGroupFrom = (RadioGroup) activity.findViewById(fromRadioId);
        RadioGroup radioGroupTo = (RadioGroup) activity.findViewById(toRadioId);
        EditText fromText = (EditText) activity.findViewById(R.id.fromValue);
        EditText toText = (EditText) activity.findViewById(R.id.toValue);
        double toValue = 0;
        double conversion = 1;

        if(radioGroupFrom != null && radioGroupTo != null){

            //Get checked radio button IDs
            int fromId = radioGroupFrom.getCheckedRadioButtonId();
            int toId = radioGroupTo.getCheckedRadioButtonId();

            //Get names of from/to units
            String fromIdString = activity.getResources().getResourceName(fromId);
            String toIdString = activity.getResources().getResourceName(toId);

            //Format strings so they just contain the unit (i.e. Kilogram, Mile, etc)
            String fromConversion = fromIdString.substring(fromIdString.lastIndexOf("/") + 1);
            String toConversion = toIdString.substring(toIdString.lastIndexOf("/") + 1);

            String fromValueString = fromText.getText().toString();
            double fromValue = 0;

            //Handle cases where fromValue starts with a - or decimal point. Don't try to parse that value, leave fromValue = 0
            if(isStringNumeric(fromValueString)){
                fromValue = Double.parseDouble(fromValueString);
            }
            
            //Get conversion between units if they're not equal
            String fromUnit = fromConversion.substring(4);
            String toUnit = toConversion.substring(2);
            
            double fromMult = Globals.conversions.get(fromConversion);
	        double toMult = Globals.conversions.get(toConversion);

            if(fromUnit.equals(Conversions.LITRES_PER_100K) || toUnit.equals(Conversions.LITRES_PER_100K)){
            	
            	if(fromUnit.equals(toUnit)){
            		toValue = fromValue;
            	}
            	else if(fromUnit.equals(Conversions.LITRES_PER_100K)){
            		toValue = (fromMult / fromValue) * toMult;
            	}
            	else if(toUnit.equals(Conversions.LITRES_PER_100K)){
            		toValue = toMult / (fromValue * fromMult);
            	}
            }
            else{
            	
            	if(!fromUnit.equals(toUnit)){
            		conversion = fromMult * toMult;
            	}
            	
            	toValue = fromValue * conversion;
            }

            //Format output
            toText.setText(format(activity).format(toValue));
        }
    }
	
	public static void convertTempValue(Activity activity){

		RadioGroup radioGroupFromTemp = (RadioGroup) activity.findViewById(R.id.radioFromTemp);
		RadioGroup radioGroupToTemp = (RadioGroup) activity.findViewById(R.id.radioToTemp);
		EditText fromTempText = (EditText) activity.findViewById(R.id.fromValue);
		EditText toTempText = (EditText) activity.findViewById(R.id.toValue);
		double toTemp = 0;
		
		//v1.1 - Only proceed with convert if radioGroups exist
		if(radioGroupFromTemp != null && radioGroupToTemp != null){
			
			//Get checked radio button IDs
			int fromId = radioGroupFromTemp.getCheckedRadioButtonId();
			int toId = radioGroupToTemp.getCheckedRadioButtonId();
			
			//String fromTempString = cleanInputValue(activity, fromTempText.getText().toString());
			String fromTempString = fromTempText.getText().toString();
			double fromTemp = 0;

			//Handle cases where fromValue starts with a - or decimal point. Don't try to parse that value, leave fromValue = 0
			if(isStringNumeric(fromTempString)){
				fromTemp = Double.parseDouble(fromTempString);
			}
			
			//Call appropriate conversion function
			switch(toId){
			case(R.id.toCelsius):
				toTemp = toCelsius(fromId, fromTemp);
			break;
			case(R.id.toFahrenheit):
				toTemp = toFahrenheit(fromId, fromTemp);
			break;
			case(R.id.toKelvin):
				toTemp = toKelvin(fromId, fromTemp);
			break;
			case(R.id.toRankine):
				toTemp = toRankine(fromId, fromTemp);
			break;
			case(R.id.toDelisle):
				toTemp = toDelisle(fromId, fromTemp);
			break;
			case(R.id.toNewton):
				toTemp = toNewton(fromId, fromTemp);
			break;
			case(R.id.toReaumur):
				toTemp = toReaumur(fromId, fromTemp);
			break;
			case(R.id.toRomer):
				toTemp = toRomer(fromId, fromTemp);
			break;
			case(R.id.toGasMark):
				toTemp = toGasMark(fromId, fromTemp);
			break;
			}
			
			//Format output
			toTempText.setText(format(activity).format(toTemp));	
		}
	}
	
	//Conversion functions
	private static double toCelsius(int fromId, double fromTemp){
		double toTemp=0;
		
		switch(fromId){
		case(R.id.fromCelsius):	// C to C (no conversion)
			toTemp = fromTemp;
		break;	
		
		case(R.id.fromFahrenheit):	// F to C
			toTemp = (fromTemp - 32) * 5 / 9;
		break;
		
		case(R.id.fromKelvin):	// K to C
			toTemp = fromTemp - 273.15;
		break;
		
		case(R.id.fromRankine):	// R to C
			toTemp = (fromTemp - 491.67) * 5 / 9;
		break;
		
		case(R.id.fromDelisle):	// D to C
			toTemp = 100 - fromTemp*2/3;
		break;
		
		case(R.id.fromNewton):	//N to C
			toTemp = fromTemp*100/33;
		break;
		
		case(R.id.fromReaumur):	//Re to C
			toTemp = fromTemp*5/4;
		break;
		
		case(R.id.fromRomer):	//Ro to C
			toTemp = (fromTemp-7.5)*40/21;
		break;
		
		case(R.id.fromGasMark): //GM to C
			//Convert from GM to F, then from F to C
			toTemp = fromGasMark(fromTemp);
			toTemp = (toTemp - 32) * 5 / 9;
		break;
		}
		
		return toTemp;
	}
	
	private static double toFahrenheit(int fromId, double fromTemp){
		double toTemp=0;
		
		switch(fromId){
		case(R.id.fromCelsius):	// C to F
			toTemp = fromTemp * 9 / 5 +32;
		break;	
		
		case(R.id.fromFahrenheit):	// F to F
			toTemp = fromTemp;
		break;
		
		case(R.id.fromKelvin):	// K to F
			toTemp = fromTemp * 9 / 5 - 459.67;
		break;
		
		case(R.id.fromRankine):	// R to F
			toTemp = fromTemp - 459.67;
		break;
		
		case(R.id.fromDelisle):	//D to F
			toTemp = 212 - fromTemp*6/5;
		break;
		
		case(R.id.fromNewton):	//N to F
			toTemp = fromTemp * 60/11+32;
		break;
		
		case(R.id.fromReaumur):	//Re to F
			toTemp = fromTemp * 9/4 + 32;
		break;
		
		case(R.id.fromRomer):	//Ro to F
			toTemp = (fromTemp-7.5) * 24/7 + 32;
		break;
		
		case(R.id.fromGasMark):
			toTemp = fromGasMark(fromTemp);
		break;
		}
		
		return toTemp;
	}
	
	private static double toKelvin(int fromId, double fromTemp){
		double toTemp=0;
		
		switch(fromId){
		case(R.id.fromCelsius):	// C to K
			toTemp = fromTemp + 273.15;
		break;	
		
		case(R.id.fromFahrenheit):	// F to K
			toTemp = (fromTemp + 459.67) * 5 / 9;
		break;
		
		case(R.id.fromKelvin):	// K to K
			toTemp = fromTemp;
		break;
		
		case(R.id.fromRankine):	// R to K
			toTemp = fromTemp * 5 / 9;
		break;
		
		case(R.id.fromDelisle):	//D to K
			toTemp = 373.15-fromTemp * 2/3;
		break;
		
		case(R.id.fromNewton):	//N to K
			toTemp = fromTemp*100/33 + 273.15;
		break;
		
		case(R.id.fromReaumur):	//Re to K
			toTemp = fromTemp*5/4 + 273.15;
		break;
		
		case(R.id.fromRomer):	//Ro to K
			toTemp = (fromTemp-7.5)*40/21 + 273.15;
		break;
		
		case(R.id.fromGasMark):
			toTemp = fromGasMark(fromTemp);
			toTemp = (toTemp + 459.67) * 5 / 9;
		break;
		}
		
		return toTemp;
	}
	
	private static double toRankine(int fromId, double fromTemp){
		double toTemp=0;
		
		switch(fromId){
		case(R.id.fromCelsius):	// C to R
			toTemp = (fromTemp + 273.15) * 9 / 5;
		break;	
		
		case(R.id.fromFahrenheit):	// F to R
			toTemp = fromTemp + 459.67;
		break;
		
		case(R.id.fromKelvin):	// K to R
			toTemp = fromTemp * 9 / 5;
		break;
		
		case(R.id.fromRankine):	// R to R
			toTemp = fromTemp;
		break;
		
		case(R.id.fromDelisle):	//D to R
			toTemp = 671.67-fromTemp*6/5;
		break;
		
		case(R.id.fromNewton):	//N to R
			toTemp = fromTemp*60/11 + 491.67;
		break;
		
		case(R.id.fromReaumur):	//Re to R
			toTemp = fromTemp*9/4 + 491.67;
		break;
		
		case(R.id.fromRomer):	//Ro to R
			toTemp = (fromTemp - 7.5)*24/7 + 491.67;
		break;
		
		case(R.id.fromGasMark):
			toTemp = fromGasMark(fromTemp);
			toTemp = toTemp + 459.67;
		break;
		}
			
		return toTemp;
	}
	
	private static double toDelisle(int fromId, double fromTemp){
		double toTemp=0;
		
		switch(fromId){
		case(R.id.fromCelsius):	// C to D
			toTemp = (100-fromTemp) * 1.5;
		break;	
		
		case(R.id.fromFahrenheit):	// F to D
			toTemp = (212-fromTemp) *5/6;
		break;
		
		case(R.id.fromKelvin):	// K to D
			toTemp = (373.15-fromTemp)*1.5;
		break;
		
		case(R.id.fromRankine):	// R to D
			toTemp = (671.67-fromTemp)*5/6;
		break;
		
		case(R.id.fromDelisle):	//D to D
			toTemp = fromTemp;
		break;
		
		case(R.id.fromNewton):	//N to D
			toTemp = (33-fromTemp)*50/11;
		break;
		
		case(R.id.fromReaumur):	//Re to D
			toTemp = (80-fromTemp)*1.875;
		break;
		
		case(R.id.fromRomer):	//Ro to D
			toTemp = (60-fromTemp)*20/7;
		break;
		
		case(R.id.fromGasMark):
			toTemp = fromGasMark(fromTemp);
			toTemp = (212 - toTemp) * 5 / 6;
		break;
		}
		
		return toTemp;
	}
	
	private static double toNewton(int fromId, double fromTemp){
		double toTemp=0;
		
		switch(fromId){
		case(R.id.fromCelsius):	// C to N
			toTemp = fromTemp * 33/100;
		break;	
		
		case(R.id.fromFahrenheit):	// F to N
			toTemp = (fromTemp-32) *11/60;
		break;
		
		case(R.id.fromKelvin):	// K to N
			toTemp = (fromTemp-273.15)*33/100;
		break;
		
		case(R.id.fromRankine):	// R to N
			toTemp = (fromTemp-491.67)*11/60;
		break;
		
		case(R.id.fromDelisle):	//D to N
			toTemp = 33-fromTemp*11/50;
		break;
		
		case(R.id.fromNewton):	//N to N
			toTemp = fromTemp;
		break;
		
		case(R.id.fromReaumur):	//Re to N
			toTemp = fromTemp*33/80;
		break;
		
		case(R.id.fromRomer):	//Ro to N
			toTemp = (fromTemp-7.5)*22/35;
		break;
		
		case(R.id.fromGasMark):
			toTemp = fromGasMark(fromTemp);
			toTemp = (toTemp - 32) * 11 / 60;
		break;
		}
		
		return toTemp;
	}
	
	private static double toReaumur(int fromId, double fromTemp){
		double toTemp=0;
		
		switch(fromId){
		case(R.id.fromCelsius):	// C to Re
			toTemp = fromTemp*4/5;
		break;	
		
		case(R.id.fromFahrenheit):	// F to Re
			toTemp = (fromTemp-32)*4/9;
		break;
		
		case(R.id.fromKelvin):	// K to Re
			toTemp = (fromTemp-273.15)*4/5;
		break;
		
		case(R.id.fromRankine):	// R to Re
			toTemp = (fromTemp-491.67)*4/9;
		break;
		
		case(R.id.fromDelisle):	//D to Re
			toTemp = 80 - fromTemp*8/15;
		break;
		
		case(R.id.fromNewton):	//N to Re
			toTemp = fromTemp*80/33;
		break;
		
		case(R.id.fromReaumur):	//Re to Re
			toTemp = fromTemp;
		break;
		
		case(R.id.fromRomer):	//Ro to Re
			toTemp = (fromTemp-7.5)*32/21;
		break;
		
		case(R.id.fromGasMark):
			toTemp = fromGasMark(fromTemp);
			toTemp = (toTemp - 32) * 4 / 9;
		break;
		}
		
		return toTemp;
	}
	
	private static double toRomer(int fromId, double fromTemp){
		double toTemp=0;
		
		switch(fromId){
		case(R.id.fromCelsius):	// C to Ro
			toTemp = fromTemp*21/40 + 7.5;
		break;	
		
		case(R.id.fromFahrenheit):	// F to Ro
			toTemp = (fromTemp-32)*7/24 + 7.5;
		break;
		
		case(R.id.fromKelvin):	// K to Ro
			toTemp = (fromTemp-273.15)*21/40 + 7.5;
		break;
		
		case(R.id.fromRankine):	// R to Ro
			toTemp = (fromTemp-491.67)*7/24 + 7.5;
		break;
		
		case(R.id.fromDelisle):	//D to Ro
			toTemp = 60 - fromTemp * 7/20;
		break;
		
		case(R.id.fromNewton):	//N to Ro
			toTemp = fromTemp*35/22 + 7.5;
		break;
		
		case(R.id.fromReaumur):	//Re to Ro
			toTemp = fromTemp*21/32 + 7.5;
		break;
		
		case(R.id.fromRomer):	//Ro to Ro
			toTemp = fromTemp;
		break;
		
		case(R.id.fromGasMark):
			toTemp = fromGasMark(fromTemp);
			toTemp = (toTemp - 32) * 7 / 24 + 7.5;
		break;
		}
		
		return toTemp;
	}
	
	private static double toGasMark(int fromId, double fromTemp){
		double toTempGM = 0;
		double toTempF = 0;
		
		//Convert incoming temperature to Fahrenheit, then convert from F to Gas Mark
		toTempF = toFahrenheit(fromId, fromTemp);
		
		if(toTempF >= 275){
			toTempGM = 0.04*toTempF - 10;
		}
		else if(toTempF < 275){
			toTempGM = 0.01*toTempF - 2;
		}
		
		if(toTempGM < 0) toTempGM = 0;
		
		return toTempGM;
	}
	
	private static double fromGasMark(double fromTemp){
		double toTempF = 0;
		
		//Convert incoming Gas Mark to Fahrenheit, which will then be subequently converted to desired unit
		if (fromTemp >= 1){
			toTempF = 25*fromTemp + 250;
		}
		else if (fromTemp < 1){
			toTempF = 100*fromTemp + 200;
		}
		
		return toTempF;
	}
	
	private static DecimalFormat format(Activity activity){
		DecimalFormat formatter = new DecimalFormat();
		int defaultNoDecimals = Constants.DEFAULT_NO_DECIMALS;
		SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
		
		int currentNoDecimals = preferences.getInt(Constants.SETTINGS_NUMBER_OF_DECIMALS, defaultNoDecimals);
		boolean separatorUsed = preferences.getBoolean(Constants.SETTINGS_IS_SEPARATOR_USED, false);
		int currentSeparator = preferences.getInt(Constants.SETTINGS_CURRENT_SEPARATOR, 0);
		int currentDecimalSeparator = preferences.getInt(Constants.SETTINGS_CURRENT_DECIMAL_SEPARATOR, 0);
		
		//Set maximum number of decimal places
		formatter.setMaximumFractionDigits(currentNoDecimals);
		
		//Set group and decimal separators
		DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
		String[] mDecimalSeparators = activity.getResources().getStringArray(R.array.decimalSeparatorType);
		char decimalSeparatorType = mDecimalSeparators[currentDecimalSeparator].charAt(0);
		symbols.setDecimalSeparator(decimalSeparatorType);
		
		formatter.setGroupingUsed(separatorUsed);

		if(separatorUsed){
			String[] mGroupSeparators = activity.getResources().getStringArray(R.array.separatorType);
			char separatorType = mGroupSeparators[currentSeparator].charAt(0);
			symbols.setGroupingSeparator(separatorType);		
		}
		
		formatter.setDecimalFormatSymbols(symbols);
		return formatter;
	}
	
	public static boolean isStringNumeric(String str){
		//Checks if the input is blank, only a minus sign, only a decimal, or a combination of minus and decimal.
		//This is still technically a number, just equal to 0.  The return of false will be interpreted by the program as a 0 when converting
		
		//Set to US-English as that is the default locale for the Android keyboard with inputType = numberDecimal
		DecimalFormat currentFormat = (DecimalFormat) NumberFormat.getNumberInstance(Locale.US);
		DecimalFormatSymbols currentSymbols = currentFormat.getDecimalFormatSymbols();
		String minus = Character.toString(currentSymbols.getMinusSign());
		String decimal = Character.toString(currentSymbols.getDecimalSeparator());
		String minusDecimal = minus+decimal;
		
		if(str.equalsIgnoreCase("") || str.equalsIgnoreCase(minus) || str.equalsIgnoreCase(decimal) || str.equalsIgnoreCase(minusDecimal)){
			return false;
		}
		else{
			return true;
		}
	}

}
