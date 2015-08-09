package com.physphil.android.unitconverterultimate.fragments;

import android.content.ActivityNotFoundException;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.Preferences;
import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.util.IntentFactory;

/**
 * Fragment to display preferences screen
 * Created by Phizz on 15-08-02.
 */
public class PreferencesFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener
{
    public static PreferencesFragment newInstance()
    {
        return new PreferencesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        // Add listeners to preferences
        Preference unitRequest = findPreference("unit_request");
        unitRequest.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
        {
            @Override
            public boolean onPreferenceClick(Preference preference)
            {
                requestUnit();
                return true;
            }
        });

        Preference rateApp = findPreference("rate_app");
        rateApp.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
        {
            @Override
            public boolean onPreferenceClick(Preference preference)
            {
                rateApp();
                return true;
            }
        });

        Preference viewSource = findPreference("view_source");
        viewSource.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
        {
            @Override
            public boolean onPreferenceClick(Preference preference)
            {
                viewSource();
                return true;
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Preferences.getInstance(getActivity()).getPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Preferences.getInstance(getActivity()).getPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    private void requestUnit()
    {
        try
        {
            startActivity(IntentFactory.getRequestUnitIntent(getString(R.string.request_unit)));
        }
        catch(ActivityNotFoundException ex)
        {
            Toast.makeText(getActivity(), R.string.toast_error_no_email_app, Toast.LENGTH_SHORT).show();
        }
    }

    private void rateApp()
    {
        try
        {
            startActivity(IntentFactory.getOpenPlayStoreIntent(getActivity().getPackageName()));
        }
        catch(ActivityNotFoundException ex)
        {
            Toast.makeText(getActivity(), R.string.toast_error_google_play, Toast.LENGTH_SHORT).show();
        }
    }

    private void viewSource()
    {
        try
        {
            startActivity(IntentFactory.getViewSourceIntent());
        }
        catch(ActivityNotFoundException ex)
        {
            Toast.makeText(getActivity(), R.string.toast_error_no_browser, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if (key.equals(Preferences.PREFS_THEME))
        {
            // Theme change, restart all open activities and reload with new theme
            getActivity().finish();
        }
    }
}
