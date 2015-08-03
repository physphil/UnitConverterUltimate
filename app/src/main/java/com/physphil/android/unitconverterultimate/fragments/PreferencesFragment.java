package com.physphil.android.unitconverterultimate.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.physphil.android.unitconverterultimate.R;

/**
 * Preference Fragment
 * Created by Phizz on 15-08-02.
 */
public class PreferencesFragment extends PreferenceFragment
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
    }
}
