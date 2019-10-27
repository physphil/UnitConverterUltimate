package com.physphil.android.unitconverterultimate.models

data class Donation(
    val productId: String,
    val title: String,
    val description: String,
    val price: String,
    val currencyCode: String
) {
    companion object {
        const val LARGE_DONATION = "donate_large"
        const val MEDIUM_DONATION = "donate_medium"
        const val SMALL_DONATION = "donate_small"
        const val TINY_DONATION = "donate_very_small"
        const val INSANE_DONATION = "donate_insane"

        val all: List<String>
            get() = listOf(
                LARGE_DONATION,
                MEDIUM_DONATION,
                SMALL_DONATION,
                TINY_DONATION,
                INSANE_DONATION
            )
    }
}
