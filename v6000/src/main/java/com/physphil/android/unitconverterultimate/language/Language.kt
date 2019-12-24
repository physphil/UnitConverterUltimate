package com.physphil.android.unitconverterultimate.language

import android.content.Context
import androidx.annotation.StringRes
import com.physphil.android.unitconverterultimate.R

sealed class Language(
    val code: String,
    @StringRes
    val displayStringResId: Int
) {
    object Default : Language("def", R.string.language_default)
    object Croatian : Language("hr", R.string.language_croatian)
    object Dutch : Language("nl", R.string.language_dutch)
    object English : Language("en", R.string.language_english)
    object Farsi : Language("fa", R.string.language_farsi)
    object French : Language("fr", R.string.language_french)
    object German : Language("de", R.string.language_german)
    object Hungarian : Language("hu", R.string.language_hungarian)
    object Italian : Language("it", R.string.language_italian)
    object Japanese : Language("ja", R.string.language_japanese)
    object Norwegian : Language("nb", R.string.language_norwegian)
    object PortugueseBr : Language("pt_BR", R.string.language_portuguese_brazil)
    object Russian : Language("ru", R.string.language_russian)
    object Spanish : Language("es", R.string.language_spanish)
    object Turkish : Language("tr", R.string.language_turkish)
}

/**
 * A registry of all available [Language]s that the app has been translated into for a user to select.
 */
object LanguageRepository {
    private val all = listOf(
        Language.Default,
        Language.Croatian,
        Language.Dutch,
        Language.English,
        Language.Farsi,
        Language.French,
        Language.German,
        Language.Hungarian,
        Language.Italian,
        Language.Japanese,
        Language.Norwegian,
        Language.PortugueseBr,
        Language.Russian,
        Language.Spanish,
        Language.Turkish
    )

    operator fun get(code: String): Language? = all.firstOrNull { it.code == code }

    fun getLanguageEntriesForLocale(context: Context): LanguageEntries {
        // Sort alphabetically but ensure that 'Default' is at the top of the list
        val sorted = listOf(Language.Default) + all.subList(1, all.size).sortedBy {
            context.getString(it.displayStringResId)
        }
        return LanguageEntries(
            entries = sorted.map { context.getString(it.displayStringResId) }.toTypedArray(),
            entryValues = sorted.map { it.code }.toTypedArray()
        )
    }
}

/**
 * Represents the entries and entryValues for the Language preference dialog.
 */
class LanguageEntries(
    val entries: Array<String>,
    val entryValues: Array<String>
)