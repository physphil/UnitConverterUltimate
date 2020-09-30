package com.physphil.android.unitconverterultimate.data

import com.physphil.android.unitconverterultimate.models.Fuel
import java.math.BigDecimal
import java.math.RoundingMode

object FuelConsumptionConverter : Converter<Fuel> {
    override fun convert(
        value: BigDecimal,
        initial: Fuel,
        final: Fuel
    ): BigDecimal = when {
        value.compareTo(BigDecimal.ZERO) == 0 -> BigDecimal.ZERO
        initial is Fuel.LitresPer100k -> fromLitresPer100k(value, final)
        final is Fuel.LitresPer100k -> toLitresPer100k(value, initial)
        else -> value * FuelConsumptionDataSource.getMultiplier(initial, final)
    }

    private fun fromLitresPer100k(value: BigDecimal, final: Fuel): BigDecimal {
        val toStandard = FuelConsumptionDataSource[Fuel.LitresPer100k].first
        val fromStandard = FuelConsumptionDataSource[final].second
        return toStandard.divide(value, SCALE_RESULT, RoundingMode.HALF_UP) * fromStandard
    }

    private fun toLitresPer100k(value: BigDecimal, initial: Fuel): BigDecimal {
        val toStandard = FuelConsumptionDataSource[initial].first
        val fromStandard = FuelConsumptionDataSource[Fuel.LitresPer100k].second
        return fromStandard.divide((value * toStandard), SCALE_RESULT, RoundingMode.HALF_UP)
    }
}