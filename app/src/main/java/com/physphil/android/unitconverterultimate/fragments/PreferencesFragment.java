package com.physphil.android.unitconverterultimate.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.Preferences;
import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.util.Constants;
import com.physphil.android.unitconverterultimate.util.Util;

/**
 * Preference Fragment
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
                Util.rateApp(getActivity());
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
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{Constants.EMAIL_ADDRESS});
            i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.request_unit));
            startActivity(i);
        }
        catch(ActivityNotFoundException ex)
        {
            Toast.makeText(getActivity(), R.string.toast_error_no_email_app, Toast.LENGTH_SHORT).show();
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
