package com.physphil.android.unitconverterultimate.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.physphil.android.unitconverterultimate.util.Event

private const val URL_RATE_APP = "market://details?id=com.physphil.android.unitconverterultimate"
private const val URL_GITHUB_REPO = "https://github.com/physphil/UnitConverterUltimate"
private const val URL_GITHUB_ISSUE = "https://github.com/physphil/UnitConverterUltimate/issues"
private const val URL_PRIVACY_POLICY = "https://privacypolicies.com/privacy/view/f7a41d67f1b0081f249c2ff0a3123136"

class SettingsViewModel : ViewModel() {

    private val _openUrlEvent = MutableLiveData<Event<String>>()
    val openUrlEvent: LiveData<Event<String>> = _openUrlEvent

    fun onPreferenceClicked(key: String) {
        when (key) {
            Keys.UNIT_REQUEST -> TODO("Still need to figure out how to accept unit requests")
            Keys.RATE_APP -> _openUrlEvent.postValue(Event(URL_RATE_APP))
            Keys.OPEN_ISSUE -> _openUrlEvent.postValue(Event(URL_GITHUB_ISSUE))
            Keys.VIEW_SOURCE -> _openUrlEvent.postValue(Event(URL_GITHUB_REPO))
            Keys.PRIVACY_POLICY -> _openUrlEvent.postValue(Event(URL_PRIVACY_POLICY))
        }
    }

    object Keys {
        const val NUMBER_DECIMALS = "pref_number_decimals"
        const val GROUP_SEPARATOR = "pref_group_separator"
        const val DECIMAL_SEPARATOR = "pref_decimal_separator"
        const val THEME = "pref_theme"
        const val LANGUAGE = "pref_language"
        const val DONATE = "pref_donate"
        const val ACKNOWLEDGEMENTS = "pref_acknowledgements"
        const val UNIT_REQUEST = "pref_unit_request"
        const val RATE_APP = "pref_rate_app"
        const val OPEN_ISSUE = "pref_open_issue"
        const val VIEW_SOURCE = "pref_view_source"
        const val PRIVACY_POLICY = "pref_privacy_policy"
    }
}