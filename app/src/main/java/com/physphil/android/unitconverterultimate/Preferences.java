package com.physphil.android.unitconverterultimate;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.physphil.android.unitconverterultimate.util.Constants;
import com.physphil.android.unitconverterultimate.util.Conversions;

/**
 * Preferences class
 * Created by Phizz on 15-07-31.
 */
public class Preferences
{
    private static final String PREFS_THEME = "light_theme";
    private static final String PREFS_CONVERSION = "conversion";
    private static final String PREFS_FROM_VALUE = "from_value";
    private static final String PREFS_NUMBER_OF_DECIMALS = "number_decimals";
    private static final String PREFS_DECIMAL_SEPARATOR = "decimal_separator";
    private static final String PREFS_GROUP_SEPARATOR = "group_separator";
    private static final String PREFS_HAS_RATED = "has_rated";
    private static final String PREFS_APP_OPEN_COUNT = "app_open_count";

    private static Preferences mInstance;
    private SharedPreferences mPrefs;

    public static Preferences getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new Preferences(context);
        }

        return mInstance;
    }

    private Preferences(Context context)
    {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Get instance of app's Shared Preferences
     * @return SharedPreference instance
     */
    public SharedPreferences getPreferences()
    {
        return mPrefs;
    }

    public boolean isLightTheme()
    {
        return mPrefs.getBoolean(PREFS_THEME, false);
    }

    public void setLightTheme(boolean lightTheme)
    {
        mPrefs.edit().putBoolean(PREFS_THEME, lightTheme).apply();
    }

    public int getLastConversion()
    {
        return mPrefs.getInt(PREFS_CONVERSION, Conversions.AREA);
    }

    public void setLastConversion(int conversionId)
    {
        mPrefs.edit().putInt(PREFS_CONVERSION, conversionId).apply();
    }

    public String getLastValue()
    {
        return mPrefs.getString(PREFS_FROM_VALUE, "1.0");
    }

    public void setLastValue(String value)
    {
        mPrefs.edit().putString(PREFS_FROM_VALUE, value).apply();
    }

    public int getNumberDecimals()
    {
        return mPrefs.getInt(PREFS_NUMBER_OF_DECIMALS, Constants.DEFAULT_NO_DECIMALS);
    }

    public String getDecimalSeparator()
    {
        return mPrefs.getString(PREFS_DECIMAL_SEPARATOR, ".");
    }

    public String getGroupSeparator()
    {
        return mPrefs.getString(PREFS_GROUP_SEPARATOR, "");
    }
}
