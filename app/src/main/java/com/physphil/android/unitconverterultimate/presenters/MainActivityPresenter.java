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

package com.physphil.android.unitconverterultimate.presenters;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import com.physphil.android.unitconverterultimate.Preferences;
import com.physphil.android.unitconverterultimate.R;

import java.util.Locale;

/**
 * Created by Phizz on 2017-05-21.
 */
public class MainActivityPresenter {
    private MainActivityView mView;
    private Context mContext;
    private Preferences mPrefs;

    public MainActivityPresenter(MainActivityView view, Context context, Preferences prefs) {
        mView = view;
        mContext = context;
        mPrefs = prefs;
    }

    public void onLanguageChanged() {
        final String language = mPrefs.getLanguage();
        final String[] languages = mContext.getResources().getStringArray(R.array.language_values);
        int position = 0;
        for (int i = 0, size = languages.length; i < size; i++) {
            if (languages[i].equals(language)) {
                position = i;
                break;
            }
        }

        // Now set language and restart app to take effect
        mPrefs.setLanguage(languages[position]);
        mView.restartApp();
    }

    public void setLanguageToDisplay() {
        final String language = mPrefs.getLanguage();

        // Use default locale if not specified, otherwise use saved locale from preferences
        Locale locale = Locale.getDefault();
        if (!language.equals(mContext.getString(R.string.language_default))) {
            locale = new Locale(language);
        }

        final Resources resources = mContext.getResources();
        final Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        }
        else {
            configuration.locale = locale;
        }
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }
}
