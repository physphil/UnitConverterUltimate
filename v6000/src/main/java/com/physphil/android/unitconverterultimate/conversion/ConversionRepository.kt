package com.physphil.android.unitconverterultimate.conversion

import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.StandardUnit
import com.physphil.android.unitconverterultimate.models.Temperature
import java.math.BigDecimal

class ConversionRepository {

    fun convert(value: BigDecimal, initial: Area, final: Area): BigDecimal = value.convert(initial, final)

    fun convert(value: BigDecimal, initial: Temperature, final: Temperature): BigDecimal {
        TODO("this is a stub")
    }

    private fun BigDecimal.convert(initial: StandardUnit, final: StandardUnit): BigDecimal {
        // Multiplier converts the value from the initial unit to a base unit, then to the final unit.
        val multiplier = initial.toStandard * final.fromStandard
        return this * multiplier
    }
}