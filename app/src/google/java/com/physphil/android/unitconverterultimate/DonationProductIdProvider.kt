package com.physphil.android.unitconverterultimate

object DonationProductIdProvider {

    const val LARGE_DONATION = "donate_large"
    const val MEDIUM_DONATION = "donate_medium"
    const val SMALL_DONATION = "donate_small"
    const val TINY_DONATION = "donate_very_small"
    const val INSANE_DONATION = "donate_insane"

    val all: List<String>
        get() = listOf(
            TINY_DONATION,
            SMALL_DONATION,
            MEDIUM_DONATION,
            LARGE_DONATION,
            INSANE_DONATION
        )
}