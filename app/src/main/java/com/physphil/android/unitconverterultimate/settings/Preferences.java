/*
 * Copyright 2018 Phil Shadlyn
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

package com.physphil.android.unitconverterultimate.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.gson.Gson;
import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.api.models.Currencies;
import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.Language;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Preferences class
 * Created by Phizz on 15-07-31.
 */
public class Preferences {

    public static final String PREFS_THEME = "light_theme";
    public static final String PREFS_FROM_VALUE = "from_value";
    public static final String PREFS_NUMBER_OF_DECIMALS = "number_decimals";
    public static final String PREFS_DECIMAL_SEPARATOR = "decimal_separator";
    private static final String PREFS_GROUP_SEPARATOR = "group_separator";
    public static final String PREFS_GROUP_SEPARATOR_V2 = "group_separator_v2";
    public static final String PREFS_HAS_RATED = "has_rated";
    public static final String PREFS_APP_OPEN_COUNT = "app_open_count";
    public static final String PREFS_SHOW_HELP = "show_help";
    private static final String PREFS_CONVERSION = "conversion";
    private static final String PREFS_CURRENCY_V2 = "currency_v2";
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

    /**
     * Returns the group separator being used, or `null` if no separator is used.
     */
    @Nullable
    public Character getGroupSeparator() {
        String defaultSeparator = getLanguage().getDefaultGroupSeparator().getIndex();
        Log.d("phil", "Default separator = " + defaultSeparator);
        if (mPrefs.contains(PREFS_GROUP_SEPARATOR_V2)) {
            return getSeparatorFromIndex(mPrefs.getString(PREFS_GROUP_SEPARATOR_V2, defaultSeparator));
        } else {
            // If the v2 preference is missing than populate from v1.
            String convertedV1Separator = getSavedV1GroupSeparatorIndexOrNull();
            String v2Separator = convertedV1Separator != null ? convertedV1Separator : defaultSeparator;
            mPrefs.edit().putString(PREFS_GROUP_SEPARATOR_V2, v2Separator).apply();
            return getSeparatorFromIndex(v2Separator);
        }
    }

    /**
     * Map a saved index in shared preferences to a group separator character.
     *
     * "0" - None
     * "1" - ,
     * "2" - ' '
     * "3" - .
     */
    private Character getSeparatorFromIndex(String index) {
        switch (index) {
            case "1":
                return ',';
            case "2":
                return ' ';
            case "3":
                return '.';
            default:
                return null;
        }
    }

    /**
     * Map the v1 group separator to its appropriate index in v2, or `null` if there is no saved
     * v1 group separator.
     */
    @Nullable
    private String getSavedV1GroupSeparatorIndexOrNull() {
        if (mPrefs.contains(PREFS_GROUP_SEPARATOR)) {
            switch (mPrefs.getString(PREFS_GROUP_SEPARATOR, "should never happen")) {
                case ",":
                    return "1";
                case " ":
                    return "2";
                case ".":
                    return "3";
                default:
                    return null;
            }
        }
        return null;
    }

    public void setShowHelp(boolean showHelp) {
        mPrefs.edit().putBoolean(PREFS_SHOW_HELP, showHelp).apply();
    }

    public boolean showHelp() {
        return mPrefs.getBoolean(PREFS_SHOW_HELP, true);
    }

    public boolean hasLatestCurrency() {
        return mPrefs.contains(PREFS_CURRENCY_V2);
    }

    public void saveLatestCurrency(Currencies currencies) {
        mPrefs.edit().putString(PREFS_CURRENCY_V2, new Gson().toJson(currencies)).apply();
    }

    public Currencies getLatestCurrency() {
        if (mPrefs.contains(PREFS_CURRENCY_V2)) {
            String currencies = mPrefs.getString(PREFS_CURRENCY_V2, null);
            return new Gson().fromJson(currencies, Currencies.class);
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
