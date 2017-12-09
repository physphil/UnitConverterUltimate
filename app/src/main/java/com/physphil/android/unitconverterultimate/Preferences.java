/*
 * Copyright 2017 Phil Shadlyn
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

package com.physphil.android.unitconverterultimate;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.physphil.android.unitconverterultimate.api.models.CurrencyResponse;
import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.Language;

/**
 * Preferences class
 * Created by Phizz on 15-07-31.
 */
public class Preferences {

    public static final String PREFS_THEME = "light_theme";
    public static final String PREFS_FROM_VALUE = "from_value";
    public static final String PREFS_NUMBER_OF_DECIMALS = "number_decimals";
    public static final String PREFS_DECIMAL_SEPARATOR = "decimal_separator";
    public static final String PREFS_GROUP_SEPARATOR = "group_separator";
    public static final String PREFS_HAS_RATED = "has_rated";
    public static final String PREFS_APP_OPEN_COUNT = "app_open_count";
    public static final String PREFS_SHOW_HELP = "show_help";
    private static final String PREFS_CONVERSION = "conversion";
    private static final String PREFS_CURRENCY = "currency";
    public static final String PREFS_LANGUAGE = "language";

    private static Preferences mInstance;
    private SharedPreferences mPrefs;
    private Context mContext;

    public static Preferences getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Preferences(context.getApplicationContext());
        }

        return mInstance;
    }

    private Preferences(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mContext = context;
    }

    /**
     * Get instance of app's Shared Preferences
     *
     * @return SharedPreference instance
     */
    public SharedPreferences getPreferences() {
        return mPrefs;
    }

    public boolean isLightTheme() {
        return mPrefs.getBoolean(PREFS_THEME, false);
    }

    @SuppressWarnings("ResourceType")
    public
    @Conversion.id
    int getLastConversion() {
        return mPrefs.getInt(PREFS_CONVERSION, Conversion.AREA);
    }

    public void setLastConversion(@Conversion.id int conversionId) {
        mPrefs.edit().putInt(PREFS_CONVERSION, conversionId).apply();
    }

    public String getLastValue() {
        return mPrefs.getString(PREFS_FROM_VALUE, "1.0");
    }

    public void setLastValue(String value) {
        mPrefs.edit().putString(PREFS_FROM_VALUE, value).apply();
    }

    public int getNumberDecimals() {
        return Integer.parseInt(mPrefs.getString(PREFS_NUMBER_OF_DECIMALS, mContext.getString(R.string.default_number_decimals)));
    }

    public String getDecimalSeparator() {
        return mPrefs.getString(PREFS_DECIMAL_SEPARATOR, mContext.getString(R.string.default_decimal_separator));
    }

    public String getGroupSeparator() {
        return mPrefs.getString(PREFS_GROUP_SEPARATOR, mContext.getString(R.string.default_group_separator));
    }

    public void setShowHelp(boolean showHelp) {
        mPrefs.edit().putBoolean(PREFS_SHOW_HELP, showHelp).apply();
    }

    public boolean showHelp() {
        return mPrefs.getBoolean(PREFS_SHOW_HELP, true);
    }

    public boolean hasLatestCurrency() {
        return mPrefs.contains(PREFS_CURRENCY);
    }

    public void saveLatestCurrency(CurrencyResponse latestCurrency) {
        mPrefs.edit().putString(PREFS_CURRENCY, new Gson().toJson(latestCurrency)).apply();
    }

    public CurrencyResponse getLatestCurrency() {
        if (mPrefs.contains(PREFS_CURRENCY)) {
            String currency = mPrefs.getString(PREFS_CURRENCY, null);
            return new Gson().fromJson(currency, CurrencyResponse.class);
        }
        else {
            return null;
        }
    }

    public void setLanguage(final Language language) {
        mPrefs.edit().putString(PREFS_LANGUAGE, language.getId()).apply();
    }

    @NonNull
    public Language getLanguage() {
        final String id = mPrefs.getString(PREFS_LANGUAGE, Language.DEFAULT.getId());
        return Language.fromId(id);
    }
}
