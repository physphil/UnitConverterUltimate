package com.physphil.android.unitconverterultimate.conversion

import com.google.common.truth.Truth.assertThat
import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.Mass
import com.physphil.android.unitconverterultimate.models.Temperature
import org.junit.Before
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class ConversionRepositoryTest {

    private lateinit var repo: ConversionRepository

    @Before
    fun setup() {
        repo = ConversionRepository()
    }

    @Test
    fun `test area conversion`() {
        val value = BigDecimal("5.5")
        val from = Area.SqMetre
        val to = Area.SqYard
        val result = repo.convert(value, from, to).setScale(2, RoundingMode.UP)
        assertThat(result).isEqualToIgnoringScale(6.58.toBigDecimal())
    }

    @Test
    fun `mass conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Mass.Kilogram, Mass.Pound).round(10)).isEqualTo(BigDecimal("12.1254244202"))
        assertThat(repo.convert(value, Mass.Pound, Mass.Gram).round(10)).isEqualTo(BigDecimal("2494.7580350000"))
        assertThat(repo.convert(value, Mass.Gram, Mass.Milligram).round(0)).isEqualTo(BigDecimal("5500"))
        assertThat(repo.convert(value, Mass.Milligram, Mass.Ounce).round(10)).isEqualTo(BigDecimal("0.0001940068"))
        assertThat(repo.convert(value, Mass.Ounce, Mass.Grain).round(2)).isEqualTo(BigDecimal("2406.25"))
        assertThat(repo.convert(value, Mass.Grain, Mass.Stone).round(10)).isEqualTo(BigDecimal("0.0000561224"))
        assertThat(repo.convert(value, Mass.Stone, Mass.MetricTon).round(10)).isEqualTo(BigDecimal("0.0349266125"))
        assertThat(repo.convert(value, Mass.MetricTon, Mass.ShortTon).round(10)).isEqualTo(BigDecimal("6.0627122101"))
        assertThat(repo.convert(value, Mass.ShortTon, Mass.LongTon).round(10)).isEqualTo(BigDecimal("4.9107142857"))
        assertThat(repo.convert(value, Mass.LongTon, Mass.Kilogram).round(10)).isEqualTo(BigDecimal("5588.2579984000"))
    }

    @Test
    fun `to celsius conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Temperature.Celsius, Temperature.Celsius)).isEqualTo(value)
        assertThat(repo.convert(value, Temperature.Fahrenheit, Temperature.Celsius).round(4)).isEqualTo(BigDecimal("-14.7222"))
        assertThat(repo.convert(value, Temperature.Kelvin, Temperature.Celsius)).isEqualTo(BigDecimal("-267.65"))
        assertThat(repo.convert(value, Temperature.Rankine, Temperature.Celsius).round(4)).isEqualTo(BigDecimal("-270.0944"))
        assertThat(repo.convert(value, Temperature.Delisle, Temperature.Celsius).round(4)).isEqualTo(BigDecimal("96.3333"))
        assertThat(repo.convert(value, Temperature.Newton, Temperature.Celsius).round(4)).isEqualTo(BigDecimal("16.6667"))
        assertThat(repo.convert(value, Temperature.Reaumur, Temperature.Celsius).round(4)).isEqualTo(BigDecimal("6.8750"))
        assertThat(repo.convert(value, Temperature.Romer, Temperature.Celsius).round(4)).isEqualTo(BigDecimal("-3.8095"))
    }

    @Test
    fun `to fahrenheit conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Temperature.Celsius, Temperature.Fahrenheit).round(4)).isEqualTo(BigDecimal("41.9000"))
        assertThat(repo.convert(value, Temperature.Fahrenheit, Temperature.Fahrenheit)).isEqualTo(value)
        assertThat(repo.convert(value, Temperature.Kelvin, Temperature.Fahrenheit).round(4)).isEqualTo(BigDecimal("-449.7700"))
        assertThat(repo.convert(value, Temperature.Rankine, Temperature.Fahrenheit).round(4)).isEqualTo(BigDecimal("-454.1700"))
        assertThat(repo.convert(value, Temperature.Delisle, Temperature.Fahrenheit).round(4)).isEqualTo(BigDecimal("205.4000"))
        assertThat(repo.convert(value, Temperature.Newton, Temperature.Fahrenheit).round(4)).isEqualTo(BigDecimal("62.0000"))
        assertThat(repo.convert(value, Temperature.Reaumur, Temperature.Fahrenheit).round(4)).isEqualTo(BigDecimal("44.3750"))
        assertThat(repo.convert(value, Temperature.Romer, Temperature.Fahrenheit).round(4)).isEqualTo(BigDecimal("25.1429"))
    }

    @Test
    fun `to kelvin conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Temperature.Celsius, Temperature.Kelvin).round(4)).isEqualTo(BigDecimal("278.6500"))
        assertThat(repo.convert(value, Temperature.Fahrenheit, Temperature.Kelvin).round(4)).isEqualTo(BigDecimal("258.4278"))
        assertThat(repo.convert(value, Temperature.Kelvin, Temperature.Kelvin)).isEqualTo(value)
        assertThat(repo.convert(value, Temperature.Rankine, Temperature.Kelvin).round(4)).isEqualTo(BigDecimal("3.0556"))
        assertThat(repo.convert(value, Temperature.Delisle, Temperature.Kelvin).round(4)).isEqualTo(BigDecimal("369.4833"))
        assertThat(repo.convert(value, Temperature.Newton, Temperature.Kelvin).round(4)).isEqualTo(BigDecimal("289.8167"))
        assertThat(repo.convert(value, Temperature.Reaumur, Temperature.Kelvin).round(4)).isEqualTo(BigDecimal("280.0250"))
        assertThat(repo.convert(value, Temperature.Romer, Temperature.Kelvin).round(4)).isEqualTo(BigDecimal("269.3405"))
    }

    @Test
    fun `to rankine conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Temperature.Celsius, Temperature.Rankine).round(4)).isEqualTo(BigDecimal("501.5700"))
        assertThat(repo.convert(value, Temperature.Fahrenheit, Temperature.Rankine).round(4)).isEqualTo(BigDecimal("465.1700"))
        assertThat(repo.convert(value, Temperature.Kelvin, Temperature.Rankine).round(4)).isEqualTo(BigDecimal("9.9000"))
        assertThat(repo.convert(value, Temperature.Rankine, Temperature.Rankine)).isEqualTo(value)
        assertThat(repo.convert(value, Temperature.Delisle, Temperature.Rankine).round(4)).isEqualTo(BigDecimal("665.0700"))
        assertThat(repo.convert(value, Temperature.Newton, Temperature.Rankine).round(4)).isEqualTo(BigDecimal("521.6700"))
        assertThat(repo.convert(value, Temperature.Reaumur, Temperature.Rankine).round(4)).isEqualTo(BigDecimal("504.0450"))
        assertThat(repo.convert(value, Temperature.Romer, Temperature.Rankine).round(4)).isEqualTo(BigDecimal("484.8129"))
    }

    @Test
    fun `to delisle conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Temperature.Celsius, Temperature.Delisle).round(4)).isEqualTo(BigDecimal("141.7500"))
        assertThat(repo.convert(value, Temperature.Fahrenheit, Temperature.Delisle).round(4)).isEqualTo(BigDecimal("172.0833"))
        assertThat(repo.convert(value, Temperature.Kelvin, Temperature.Delisle).round(4)).isEqualTo(BigDecimal("551.4750"))
        assertThat(repo.convert(value, Temperature.Rankine, Temperature.Delisle).round(4)).isEqualTo(BigDecimal("555.1417"))
        assertThat(repo.convert(value, Temperature.Delisle, Temperature.Delisle)).isEqualTo(value)
        assertThat(repo.convert(value, Temperature.Newton, Temperature.Delisle).round(4)).isEqualTo(BigDecimal("125.0000"))
        assertThat(repo.convert(value, Temperature.Reaumur, Temperature.Delisle).round(4)).isEqualTo(BigDecimal("139.6875"))
        assertThat(repo.convert(value, Temperature.Romer, Temperature.Delisle).round(4)).isEqualTo(BigDecimal("155.7143"))
    }

    @Test
    fun `to newton conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Temperature.Celsius, Temperature.Newton).round(4)).isEqualTo(BigDecimal("1.8150"))
        assertThat(repo.convert(value, Temperature.Fahrenheit, Temperature.Newton).round(4)).isEqualTo(BigDecimal("-4.8583"))
        assertThat(repo.convert(value, Temperature.Kelvin, Temperature.Newton).round(4)).isEqualTo(BigDecimal("-88.3245"))
        assertThat(repo.convert(value, Temperature.Rankine, Temperature.Newton).round(4)).isEqualTo(BigDecimal("-89.1312"))
        assertThat(repo.convert(value, Temperature.Delisle, Temperature.Newton).round(4)).isEqualTo(BigDecimal("31.7900"))
        assertThat(repo.convert(value, Temperature.Newton, Temperature.Newton)).isEqualTo(value)
        assertThat(repo.convert(value, Temperature.Reaumur, Temperature.Newton).round(4)).isEqualTo(BigDecimal("2.2688"))
        assertThat(repo.convert(value, Temperature.Romer, Temperature.Newton).round(4)).isEqualTo(BigDecimal("-1.2571"))
    }

    @Test
    fun `to reaumur conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Temperature.Celsius, Temperature.Reaumur).round(4)).isEqualTo(BigDecimal("4.4000"))
        assertThat(repo.convert(value, Temperature.Fahrenheit, Temperature.Reaumur).round(4)).isEqualTo(BigDecimal("-11.7778"))
        assertThat(repo.convert(value, Temperature.Kelvin, Temperature.Reaumur).round(4)).isEqualTo(BigDecimal("-214.1200"))
        assertThat(repo.convert(value, Temperature.Rankine, Temperature.Reaumur).round(4)).isEqualTo(BigDecimal("-216.0756"))
        assertThat(repo.convert(value, Temperature.Delisle, Temperature.Reaumur).round(4)).isEqualTo(BigDecimal("77.0667"))
        assertThat(repo.convert(value, Temperature.Newton, Temperature.Reaumur).round(4)).isEqualTo(BigDecimal("13.3333"))
        assertThat(repo.convert(value, Temperature.Reaumur, Temperature.Reaumur)).isEqualTo(value)
        assertThat(repo.convert(value, Temperature.Romer, Temperature.Reaumur).round(4)).isEqualTo(BigDecimal("-3.0476"))
    }

    @Test
    fun `to romer conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Temperature.Celsius, Temperature.Romer).round(4)).isEqualTo(BigDecimal("10.3875"))
        assertThat(repo.convert(value, Temperature.Fahrenheit, Temperature.Romer).round(4)).isEqualTo(BigDecimal("-0.2292"))
        assertThat(repo.convert(value, Temperature.Kelvin, Temperature.Romer).round(4)).isEqualTo(BigDecimal("-133.0163"))
        assertThat(repo.convert(value, Temperature.Rankine, Temperature.Romer).round(4)).isEqualTo(BigDecimal("-134.2996"))
        assertThat(repo.convert(value, Temperature.Delisle, Temperature.Romer).round(4)).isEqualTo(BigDecimal("58.0750"))
        assertThat(repo.convert(value, Temperature.Newton, Temperature.Romer).round(4)).isEqualTo(BigDecimal("16.2500"))
        assertThat(repo.convert(value, Temperature.Reaumur, Temperature.Romer).round(4)).isEqualTo(BigDecimal("11.1094"))
        assertThat(repo.convert(value, Temperature.Romer, Temperature.Romer)).isEqualTo(value)
    }

    private fun BigDecimal.round(scale: Int): BigDecimal = this.setScale(scale, RoundingMode.HALF_UP)
}