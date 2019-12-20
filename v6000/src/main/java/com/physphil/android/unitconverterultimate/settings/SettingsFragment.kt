package com.physphil.android.unitconverterultimate.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.snackbar.Snackbar
import com.physphil.android.unitconverterultimate.R

private const val URL_RATE_APP = "market://details?id=com.physphil.android.unitconverterultimate"
private const val URL_GITHUB_REPO = "https://github.com/physphil/UnitConverterUltimate"
private const val URL_GITHUB_ISSUE = "https://github.com/physphil/UnitConverterUltimate/issues"
private const val URL_PRIVACY_POLICY = "https://privacypolicies.com/privacy/view/f7a41d67f1b0081f249c2ff0a3123136"

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        findPreference<Preference>("pref_rate_app")?.openUrl(URL_RATE_APP)
        findPreference<Preference>("pref_open_issue")?.openUrl(URL_GITHUB_ISSUE)
        findPreference<Preference>("pref_view_source")?.openUrl(URL_GITHUB_REPO)
        findPreference<Preference>("pref_privacy_policy")?.openUrl(URL_PRIVACY_POLICY)
    }

    private fun Preference.openUrl(url: String) {
        setOnPreferenceClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            try {
                startActivity(intent)
            } catch (ex: ActivityNotFoundException) {
                view?.let {
                    Snackbar.make(it, R.string.toast_error_no_browser, Snackbar.LENGTH_LONG).apply {
                        view.setBackgroundColor(ContextCompat.getColor(context, R.color.theme_red))
                    }.show()
                }
            }
            true
        }
    }
}