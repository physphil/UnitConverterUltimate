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
            initial is Temperature && final is Temperature -> value.convertTemperature(initial, final)
            else -> throw IllegalArgumentException("The initial unit $initial and final unit $final are not of the same type, and cannot be converted.")
        }

    private fun BigDecimal.convertStandard(initial: StandardUnit, final: StandardUnit): BigDecimal {
        // Multiplier converts the value from the initial unit to a base unit, then to the final unit.
        val multiplier = initial.toStandard * final.fromStandard
        return this * multiplier
    }

    private fun BigDecimal.convertTemperature(initial: Temperature, final: Temperature): BigDecimal =
        when (final) {
            Temperature.Celsius -> this.toCelsius(initial)
            Temperature.Fahrenheit -> this.toFahrenheit(initial)
            Temperature.Kelvin -> TODO()
            Temperature.Rankine -> TODO()
            Temperature.Delisle -> TODO()
            Temperature.Newton -> TODO()
            Temperature.Reaumur -> TODO()
            Temperature.Romer -> TODO()
            Temperature.GasMark -> TODO()
        }

    private fun BigDecimal.toCelsius(initial: Temperature): BigDecimal =
        when (initial) {
            Temperature.Celsius -> this
            Temperature.Fahrenheit -> (this - 32) * 5 / 9
            Temperature.Kelvin -> this - 273.15
            Temperature.Rankine -> (this - 491.67) * 5 / 9
            Temperature.Delisle -> 100 - this * 2 / 3
            Temperature.Newton -> this * 100 / 33
            Temperature.Reaumur -> this * 5 / 4
            Temperature.Romer -> (this - 7.5) * 40 / 21
            Temperature.GasMark -> TODO()
        }

    private fun BigDecimal.toFahrenheit(initial: Temperature): BigDecimal =
        when (initial) {
            Temperature.Celsius -> this * 9 / 5 + 32
            Temperature.Fahrenheit -> this
            Temperature.Kelvin -> this * 9 / 5 - 459.67
            Temperature.Rankine -> this - 459.6
            Temperature.Delisle -> 212 - this * 6 / 5
            Temperature.Newton -> this * 60 / 11 + 32
            Temperature.Reaumur -> this * 9 / 4 + 32
            Temperature.Romer -> (this - 7.5) * 24 / 7 + 32
            Temperature.GasMark -> TODO()
        }
    
    private operator fun BigDecimal.plus(i: Int): BigDecimal = this + i.toBigDecimal()
    private operator fun BigDecimal.minus(i: Int): BigDecimal = this - i.toBigDecimal()
    private operator fun BigDecimal.minus(d: Double): BigDecimal = this - d.toBigDecimal()
    private operator fun BigDecimal.times(i: Int): BigDecimal = this * i.toBigDecimal()
    private operator fun BigDecimal.div(i: Int): BigDecimal = this / i.toBigDecimal()

    private operator fun Int.minus(bigDecimal: BigDecimal): BigDecimal = this.toBigDecimal() - bigDecimal
    private operator fun Int.times(bigDecimal: BigDecimal): BigDecimal = this.toBigDecimal() * bigDecimal
}
