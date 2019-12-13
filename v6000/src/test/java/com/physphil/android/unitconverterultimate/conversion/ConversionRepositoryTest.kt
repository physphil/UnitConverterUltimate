package com.physphil.android.unitconverterultimate.conversion

import com.google.common.truth.Truth.assertThat
import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.DigitalStorage
import com.physphil.android.unitconverterultimate.models.Energy
import com.physphil.android.unitconverterultimate.models.Fuel
import com.physphil.android.unitconverterultimate.models.Length
import com.physphil.android.unitconverterultimate.models.Mass
import com.physphil.android.unitconverterultimate.models.Power
import com.physphil.android.unitconverterultimate.models.Pressure
import com.physphil.android.unitconverterultimate.models.Speed
import com.physphil.android.unitconverterultimate.models.Temperature
import com.physphil.android.unitconverterultimate.models.Time
import com.physphil.android.unitconverterultimate.models.Torque
import com.physphil.android.unitconverterultimate.models.Unit
import com.physphil.android.unitconverterultimate.models.Volume
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
        assertConversion(value, Area.SqKilometre, Area.SqMetre, "5500000.0")
        assertConversion(value, Area.SqMetre, Area.SqCentimetre, "55000.0")
        assertConversion(value, Area.SqCentimetre, Area.Hectare, "0.000000055")
        assertConversion(value, Area.Hectare, Area.SqMile, "0.0212356187")
        assertConversion(value, Area.SqMile, Area.SqYard, "17036800.0000000001")
        assertConversion(value, Area.SqYard, Area.SqFoot, "49.5")
        assertConversion(value, Area.SqFoot, Area.SqInch, "792")
        assertConversion(value, Area.SqInch, Area.Acre, "0.0000008768")
        assertConversion(value, Area.Acre, Area.SqKilometre, "0.0222577103")
    }

    @Test
    fun `digital storage conversions`() {
        val value = BigDecimal("4.0")
        assertConversion(value, DigitalStorage.Byte, DigitalStorage.Bit, "32.0")
        assertConversion(value, DigitalStorage.Kilobit, DigitalStorage.Byte, "512.0")
        assertConversion(value, DigitalStorage.Kilobyte, DigitalStorage.Kilobit, "32.0")
        assertConversion(value, DigitalStorage.Megabit, DigitalStorage.Kilobyte, "512.0")
        assertConversion(value, DigitalStorage.Megabyte, DigitalStorage.Megabit, "32.0")
        assertConversion(value, DigitalStorage.Gigabit, DigitalStorage.Megabyte, "512.0")
        assertConversion(value, DigitalStorage.Gigabyte, DigitalStorage.Gigabit, "32.0")
        assertConversion(value, DigitalStorage.Terabit, DigitalStorage.Gigabyte, "512.0")
        assertConversion(value, DigitalStorage.Terabit, DigitalStorage.Terabyte, "0.5")
        assertConversion(value, DigitalStorage.Bit, DigitalStorage.Byte, "0.5")
        assertConversion(value, DigitalStorage.Gigabyte, DigitalStorage.Megabyte, "4096.0")
    }

    @Test
    fun `energy conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Energy.Joule, Energy.Kilojoule, "0.0055")
        assertConversion(value, Energy.Kilojoule, Energy.Calorie, "1314.5315487572")
        assertConversion(value, Energy.Calorie, Energy.Kilocalorie, "0.0055")
        assertConversion(value, Energy.Kilocalorie, Energy.Btu, "21.8111675727")
        assertConversion(value, Energy.Btu, Energy.FtLbF, "4279.9309435089")
        assertConversion(value, Energy.FtLbF, Energy.InLbF, "66.0000000161")
        assertConversion(BigDecimal("5555555.0"), Energy.InLbF, Energy.KilowattHour, "0.1743592866")
        assertConversion(value, Energy.KilowattHour, Energy.Joule, "19800000.0")
    }

    @Test
    fun `fuel consumption conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Fuel.MilesPerGallonUs, Fuel.MilesPerGallonUk, "6.6052245905")
        assertConversion(value, Fuel.MilesPerGallonUk, Fuel.LitresPer100k, "51.3601699525")
        assertConversion(value, Fuel.LitresPer100k, Fuel.KilometresPerLitre, "18.1818180813")
        assertConversion(value, Fuel.KilometresPerLitre, Fuel.MilesPerLitre, "3.4175415522")
        assertConversion(BigDecimal("0.0"), Fuel.LitresPer100k, Fuel.MilesPerGallonUk, BigDecimal.ZERO)
        assertConversion(BigDecimal("0.0"), Fuel.MilesPerGallonUk, Fuel.LitresPer100k, BigDecimal.ZERO)
    }

    @Test
    fun `length conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Length.Kilometre, Length.Mile, "3.4175415573")
        assertConversion(value, Length.Mile, Length.Metre, "8851.3920")
        assertConversion(value, Length.Metre, Length.Centimetre, "550.0")
        assertConversion(value, Length.Centimetre, Length.Millimetre, "55.0")
        assertConversion(value, Length.Millimetre, Length.Micrometre, "5500.0")
        assertConversion(value, Length.Micrometre, Length.Nanometre, "5500.0")
        assertConversion(BigDecimal("5558"), Length.Nanometre, Length.Yard, "0.0000060783")
        assertConversion(value, Length.Yard, Length.Feet, "16.5")
        assertConversion(value, Length.Feet, Length.Inch, "66.0")
        assertConversion(value, Length.Inch, Length.NauticalMile, "0.0000754320")
        assertConversion(value, Length.NauticalMile, Length.Furlong, "50.6342957130")
        assertConversion(BigDecimal(123456789), Length.Furlong, Length.LightYear, "0.0000026251")
        assertConversion(value, Length.LightYear, Length.Kilometre, "52034017599194.4")
    }

    @Test
    fun `mass conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Mass.Kilogram, Mass.Pound, "12.1254244202")
        assertConversion(value, Mass.Pound, Mass.Gram, "2494.7580350000")
        assertConversion(value, Mass.Gram, Mass.Milligram, "5500")
        assertConversion(value, Mass.Milligram, Mass.Ounce, "0.0001940068")
        assertConversion(value, Mass.Ounce, Mass.Grain, "2406.25")
        assertConversion(value, Mass.Grain, Mass.Stone, "0.0000561224")
        assertConversion(value, Mass.Stone, Mass.MetricTon, "0.0349266125")
        assertConversion(value, Mass.MetricTon, Mass.ShortTon, "6.0627122101")
        assertConversion(value, Mass.ShortTon, Mass.LongTon, "4.9107142857")
        assertConversion(value, Mass.LongTon, Mass.Kilogram, "5588.2579984000")
    }

    @Test
    fun `power conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Power.Watt, Power.Kilowatt, "0.0055")
        assertConversion(value, Power.Kilowatt, Power.Megawatt, "0.0055")
        assertConversion(value, Power.Megawatt, Power.Horsepower, "7477.9188951715")
        assertConversion(value, Power.Horsepower, Power.HorsepowerUk, "5.4247603884")
        assertConversion(value, Power.HorsepowerUk, Power.FtLbFS, "3025.0")
        assertConversion(value, Power.FtLbFS, Power.CaloriePerSecond, "1.7810735444")
        assertConversion(value, Power.CaloriePerSecond, Power.BtuPerSecond, "0.0218257640")
        assertConversion(value, Power.BtuPerSecond, Power.Kva, "5.8028071894")
        assertConversion(value, Power.Kva, Power.Watt, "5500.0")
    }

    @Test
    fun `pressure conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Pressure.Megapascal, Pressure.Kilopascal, "5500.0")
        assertConversion(value, Pressure.Kilopascal, Pressure.Pascal, "5500.0")
        assertConversion(value, Pressure.Pascal, Pressure.Bar, "0.000055")
        assertConversion(value, Pressure.Bar, Pressure.Psi, "79.7707557516")
        assertConversion(value, Pressure.Psi, Pressure.Psf, "792.0")
        assertConversion(value, Pressure.Psf, Pressure.Atmosphere, "0.0025989778")
        assertConversion(value, Pressure.Atmosphere, Pressure.MmHg, "4179.9994044909")
        assertConversion(value, Pressure.MmHg, Pressure.Torr, "5.5000007836")
        assertConversion(value, Pressure.Torr, Pressure.TechnicalAtmosphere, "0.0074773039")
        assertConversion(value, Pressure.TechnicalAtmosphere, Pressure.Megapascal, "0.5393657500")
    }

    @Test
    fun `speed conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Speed.KilometresPerHour, Speed.MilesPerHour, "3.4175415573")
        assertConversion(value, Speed.MilesPerHour, Speed.MetresPerSecond, "2.45872")
        assertConversion(value, Speed.MetresPerSecond, Speed.FeetPerSecond, "18.0446194226")
        assertConversion(value, Speed.FeetPerSecond, Speed.Knot, "3.2586609071")
        assertConversion(value, Speed.Knot, Speed.KilometresPerHour, "10.1860")
    }

    @Test
    fun `to celsius conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Temperature.Celsius, Temperature.Celsius, value)
        assertConversion(value, Temperature.Fahrenheit, Temperature.Celsius, "-14.7222", 4)
        assertConversion(value, Temperature.Kelvin, Temperature.Celsius, "-267.65", 4)
        assertConversion(value, Temperature.Rankine, Temperature.Celsius, "-270.0944", 4)
        assertConversion(value, Temperature.Delisle, Temperature.Celsius, "96.3333", 4)
        assertConversion(value, Temperature.Newton, Temperature.Celsius, "16.6667", 4)
        assertConversion(value, Temperature.Reaumur, Temperature.Celsius, "6.8750", 4)
        assertConversion(value, Temperature.Romer, Temperature.Celsius, "-3.8095", 4)
    }

    @Test
    fun `to fahrenheit conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Temperature.Celsius, Temperature.Fahrenheit, "41.9000", 4)
        assertConversion(value, Temperature.Fahrenheit, Temperature.Fahrenheit, value)
        assertConversion(value, Temperature.Kelvin, Temperature.Fahrenheit, "-449.7700", 4)
        assertConversion(value, Temperature.Rankine, Temperature.Fahrenheit, "-454.1700", 4)
        assertConversion(value, Temperature.Delisle, Temperature.Fahrenheit, "205.4000", 4)
        assertConversion(value, Temperature.Newton, Temperature.Fahrenheit, "62.0000", 4)
        assertConversion(value, Temperature.Reaumur, Temperature.Fahrenheit, "44.3750", 4)
        assertConversion(value, Temperature.Romer, Temperature.Fahrenheit, "25.1429", 4)
    }

    @Test
    fun `to kelvin conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Temperature.Celsius, Temperature.Kelvin, "278.6500", 4)
        assertConversion(value, Temperature.Fahrenheit, Temperature.Kelvin, "258.4278", 4)
        assertConversion(value, Temperature.Kelvin, Temperature.Kelvin, value)
        assertConversion(value, Temperature.Rankine, Temperature.Kelvin, "3.0556", 4)
        assertConversion(value, Temperature.Delisle, Temperature.Kelvin, "369.4833", 4)
        assertConversion(value, Temperature.Newton, Temperature.Kelvin, "289.8167", 4)
        assertConversion(value, Temperature.Reaumur, Temperature.Kelvin, "280.0250", 4)
        assertConversion(value, Temperature.Romer, Temperature.Kelvin, "269.3405", 4)
    }

    @Test
    fun `to rankine conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Temperature.Celsius, Temperature.Rankine, "501.5700", 4)
        assertConversion(value, Temperature.Fahrenheit, Temperature.Rankine, "465.1700", 4)
        assertConversion(value, Temperature.Kelvin, Temperature.Rankine, "9.9000", 4)
        assertConversion(value, Temperature.Rankine, Temperature.Rankine, value)
        assertConversion(value, Temperature.Delisle, Temperature.Rankine, "665.0700", 4)
        assertConversion(value, Temperature.Newton, Temperature.Rankine, "521.6700", 4)
        assertConversion(value, Temperature.Reaumur, Temperature.Rankine, "504.0450", 4)
        assertConversion(value, Temperature.Romer, Temperature.Rankine, "484.8129", 4)
    }

    @Test
    fun `to delisle conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Temperature.Celsius, Temperature.Delisle, "141.7500", 4)
        assertConversion(value, Temperature.Fahrenheit, Temperature.Delisle, "172.0833", 4)
        assertConversion(value, Temperature.Kelvin, Temperature.Delisle, "551.4750", 4)
        assertConversion(value, Temperature.Rankine, Temperature.Delisle, "555.1417", 4)
        assertConversion(value, Temperature.Delisle, Temperature.Delisle, value)
        assertConversion(value, Temperature.Newton, Temperature.Delisle, "125.0000", 4)
        assertConversion(value, Temperature.Reaumur, Temperature.Delisle, "139.6875", 4)
        assertConversion(value, Temperature.Romer, Temperature.Delisle, "155.7143", 4)
    }

    @Test
    fun `to newton conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Temperature.Celsius, Temperature.Newton, "1.8150", 4)
        assertConversion(value, Temperature.Fahrenheit, Temperature.Newton, "-4.8583", 4)
        assertConversion(value, Temperature.Kelvin, Temperature.Newton, "-88.3245", 4)
        assertConversion(value, Temperature.Rankine, Temperature.Newton, "-89.1312", 4)
        assertConversion(value, Temperature.Delisle, Temperature.Newton, "31.7900", 4)
        assertConversion(value, Temperature.Newton, Temperature.Newton, value)
        assertConversion(value, Temperature.Reaumur, Temperature.Newton, "2.2688", 4)
        assertConversion(value, Temperature.Romer, Temperature.Newton, "-1.2571", 4)
    }

    @Test
    fun `to reaumur conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Temperature.Celsius, Temperature.Reaumur, "4.4000", 4)
        assertConversion(value, Temperature.Fahrenheit, Temperature.Reaumur, "-11.7778", 4)
        assertConversion(value, Temperature.Kelvin, Temperature.Reaumur, "-214.1200", 4)
        assertConversion(value, Temperature.Rankine, Temperature.Reaumur, "-216.0756", 4)
        assertConversion(value, Temperature.Delisle, Temperature.Reaumur, "77.0667", 4)
        assertConversion(value, Temperature.Newton, Temperature.Reaumur, "13.3333", 4)
        assertConversion(value, Temperature.Reaumur, Temperature.Reaumur, value, 4)
        assertConversion(value, Temperature.Romer, Temperature.Reaumur, "-3.0476", 4)
    }

    @Test
    fun `to romer conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Temperature.Celsius, Temperature.Romer, "10.3875", 4)
        assertConversion(value, Temperature.Fahrenheit, Temperature.Romer, "-0.2292", 4)
        assertConversion(value, Temperature.Kelvin, Temperature.Romer, "-133.0163", 4)
        assertConversion(value, Temperature.Rankine, Temperature.Romer, "-134.2996", 4)
        assertConversion(value, Temperature.Delisle, Temperature.Romer, "58.0750", 4)
        assertConversion(value, Temperature.Newton, Temperature.Romer, "16.2500", 4)
        assertConversion(value, Temperature.Reaumur, Temperature.Romer, "11.1094", 4)
        assertConversion(value, Temperature.Romer, Temperature.Romer, value)
    }

    @Test
    fun `time conversions`() {
        val value = BigDecimal("5.0")
        assertConversion(value, Time.Year, Time.Month, "59.9999994")
        assertConversion(value, Time.Month, Time.Week, "21.7261904762")
        assertConversion(value, Time.Week, Time.Day, "35.0")
        assertConversion(value, Time.Day, Time.Hour, "120.0")
        assertConversion(value, Time.Hour, Time.Minute, "300.0")
        assertConversion(value, Time.Minute, Time.Second, "300.0")
        assertConversion(value, Time.Second, Time.Millisecond, "5000.0")
        assertConversion(value, Time.Millisecond, Time.Nanosecond, "5000000.0")
        assertConversion(value, Time.Nanosecond, Time.Millisecond, "0.000005")
        assertConversion(value, Time.Month, Time.Year, "0.4166666667")
    }

    @Test
    fun `torque conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Torque.NewtonMetres, Torque.FtLbF, "4.0565918220")
        assertConversion(value, Torque.FtLbF, Torque.InLbF, "66.0000000161")
        assertConversion(value, Torque.InLbF, Torque.NewtonMetres, "0.6214165597")
    }

    @Test
    fun `volume conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(value, Volume.Teaspoon, Volume.Tablespoon, "1.8333333334")
        assertConversion(value, Volume.Tablespoon, Volume.Cup, "0.34375")
        assertConversion(value, Volume.Cup, Volume.FluidOunce, "44.0")
        assertConversion(value, Volume.FluidOunce, Volume.FluidOunceUk, "5.7246350193")
        assertConversion(value, Volume.FluidOunceUk, Volume.Pint, "0.3302612295")
        assertConversion(value, Volume.Pint, Volume.PintUk, "4.5797080155")
        assertConversion(value, Volume.PintUk, Volume.Quart, "3.3026122951")
        assertConversion(value, Volume.Quart, Volume.QuartUk, "4.5797080155")
        assertConversion(value, Volume.QuartUk, Volume.Gallon, "1.6513061476")
        assertConversion(value, Volume.Gallon, Volume.GallonUk, "4.5797080155")
        assertConversion(value, Volume.GallonUk, Volume.Barrel, "0.2096896695")
        assertConversion(value, Volume.Barrel, Volume.BarrelUk, "4.0072445135")
        assertConversion(value, Volume.BarrelUk, Volume.Millilitre, "900125.82")
        assertConversion(value, Volume.Millilitre, Volume.Litre, "0.0055")
        assertConversion(value, Volume.Litre, Volume.CubicCentimetre, "5500")
        assertConversion(value, Volume.CubicCentimetre, Volume.CubicMetre, "0.0000055")
        assertConversion(value, Volume.CubicMetre, Volume.CubicInch, "335630.5925210276")
        assertConversion(value, Volume.CubicInch, Volume.CubicFoot, "0.0031828704")
        assertConversion(value, Volume.CubicFoot, Volume.CubicYard, "0.2037037007")
        assertConversion(value, Volume.CubicYard, Volume.Teaspoon, "853138.2983125043")
    }

    private fun assertConversion(
        value: BigDecimal,
        initial: Unit,
        final: Unit,
        result: BigDecimal,
        scale: Int = 10
    ) {
        assertThat(repo.convert(value, initial, final).round(scale)).isEqualTo(result.round(scale))
    }

    private fun assertConversion(
        value: BigDecimal,
        initial: Unit,
        final: Unit,
        result: String,
        scale: Int = 10
    ) {
        assertConversion(value, initial, final, BigDecimal(result), scale)
    }

    private fun BigDecimal.round(scale: Int): BigDecimal =
        this.setScale(scale, RoundingMode.HALF_UP)
}