package com.physphil.android.unitconverterultimate.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.snackbar.Snackbar
import com.physphil.android.unitconverterultimate.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        viewModel.init(this)

        findPreference<Preference>("pref_rate_app")?.handleClick { viewModel.onPreferenceClicked(it.key) }
        findPreference<Preference>("pref_open_issue")?.handleClick { viewModel.onPreferenceClicked(it.key) }
        findPreference<Preference>("pref_view_source")?.handleClick { viewModel.onPreferenceClicked(it.key) }
        findPreference<Preference>("pref_privacy_policy")?.handleClick { viewModel.onPreferenceClicked(it.key) }
    }

    private fun SettingsViewModel.init(lifecycleOwner: LifecycleOwner) {
        openUrlEvent.observe(lifecycleOwner, Observer {
            it.getIfNotHandled()?.let { url ->
                openUrl(url)
            }
        })
    }

    private fun openUrl(url: String) {
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
    }

    private fun Preference.handleClick(action: (Preference) -> Unit) {
        setOnPreferenceClickListener {
            action(this)
            true
        }
    }
}