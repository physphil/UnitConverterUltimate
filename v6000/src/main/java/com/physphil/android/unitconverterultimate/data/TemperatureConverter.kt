package com.physphil.android.unitconverterultimate.data

import com.physphil.android.unitconverterultimate.models.Temperature
import java.math.BigDecimal
import java.math.RoundingMode

object TemperatureConverter : Converter<Temperature> {

    override fun convert(value: BigDecimal, initial: Temperature, final: Temperature): BigDecimal =
        when (final) {
            Temperature.Celsius -> value.toCelsius(initial)
            Temperature.Fahrenheit -> value.toFahrenheit(initial)
            Temperature.Kelvin -> value.toKelvin(initial)
            Temperature.Rankine -> value.toRankine(initial)
            Temperature.Delisle -> value.toDelisle(initial)
            Temperature.Newton -> value.toNewton(initial)
            Temperature.Reaumur -> value.toReaumur(initial)
            Temperature.Romer -> value.toRomer(initial)
        }

    private fun BigDecimal.toCelsius(from: Temperature): BigDecimal =
        when (from) {
            Temperature.Celsius -> this
            Temperature.Fahrenheit -> (this - 32) * 5 / 9
            Temperature.Kelvin -> this - 273.15
            Temperature.Rankine -> (this - 491.67) * 5 / 9
            Temperature.Delisle -> 100 - this * 2 / 3
            Temperature.Newton -> this * 100 / 33
            Temperature.Reaumur -> this * 5 / 4
            Temperature.Romer -> (this - 7.5) * 40 / 21
        }

    private fun BigDecimal.toFahrenheit(from: Temperature): BigDecimal =
        when (from) {
            Temperature.Celsius -> this * 9 / 5 + 32
            Temperature.Fahrenheit -> this
            Temperature.Kelvin -> this * 9 / 5 - 459.67
            Temperature.Rankine -> this - 459.67
            Temperature.Delisle -> 212 - this * 6 / 5
            Temperature.Newton -> this * 60 / 11 + 32
            Temperature.Reaumur -> this * 9 / 4 + 32
            Temperature.Romer -> (this - 7.5) * 24 / 7 + 32
        }

    private fun BigDecimal.toKelvin(from: Temperature): BigDecimal =
        when (from) {
            Temperature.Celsius -> this + 273.15
            Temperature.Fahrenheit -> (this + 459.67) * 5 / 9
            Temperature.Kelvin -> this
            Temperature.Rankine -> this * 5 / 9
            Temperature.Delisle -> 373.15 - this * 2 / 3
            Temperature.Newton -> this * 100 / 33 + 273.15
            Temperature.Reaumur -> this * 5 / 4 + 273.15
            Temperature.Romer -> (this - 7.5) * 40 / 21 + 273.15
        }

    private fun BigDecimal.toRankine(from: Temperature): BigDecimal =
        when (from) {
            Temperature.Celsius -> (this + 273.15) * 9 / 5
            Temperature.Fahrenheit -> this + 459.67
            Temperature.Kelvin -> this * 9 / 5
            Temperature.Rankine -> this
            Temperature.Delisle -> 671.67 - this * 6 / 5
            Temperature.Newton -> this * 60 / 11 + 491.67
            Temperature.Reaumur -> this * 9 / 4 + 491.67
            Temperature.Romer -> (this - 7.5) * 24 / 7 + 491.67
        }

    private fun BigDecimal.toDelisle(from: Temperature): BigDecimal =
        when (from) {
            Temperature.Celsius -> (100 - this) * 1.5
            Temperature.Fahrenheit -> (212 - this) * 5 / 6
            Temperature.Kelvin -> (373.15 - this) * 1.5
            Temperature.Rankine -> (671.67 - this) * 5 / 6
            Temperature.Delisle -> this
            Temperature.Newton -> (33 - this) * 50 / 11
            Temperature.Reaumur -> (80 - this) * 1.875
            Temperature.Romer -> (60 - this) * 20 / 7
        }

    private fun BigDecimal.toNewton(from: Temperature): BigDecimal =
        when (from) {
            Temperature.Celsius -> this * 33 / 100
            Temperature.Fahrenheit -> (this - 32) * 11 / 60
            Temperature.Kelvin -> (this - 273.15) * 33 / 100
            Temperature.Rankine -> (this - 491.67) * 11 / 60
            Temperature.Delisle -> 33 - this * 11 / 50
            Temperature.Newton -> this
            Temperature.Reaumur -> this * 33 / 80
            Temperature.Romer -> (this - 7.5) * 22 / 35
        }

    private fun BigDecimal.toReaumur(from: Temperature): BigDecimal =
        when (from) {
            Temperature.Celsius -> this * 4 / 5
            Temperature.Fahrenheit -> (this - 32) * 4 / 9
            Temperature.Kelvin -> (this - 273.15) * 4 / 5
            Temperature.Rankine -> (this - 491.67) * 4 / 9
            Temperature.Delisle -> 80 - this * 8 / 15
            Temperature.Newton -> this * 80 / 33
            Temperature.Reaumur -> this
            Temperature.Romer -> (this - 7.5) * 32 / 21
        }

    private fun BigDecimal.toRomer(from: Temperature): BigDecimal =
        when (from) {
            Temperature.Celsius -> this * 21 / 40 + 7.5
            Temperature.Fahrenheit -> (this - 32) * 7 / 24 + 7.5
            Temperature.Kelvin -> (this - 273.15) * 21 / 40 + 7.5
            Temperature.Rankine -> (this - 491.67) * 7 / 24 + 7.5
            Temperature.Delisle -> 60 - this * 7 / 20
            Temperature.Newton -> this * 35 / 22 + 7.5
            Temperature.Reaumur -> this * 21 / 32 + 7.5
            Temperature.Romer -> this
        }

    private operator fun BigDecimal.plus(i: Int): BigDecimal = this + i.toBigDecimal()
    private operator fun BigDecimal.plus(d: Double): BigDecimal = this + d.toBigDecimal()
    private operator fun BigDecimal.minus(i: Int): BigDecimal = this - i.toBigDecimal()
    private operator fun BigDecimal.minus(d: Double): BigDecimal = this - d.toBigDecimal()
    private operator fun BigDecimal.times(i: Int): BigDecimal = this * i.toBigDecimal()
    private operator fun BigDecimal.times(d: Double): BigDecimal = this * d.toBigDecimal()
    private operator fun BigDecimal.div(i: Int): BigDecimal = this.divide(i.toBigDecimal(), SCALE_RESULT, RoundingMode.HALF_UP)

    private operator fun Int.minus(bigDecimal: BigDecimal): BigDecimal = this.toBigDecimal() - bigDecimal
    private operator fun Int.times(bigDecimal: BigDecimal): BigDecimal = this.toBigDecimal() * bigDecimal

    private operator fun Double.minus(bigDecimal: BigDecimal): BigDecimal = this.toBigDecimal() - bigDecimal
}