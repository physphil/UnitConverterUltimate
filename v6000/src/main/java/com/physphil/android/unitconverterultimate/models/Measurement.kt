package com.physphil.android.unitconverterultimate.models

import java.math.BigDecimal

data class Measurement<T : StandardUnit> (
    val value: BigDecimal,
    val unit: T
) {
    fun to(unit: T): Measurement<T> = copy(
        value = this.value * this.unit.toStandard * unit.fromStandard
    )
}
