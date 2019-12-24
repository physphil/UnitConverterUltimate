package com.physphil.android.unitconverterultimate.language

import android.content.Context
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test

class LanguageRepositoryTest {

    @Test
    fun `sorted language list always has Default at top`() {
        val context: Context = mock {
            on { getString(any()) } doReturn "Language name"
        }
        val entries = LanguageRepository.getLanguageEntriesForLocale(context)
        assertThat(entries.entryValues.first()).isEqualTo(Language.Default.code)
    }
}