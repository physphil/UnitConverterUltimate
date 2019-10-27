package com.physphil.android.unitconverterultimate.models

enum class Donation(
    val productId: String
) {
    LARGE_DONATION("donate_large"),
    MEDIUM_DONATION("donate_medium"),
    SMALL_DONATION("donate_small"),
    TINY_DONATION("donate_very_small"),
    INSANE_DONATION("donate_insane");

    companion object {
        val allProductIds: List<String>
            get() = values().map {
                it.productId
            }
    }
}
