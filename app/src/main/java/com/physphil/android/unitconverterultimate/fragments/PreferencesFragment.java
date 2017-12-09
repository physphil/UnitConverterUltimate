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

package com.physphil.android.unitconverterultimate.fragments;

import android.content.ActivityNotFoundException;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.BuildConfig;
import com.physphil.android.unitconverterultimate.Preferences;
import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.UnitConverterApplication;
import com.physphil.android.unitconverterultimate.models.Language;
import com.physphil.android.unitconverterultimate.util.IntentFactory;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Fragment to display preferences screen
 * Created by Phizz on 15-08-02.
 */
public class PreferencesFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String GITHUB_ISSUE = "https://github.com/physphil/UnitConverterUltimate/issues";

    public static PreferencesFragment newInstance() {
        return new PreferencesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        // Add listeners to preferences
        Preference unitRequest = findPreference("unit_request");
        unitRequest.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                requestUnit();
                return true;
            }
        });

        Preference rateApp = findPreference("rate_app");
        rateApp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                rateApp();
                return true;
            }
        });

        Preference openIssue = findPreference("open_issue");
        openIssue.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                openIssue();
                return true;
            }
        });

        Preference viewSource = findPreference("view_source");
        viewSource.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                viewSource();
                return true;
            }
        });

        Preference donate = findPreference("donate");
        if (BuildConfig.FLAVOR.equals(UnitConverterApplication.BUILD_FLAVOUR_GOOGLE)) {
            donate.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    startActivity(IntentFactory.getDonateIntent(getActivity()));
                    return true;
                }
            });
        }
        else {
            ((PreferenceCategory) findPreference("other")).removePreference(donate);
        }

        final ListPreference language = (ListPreference) findPreference("language");
        sortLanguageOptions(language);
    }

    @Override
    public void onResume() {
        super.onResume();
        Preferences.getInstance(getActivity()).getPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        Preferences.getInstance(getActivity()).getPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    private void requestUnit() {
        try {
            startActivity(IntentFactory.getRequestUnitIntent(getString(R.string.request_unit)));
        }
        catch (ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), R.string.toast_error_no_email_app, Toast.LENGTH_SHORT).show();
        }
    }

    private void rateApp() {
        try {
            startActivity(IntentFactory.getOpenPlayStoreIntent(getActivity().getPackageName()));
        }
        catch (ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), R.string.toast_error_google_play, Toast.LENGTH_SHORT).show();
        }
    }

    private void viewSource() {
        try {
            startActivity(IntentFactory.getOpenUrlIntent(IntentFactory.GITHUB_REPO));
        }
        catch (ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), R.string.toast_error_no_browser, Toast.LENGTH_SHORT).show();
        }
    }

    private void openIssue() {
        try {
            startActivity(IntentFactory.getOpenUrlIntent(GITHUB_ISSUE));
        }
        catch (ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), R.string.toast_error_no_browser, Toast.LENGTH_SHORT).show();
        }
    }

    private void sortLanguageOptions(final ListPreference preference) {
        // Sort language options so they're always alphabetical, no matter what language the user has chosen
        final Language[] languages = Language.values();
        Arrays.sort(languages, new Comparator<Language>() {
            @Override
            public int compare(Language lang1, Language lang2) {
                // Always put DEFAULT at top of list, then sort the rest alphabetically
                if (lang1 == Language.DEFAULT) {
                    return Integer.MIN_VALUE;
                }
                else if (lang2 == Language.DEFAULT) {
                    return Integer.MAX_VALUE;
                }
                else {
                    return getString(lang1.getDisplayStringId()).compareTo(getString(lang2.getDisplayStringId()));
                }
            }
        });

        // Create CharSequence[] arrays from the sorted list of Languages to supply to ListPreference
        final int size = languages.length;
        final CharSequence[] entries = new CharSequence[size];
        final CharSequence[] entryValues = new CharSequence[size];
        for (int i = 0; i < size; i++) {
            entries[i] = getString(languages[i].getDisplayStringId());
            entryValues[i] = languages[i].getId();
        }

        preference.setEntries(entries);
        preference.setEntryValues(entryValues);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(Preferences.PREFS_THEME)) {
            // Theme change, restart all open activities and reload with new theme
            getActivity().finish();
        }
    }
}
