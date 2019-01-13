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

package com.physphil.android.unitconverterultimate.models

import com.physphil.android.unitconverterultimate.R

// ISO-639 Language codes
private const val LANG_DEFAULT = "def"
private const val LANG_CROATIAN = "hr"
private const val LANG_ENGLISH = "en"
private const val LANG_FARSI = "fa"
private const val LANG_FRENCH = "fr"
private const val LANG_GERMAN = "de"
private const val LANG_HUNGARIAN = "hu"
private const val LANG_ITALIAN = "it"
private const val LANG_JAPANESE = "ja"
private const val LANG_PORTUGUESE_BR = "pt_BR"
private const val LANG_RUSSIAN = "ru"
private const val LANG_SPANISH = "es"
private const val LANG_TURKISH = "tr"

/**
 * Represents a Language that a user can select to display the app in.
 * Copyright (c) 2017 Phil Shadlyn
 */
enum class Language(val id: String) {
    DEFAULT(LANG_DEFAULT),
    CROATIAN(LANG_CROATIAN),
    ENGLISH(LANG_ENGLISH),
    FARSI(LANG_FARSI),
    FRENCH(LANG_FRENCH),
    GERMAN(LANG_GERMAN),
    HUNGARIAN(LANG_HUNGARIAN),
    ITALIAN(LANG_ITALIAN),
    JAPANESE(LANG_JAPANESE),
    PORTUGUESE_BR(LANG_PORTUGUESE_BR),
    RUSSIAN(LANG_RUSSIAN),
    SPANISH(LANG_SPANISH),
    TURKISH(LANG_TURKISH);

    val displayStringId = when (id) {
        LANG_CROATIAN -> R.string.language_croatian
        LANG_ENGLISH -> R.string.language_english
        LANG_FARSI -> R.string.language_farsi
        LANG_FRENCH -> R.string.language_french
        LANG_GERMAN -> R.string.language_german
        LANG_HUNGARIAN -> R.string.language_hungarian
        LANG_ITALIAN -> R.string.language_italian
        LANG_JAPANESE -> R.string.language_japanese
        LANG_PORTUGUESE_BR -> R.string.language_portuguese_brazil
        LANG_RUSSIAN -> R.string.language_russian
        LANG_SPANISH -> R.string.language_spanish
        LANG_TURKISH -> R.string.language_turkish
        else -> R.string.language_default
    }

    companion object {
        /**
         * Get the Language corresponding to the given id.
         * @param id ISO-639 code for the Language value
         * @return the Language corresponding to the given id, or DEFAULT if the language id is not supported
         */
        @JvmStatic
        fun fromId(id: String) = values().firstOrNull { it.id == id } ?: DEFAULT
    }
}