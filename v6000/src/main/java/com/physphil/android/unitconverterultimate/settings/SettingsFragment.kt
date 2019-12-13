package com.physphil.android.unitconverterultimate.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.physphil.android.unitconverterultimate.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}