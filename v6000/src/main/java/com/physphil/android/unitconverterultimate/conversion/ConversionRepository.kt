package com.physphil.android.unitconverterultimate.conversion

import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.StandardUnit
import com.physphil.android.unitconverterultimate.models.Temperature
import com.physphil.android.unitconverterultimate.models.Unit
import java.math.BigDecimal

class ConversionRepository {

    // TODO I don't like how this method signature is unsafe. convert(intial: Area, final: Area) would be preferred.
    fun convert(value: BigDecimal, initial: Unit, final: Unit): BigDecimal =
        when {
            initial is Area && final is Area -> value.convertStandard(initial, final)
            initial is Temperature && final is Temperature -> TODO()
            else -> throw IllegalArgumentException("The initial unit $initial and final unit $final are not of the same type, and cannot be converted.")
        }

    private fun BigDecimal.convertStandard(initial: StandardUnit, final: StandardUnit): BigDecimal {
        // Multiplier converts the value from the initial unit to a base unit, then to the final unit.
        val multiplier = initial.toStandard * final.fromStandard
        return this * multiplier
    }
}