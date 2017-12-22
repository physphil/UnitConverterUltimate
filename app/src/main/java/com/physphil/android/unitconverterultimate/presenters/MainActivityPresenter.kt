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

package com.physphil.android.unitconverterultimate.presenters

import android.content.Context
import android.os.Build
import com.physphil.android.unitconverterultimate.Preferences
import com.physphil.android.unitconverterultimate.models.Language
import java.util.*

class MainActivityPresenter(private val view: MainActivityView, private val context: Context, private val prefs: Preferences) {

    fun onLanguageChanged() {
        // The language has already been set when user picks from dialog, restart app to take effect
        view.restartApp()
    }

    fun setLanguageToDisplay() {
        val language = prefs.language

        // Use default locale if not specified, otherwise use saved locale from preferences
        val locale = when (language) {
            Language.DEFAULT -> Locale.getDefault()
            Language.PORTUGUESE_BR -> Locale("pt", "BR")
            else -> Locale(language.id)
        }

        val resources = context.resources
        val configuration = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale)
        } else {
            configuration.locale = locale
        }
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}
