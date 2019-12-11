package com.physphil.android.unitconverterultimate.conversion

import com.physphil.android.unitconverterultimate.data.AreaDataSource
import com.physphil.android.unitconverterultimate.data.MassDataSource
import com.physphil.android.unitconverterultimate.data.TemperatureConverter
import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.ConversionType
import com.physphil.android.unitconverterultimate.models.Mass
import com.physphil.android.unitconverterultimate.models.Temperature
import com.physphil.android.unitconverterultimate.models.Unit
import java.math.BigDecimal

class ConversionRepository {

    fun unitsFor(type: ConversionType): List<Unit> =
        when (type) {
            ConversionType.AREA -> Area.all
            ConversionType.CURRENCY -> TODO()
            ConversionType.MASS -> Mass.all
            ConversionType.TEMPERATURE -> Temperature.all
        }

    fun convert(value: BigDecimal, initial: Unit, final: Unit): BigDecimal =
        when {
            initial is Area && final is Area -> convertArea(value, initial, final)
            initial is Mass && final is Mass -> convertMass(value, initial, final)
            initial is Temperature && final is Temperature -> TemperatureConverter.convert(value, initial, final)
            else -> throw IllegalArgumentException("The initial unit $initial and final unit $final are not of the same type, and cannot be converted.")
        }

    private fun convertArea(value: BigDecimal, initial: Area, final: Area): BigDecimal =
        when (initial) {
            final -> value
            else -> value * AreaDataSource.getMultiplier(initial, final)
        }

    private fun convertMass(value: BigDecimal, initial: Mass, final: Mass): BigDecimal =
        when (initial) {
            final -> value
            else -> value * MassDataSource.getMultiplier(initial, final)
        }
}
