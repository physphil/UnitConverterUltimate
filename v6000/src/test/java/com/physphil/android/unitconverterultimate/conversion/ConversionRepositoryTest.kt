package com.physphil.android.unitconverterultimate.conversion

import com.google.common.truth.Truth.assertThat
import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.DigitalStorage
import com.physphil.android.unitconverterultimate.models.Energy
import com.physphil.android.unitconverterultimate.models.Length
import com.physphil.android.unitconverterultimate.models.Mass
import com.physphil.android.unitconverterultimate.models.Power
import com.physphil.android.unitconverterultimate.models.Pressure
import com.physphil.android.unitconverterultimate.models.Speed
import com.physphil.android.unitconverterultimate.models.Temperature
import com.physphil.android.unitconverterultimate.models.Time
import com.physphil.android.unitconverterultimate.models.Torque
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
    fun `area conversion`() {
        val value = BigDecimal("5.5")
        val from = Area.SqMetre
        val to = Area.SqYard
        val result = repo.convert(value, from, to).setScale(2, RoundingMode.UP)
        assertThat(result).isEqualToIgnoringScale(6.58.toBigDecimal())
    }

    @Test
    fun `storage conversions`() {
        val value = BigDecimal("4.0")
        assertThat(repo.convert(value, DigitalStorage.Byte, DigitalStorage.Bit).round(1)).isEqualTo(BigDecimal("32.0"))
        assertThat(repo.convert(value, DigitalStorage.Kilobit, DigitalStorage.Byte).round(1)).isEqualTo(BigDecimal("512.0"))
        assertThat(repo.convert(value, DigitalStorage.Kilobyte, DigitalStorage.Kilobit).round(1)).isEqualTo(BigDecimal("32.0"))
        assertThat(repo.convert(value, DigitalStorage.Megabit, DigitalStorage.Kilobyte).round(1)).isEqualTo(BigDecimal("512.0"))
        assertThat(repo.convert(value, DigitalStorage.Megabyte, DigitalStorage.Megabit).round(1)).isEqualTo(BigDecimal("32.0"))
        assertThat(repo.convert(value, DigitalStorage.Gigabit, DigitalStorage.Megabyte).round(1)).isEqualTo(BigDecimal("512.0"))
        assertThat(repo.convert(value, DigitalStorage.Gigabyte, DigitalStorage.Gigabit).round(1)).isEqualTo(BigDecimal("32.0"))
        assertThat(repo.convert(value, DigitalStorage.Terabit, DigitalStorage.Gigabyte).round(1)).isEqualTo(BigDecimal("512.0"))
        assertThat(repo.convert(value, DigitalStorage.Terabit, DigitalStorage.Terabyte).round(1)).isEqualTo(BigDecimal("0.5"))
        assertThat(repo.convert(value, DigitalStorage.Bit, DigitalStorage.Byte).round(1)).isEqualTo(BigDecimal("0.5"))
        assertThat(repo.convert(value, DigitalStorage.Gigabyte, DigitalStorage.Megabyte).round(1)).isEqualTo(BigDecimal("4096.0"))
    }

    @Test
    fun `energy conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Energy.Joule, Energy.Kilojoule).round(4)).isEqualTo(BigDecimal("0.0055"))
        assertThat(repo.convert(value, Energy.Kilojoule, Energy.Calorie).round(4)).isEqualTo(BigDecimal("1314.5315"))
        assertThat(repo.convert(value, Energy.Calorie, Energy.Kilocalorie).round(4)).isEqualTo(BigDecimal("0.0055"))
        assertThat(repo.convert(value, Energy.Kilocalorie, Energy.Btu).round(4)).isEqualTo(BigDecimal("21.8112"))
        assertThat(repo.convert(value, Energy.Btu, Energy.FtLbF).round(4)).isEqualTo(BigDecimal("4279.9309"))
        assertThat(repo.convert(value, Energy.FtLbF, Energy.InLbF).round(10)).isEqualTo(BigDecimal("66.0000000161"))
        assertThat(repo.convert(BigDecimal("5555555.0"), Energy.InLbF, Energy.KilowattHour).round(4)).isEqualTo(BigDecimal("0.1744"))
        assertThat(repo.convert(value, Energy.KilowattHour, Energy.Joule).round(1)).isEqualTo(BigDecimal("19800000.0"))
    }

    @Test
    fun `length conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Length.Kilometre, Length.Mile).round(10)).isEqualTo(BigDecimal("3.4175415573"))
        assertThat(repo.convert(value, Length.Mile, Length.Metre).round(4)).isEqualTo(BigDecimal("8851.3920"))
        assertThat(repo.convert(value, Length.Metre, Length.Centimetre).round(1)).isEqualTo(BigDecimal("550.0"))
        assertThat(repo.convert(value, Length.Centimetre, Length.Millimetre).round(1)).isEqualTo(BigDecimal("55.0"))
        assertThat(repo.convert(value, Length.Millimetre, Length.Micrometre).round(1)).isEqualTo(BigDecimal("5500.0"))
        assertThat(repo.convert(value, Length.Micrometre, Length.Nanometre).round(1)).isEqualTo(BigDecimal("5500.0"))
        assertThat(repo.convert(BigDecimal("5558"), Length.Nanometre, Length.Yard).round(10)).isEqualTo(BigDecimal("0.0000060783"))
        assertThat(repo.convert(value, Length.Yard, Length.Feet).round(1)).isEqualTo(BigDecimal("16.5"))
        assertThat(repo.convert(value, Length.Feet, Length.Inch).round(1)).isEqualTo(BigDecimal("66.0"))
        assertThat(repo.convert(value, Length.Inch, Length.NauticalMile).round(10)).isEqualTo(BigDecimal("0.0000754320"))
        assertThat(repo.convert(value, Length.NauticalMile, Length.Furlong).round(10)).isEqualTo(BigDecimal("50.6342957130"))
        assertThat(repo.convert(BigDecimal(123456789), Length.Furlong, Length.LightYear).round(10)).isEqualTo(BigDecimal("0.0000026251"))
        assertThat(repo.convert(value, Length.LightYear, Length.Kilometre).round(1)).isEqualTo(BigDecimal("52034017599194.4"))
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
    fun `power conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Power.Watt, Power.Kilowatt).round(4)).isEqualTo(BigDecimal("0.0055"))
        assertThat(repo.convert(value, Power.Kilowatt, Power.Megawatt).round(4)).isEqualTo(BigDecimal("0.0055"))
        assertThat(repo.convert(value, Power.Megawatt, Power.Horsepower).round(10)).isEqualTo(BigDecimal("7477.9188951715"))
        assertThat(repo.convert(value, Power.Horsepower, Power.HorsepowerUk).round(10)).isEqualTo(BigDecimal("5.4247603884"))
        assertThat(repo.convert(value, Power.HorsepowerUk, Power.FtLbFS).round(1)).isEqualTo(BigDecimal("3025.0"))
        assertThat(repo.convert(value, Power.FtLbFS, Power.CaloriePerSecond).round(10)).isEqualTo(BigDecimal("1.7810735444"))
        assertThat(repo.convert(value, Power.CaloriePerSecond, Power.BtuPerSecond).round(10)).isEqualTo(BigDecimal("0.0218257640"))
        assertThat(repo.convert(value, Power.BtuPerSecond, Power.Kva).round(10)).isEqualTo(BigDecimal("5.8028071894"))
        assertThat(repo.convert(value, Power.Kva, Power.Watt).round(1)).isEqualTo(BigDecimal("5500.0"))
    }

    @Test
    fun `pressure conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Pressure.Megapascal, Pressure.Kilopascal).round(1)).isEqualTo(BigDecimal("5500.0"))
        assertThat(repo.convert(value, Pressure.Kilopascal, Pressure.Pascal).round(1)).isEqualTo(BigDecimal("5500.0"))
        assertThat(repo.convert(value, Pressure.Pascal, Pressure.Bar).round(6)).isEqualTo(BigDecimal("0.000055"))
        assertThat(repo.convert(value, Pressure.Bar, Pressure.Psi).round(10)).isEqualTo(BigDecimal("79.7707557516"))
        assertThat(repo.convert(value, Pressure.Psi, Pressure.Psf).round(1)).isEqualTo(BigDecimal("792.0"))
        assertThat(repo.convert(value, Pressure.Psf, Pressure.Atmosphere).round(10)).isEqualTo(BigDecimal("0.0025989778"))
        assertThat(repo.convert(value, Pressure.Atmosphere, Pressure.MmHg).round(10)).isEqualTo(BigDecimal("4179.9994044909"))
        assertThat(repo.convert(value, Pressure.MmHg, Pressure.Torr).round(10)).isEqualTo(BigDecimal("5.5000007836"))
        assertThat(repo.convert(value, Pressure.Torr, Pressure.TechnicalAtmosphere).round(10)).isEqualTo(BigDecimal("0.0074773039"))
        assertThat(repo.convert(value, Pressure.TechnicalAtmosphere, Pressure.Megapascal).round(10)).isEqualTo(BigDecimal("0.5393657500"))
    }

    @Test
    fun `speed conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Speed.KilometresPerHour, Speed.MilesPerHour).round(10)).isEqualTo(BigDecimal("3.4175415573"))
        assertThat(repo.convert(value, Speed.MilesPerHour, Speed.MetresPerSecond).round(5)).isEqualTo(BigDecimal("2.45872"))
        assertThat(repo.convert(value, Speed.MetresPerSecond, Speed.FeetPerSecond).round(10)).isEqualTo(BigDecimal("18.0446194226"))
        assertThat(repo.convert(value, Speed.FeetPerSecond, Speed.Knot).round(10)).isEqualTo(BigDecimal("3.2586609071"))
        assertThat(repo.convert(value, Speed.Knot, Speed.KilometresPerHour).round(4)).isEqualTo(BigDecimal("10.1860"))
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

    @Test
    fun `time conversions`() {
        val value = BigDecimal("5.0")
        assertThat(repo.convert(value, Time.Year, Time.Month).round(7)).isEqualTo(BigDecimal("59.9999994"))
        assertThat(repo.convert(value, Time.Month, Time.Week).round(10)).isEqualTo(BigDecimal("21.7261904762"))
        assertThat(repo.convert(value, Time.Week, Time.Day).round(1)).isEqualTo(BigDecimal("35.0"))
        assertThat(repo.convert(value, Time.Day, Time.Hour).round(1)).isEqualTo(BigDecimal("120.0"))
        assertThat(repo.convert(value, Time.Hour, Time.Minute).round(1)).isEqualTo(BigDecimal("300.0"))
        assertThat(repo.convert(value, Time.Minute, Time.Second).round(1)).isEqualTo(BigDecimal("300.0"))
        assertThat(repo.convert(value, Time.Second, Time.Millisecond).round(1)).isEqualTo(BigDecimal("5000.0"))
        assertThat(repo.convert(value, Time.Millisecond, Time.Nanosecond).round(1)).isEqualTo(BigDecimal("5000000.0"))
        assertThat(repo.convert(value, Time.Nanosecond, Time.Millisecond).round(6)).isEqualTo(BigDecimal("0.000005"))
        assertThat(repo.convert(value, Time.Month, Time.Year).round(10)).isEqualTo(BigDecimal("0.4166666667"))
    }

    @Test
    fun `torque conversions`() {
        val value = BigDecimal("5.5")
        assertThat(repo.convert(value, Torque.NewtonMetres, Torque.FtLbF).round(10)).isEqualTo(BigDecimal("4.0565918220"))
        assertThat(repo.convert(value, Torque.FtLbF, Torque.InLbF).round(10)).isEqualTo(BigDecimal("66.0000000161"))
        assertThat(repo.convert(value, Torque.InLbF, Torque.NewtonMetres).round(10)).isEqualTo(BigDecimal("0.6214165597"))
    }

    private fun BigDecimal.round(scale: Int): BigDecimal = this.setScale(scale, RoundingMode.HALF_UP)
}