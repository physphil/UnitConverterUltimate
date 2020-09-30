package com.physphil.android.unitconverterultimate.data

import com.physphil.android.unitconverterultimate.models.Unit
import java.math.BigDecimal

const val SCALE_RESULT = 15

interface Converter<T : Unit> {
    fun convert(value: BigDecimal, initial: T, final: T): BigDecimal
}