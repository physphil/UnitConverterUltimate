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
import com.physphil.android.unitconverterultimate.models.Volume
import org.junit.Test
import java.math.BigDecimal
import java.math.RoundingMode

class ConverterTest {

    @Test
    fun `area conversion`() {
        val value = BigDecimal("5.5")
        assertConversion(Converter.convert(value, Area.SqKilometre, Area.SqMetre), "5500000.0")
        assertConversion(Converter.convert(value, Area.SqKilometre, Area.SqMetre), "5500000.0")
        assertConversion(Converter.convert(value, Area.SqMetre, Area.SqCentimetre), "55000.0")
        assertConversion(Converter.convert(value, Area.SqCentimetre, Area.Hectare), "0.000000055")
        assertConversion(Converter.convert(value, Area.Hectare, Area.SqMile), "0.0212356187")
        assertConversion(Converter.convert(value, Area.SqMile, Area.SqYard), "17036800.0000000001")
        assertConversion(Converter.convert(value, Area.SqYard, Area.SqFoot), "49.5")
        assertConversion(Converter.convert(value, Area.SqFoot, Area.SqInch), "792")
        assertConversion(Converter.convert(value, Area.SqInch, Area.Acre), "0.0000008768")
        assertConversion(Converter.convert(value, Area.Acre, Area.SqKilometre), "0.0222577103")
    }

    @Test
    fun `digital storage conversions`() {
        val value = BigDecimal("4.0")
        assertConversion(Converter.convert(value, DigitalStorage.Byte, DigitalStorage.Bit), "32.0")
        assertConversion(Converter.convert(value, DigitalStorage.Kilobit, DigitalStorage.Byte), "512.0")
        assertConversion(Converter.convert(value, DigitalStorage.Kilobyte, DigitalStorage.Kilobit), "32.0")
        assertConversion(Converter.convert(value, DigitalStorage.Megabit, DigitalStorage.Kilobyte), "512.0")
        assertConversion(Converter.convert(value, DigitalStorage.Megabyte, DigitalStorage.Megabit), "32.0")
        assertConversion(Converter.convert(value, DigitalStorage.Gigabit, DigitalStorage.Megabyte), "512.0")
        assertConversion(Converter.convert(value, DigitalStorage.Gigabyte, DigitalStorage.Gigabit), "32.0")
        assertConversion(Converter.convert(value, DigitalStorage.Terabit, DigitalStorage.Gigabyte), "512.0")
        assertConversion(Converter.convert(value, DigitalStorage.Terabit, DigitalStorage.Terabyte), "0.5")
        assertConversion(Converter.convert(value, DigitalStorage.Bit, DigitalStorage.Byte), "0.5")
        assertConversion(Converter.convert(value, DigitalStorage.Gigabyte, DigitalStorage.Megabyte), "4096.0")
    }

    @Test
    fun `energy conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(Converter.convert(value, Energy.Joule, Energy.Kilojoule), "0.0055")
        assertConversion(Converter.convert(value, Energy.Kilojoule, Energy.Calorie), "1314.5315487572")
        assertConversion(Converter.convert(value, Energy.Calorie, Energy.Kilocalorie), "0.0055")
        assertConversion(Converter.convert(value, Energy.Kilocalorie, Energy.Btu), "21.8111675727")
        assertConversion(Converter.convert(value, Energy.Btu, Energy.FtLbF), "4279.9309435089")
        assertConversion(Converter.convert(value, Energy.FtLbF, Energy.InLbF), "66.0000000161")
        assertConversion(Converter.convert(BigDecimal("5555555.0"), Energy.InLbF, Energy.KilowattHour), "0.1743592866")
        assertConversion(Converter.convert(value, Energy.KilowattHour, Energy.Joule), "19800000.0")
    }

    @Test
    fun `fuel consumption conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(FuelConsumptionConverter.convert(value, Fuel.MilesPerGallonUs, Fuel.MilesPerGallonUk), "6.6052245905")
        assertConversion(FuelConsumptionConverter.convert(value, Fuel.MilesPerGallonUk, Fuel.LitresPer100k), "51.3601699525")
        assertConversion(FuelConsumptionConverter.convert(value, Fuel.LitresPer100k, Fuel.KilometresPerLitre), "18.1818180813")
        assertConversion(FuelConsumptionConverter.convert(value, Fuel.KilometresPerLitre, Fuel.MilesPerLitre), "3.4175415522")
        assertConversion(FuelConsumptionConverter.convert(BigDecimal("0.0"), Fuel.LitresPer100k, Fuel.MilesPerGallonUk), BigDecimal.ZERO)
        assertConversion(FuelConsumptionConverter.convert(BigDecimal("0.0"), Fuel.MilesPerGallonUk, Fuel.LitresPer100k), BigDecimal.ZERO)
    }

    @Test
    fun `length conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(Converter.convert(value, Length.Kilometre, Length.Mile), "3.4175415573")
        assertConversion(Converter.convert(value, Length.Mile, Length.Metre), "8851.3920")
        assertConversion(Converter.convert(value, Length.Metre, Length.Centimetre), "550.0")
        assertConversion(Converter.convert(value, Length.Centimetre, Length.Millimetre), "55.0")
        assertConversion(Converter.convert(value, Length.Millimetre, Length.Micrometre), "5500.0")
        assertConversion(Converter.convert(value, Length.Micrometre, Length.Nanometre), "5500.0")
        assertConversion(Converter.convert(BigDecimal("5558"), Length.Nanometre, Length.Yard), "0.0000060783")
        assertConversion(Converter.convert(value, Length.Yard, Length.Feet), "16.5")
        assertConversion(Converter.convert(value, Length.Feet, Length.Inch), "66.0")
        assertConversion(Converter.convert(value, Length.Inch, Length.NauticalMile), "0.0000754320")
        assertConversion(Converter.convert(value, Length.NauticalMile, Length.Furlong), "50.6342957130")
        assertConversion(Converter.convert(BigDecimal(123456789), Length.Furlong, Length.LightYear), "0.0000026251")
        assertConversion(Converter.convert(value, Length.LightYear, Length.Kilometre), "52034017599194.4")
    }

    @Test
    fun `mass conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(Converter.convert(value, Mass.Kilogram, Mass.Pound), "12.1254244202")
        assertConversion(Converter.convert(value, Mass.Pound, Mass.Gram), "2494.7580350000")
        assertConversion(Converter.convert(value, Mass.Gram, Mass.Milligram), "5500")
        assertConversion(Converter.convert(value, Mass.Milligram, Mass.Ounce), "0.0001940068")
        assertConversion(Converter.convert(value, Mass.Ounce, Mass.Grain), "2406.25")
        assertConversion(Converter.convert(value, Mass.Grain, Mass.Stone), "0.0000561224")
        assertConversion(Converter.convert(value, Mass.Stone, Mass.MetricTon), "0.0349266125")
        assertConversion(Converter.convert(value, Mass.MetricTon, Mass.ShortTon), "6.0627122101")
        assertConversion(Converter.convert(value, Mass.ShortTon, Mass.LongTon), "4.9107142857")
        assertConversion(Converter.convert(value, Mass.LongTon, Mass.Kilogram), "5588.2579984000")
    }

    @Test
    fun `power conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(Converter.convert(value, Power.Watt, Power.Kilowatt), "0.0055")
        assertConversion(Converter.convert(value, Power.Kilowatt, Power.Megawatt), "0.0055")
        assertConversion(Converter.convert(value, Power.Megawatt, Power.Horsepower), "7477.9188951715")
        assertConversion(Converter.convert(value, Power.Horsepower, Power.HorsepowerUk), "5.4247603884")
        assertConversion(Converter.convert(value, Power.HorsepowerUk, Power.FtLbFS), "3025.0")
        assertConversion(Converter.convert(value, Power.FtLbFS, Power.CaloriePerSecond), "1.7810735444")
        assertConversion(Converter.convert(value, Power.CaloriePerSecond, Power.BtuPerSecond), "0.0218257640")
        assertConversion(Converter.convert(value, Power.BtuPerSecond, Power.Kva), "5.8028071894")
        assertConversion(Converter.convert(value, Power.Kva, Power.Watt), "5500.0")
    }

    @Test
    fun `pressure conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(Converter.convert(value, Pressure.Megapascal, Pressure.Kilopascal), "5500.0")
        assertConversion(Converter.convert(value, Pressure.Kilopascal, Pressure.Pascal), "5500.0")
        assertConversion(Converter.convert(value, Pressure.Pascal, Pressure.Bar), "0.000055")
        assertConversion(Converter.convert(value, Pressure.Bar, Pressure.Psi), "79.7707557516")
        assertConversion(Converter.convert(value, Pressure.Psi, Pressure.Psf), "792.0")
        assertConversion(Converter.convert(value, Pressure.Psf, Pressure.Atmosphere), "0.0025989778")
        assertConversion(Converter.convert(value, Pressure.Atmosphere, Pressure.MmHg), "4179.9994044909")
        assertConversion(Converter.convert(value, Pressure.MmHg, Pressure.Torr), "5.5000007836")
        assertConversion(Converter.convert(value, Pressure.Torr, Pressure.TechnicalAtmosphere), "0.0074773039")
        assertConversion(Converter.convert(value, Pressure.TechnicalAtmosphere, Pressure.Megapascal), "0.5393657500")
    }

    @Test
    fun `speed conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(Converter.convert(value, Speed.KilometresPerHour, Speed.MilesPerHour), "3.4175415573")
        assertConversion(Converter.convert(value, Speed.MilesPerHour, Speed.MetresPerSecond), "2.45872")
        assertConversion(Converter.convert(value, Speed.MetresPerSecond, Speed.FeetPerSecond), "18.0446194226")
        assertConversion(Converter.convert(value, Speed.FeetPerSecond, Speed.Knot), "3.2586609071")
        assertConversion(Converter.convert(value, Speed.Knot, Speed.KilometresPerHour), "10.1860")
    }

    @Test
    fun `to celsius conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(TemperatureConverter.convert(value, Temperature.Celsius, Temperature.Celsius), value)
        assertConversion(TemperatureConverter.convert(value, Temperature.Fahrenheit, Temperature.Celsius), "-14.7222", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Kelvin, Temperature.Celsius), "-267.65", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Rankine, Temperature.Celsius), "-270.0944", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Delisle, Temperature.Celsius), "96.3333", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Newton, Temperature.Celsius), "16.6667", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Reaumur, Temperature.Celsius), "6.8750", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Romer, Temperature.Celsius), "-3.8095", 4)
    }

    @Test
    fun `to fahrenheit conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(TemperatureConverter.convert(value, Temperature.Celsius, Temperature.Fahrenheit), "41.9000", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Fahrenheit, Temperature.Fahrenheit), value)
        assertConversion(TemperatureConverter.convert(value, Temperature.Kelvin, Temperature.Fahrenheit), "-449.7700", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Rankine, Temperature.Fahrenheit), "-454.1700", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Delisle, Temperature.Fahrenheit), "205.4000", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Newton, Temperature.Fahrenheit), "62.0000", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Reaumur, Temperature.Fahrenheit), "44.3750", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Romer, Temperature.Fahrenheit), "25.1429", 4)
    }

    @Test
    fun `to kelvin conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(TemperatureConverter.convert(value, Temperature.Celsius, Temperature.Kelvin), "278.6500", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Fahrenheit, Temperature.Kelvin), "258.4278", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Kelvin, Temperature.Kelvin), value)
        assertConversion(TemperatureConverter.convert(value, Temperature.Rankine, Temperature.Kelvin), "3.0556", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Delisle, Temperature.Kelvin), "369.4833", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Newton, Temperature.Kelvin), "289.8167", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Reaumur, Temperature.Kelvin), "280.0250", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Romer, Temperature.Kelvin), "269.3405", 4)
    }

    @Test
    fun `to rankine conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(TemperatureConverter.convert(value, Temperature.Celsius, Temperature.Rankine), "501.5700", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Fahrenheit, Temperature.Rankine), "465.1700", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Kelvin, Temperature.Rankine), "9.9000", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Rankine, Temperature.Rankine), value)
        assertConversion(TemperatureConverter.convert(value, Temperature.Delisle, Temperature.Rankine), "665.0700", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Newton, Temperature.Rankine), "521.6700", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Reaumur, Temperature.Rankine), "504.0450", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Romer, Temperature.Rankine), "484.8129", 4)
    }

    @Test
    fun `to delisle conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(TemperatureConverter.convert(value, Temperature.Celsius, Temperature.Delisle), "141.7500", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Fahrenheit, Temperature.Delisle), "172.0833", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Kelvin, Temperature.Delisle), "551.4750", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Rankine, Temperature.Delisle), "555.1417", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Delisle, Temperature.Delisle), value)
        assertConversion(TemperatureConverter.convert(value, Temperature.Newton, Temperature.Delisle), "125.0000", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Reaumur, Temperature.Delisle), "139.6875", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Romer, Temperature.Delisle), "155.7143", 4)
    }

    @Test
    fun `to newton conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(TemperatureConverter.convert(value, Temperature.Celsius, Temperature.Newton), "1.8150", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Fahrenheit, Temperature.Newton), "-4.8583", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Kelvin, Temperature.Newton), "-88.3245", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Rankine, Temperature.Newton), "-89.1312", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Delisle, Temperature.Newton), "31.7900", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Newton, Temperature.Newton), value)
        assertConversion(TemperatureConverter.convert(value, Temperature.Reaumur, Temperature.Newton), "2.2688", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Romer, Temperature.Newton), "-1.2571", 4)
    }

    @Test
    fun `to reaumur conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(TemperatureConverter.convert(value, Temperature.Celsius, Temperature.Reaumur), "4.4000", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Fahrenheit, Temperature.Reaumur), "-11.7778", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Kelvin, Temperature.Reaumur), "-214.1200", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Rankine, Temperature.Reaumur), "-216.0756", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Delisle, Temperature.Reaumur), "77.0667", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Newton, Temperature.Reaumur), "13.3333", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Reaumur, Temperature.Reaumur), value, 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Romer, Temperature.Reaumur), "-3.0476", 4)
    }

    @Test
    fun `to romer conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(TemperatureConverter.convert(value, Temperature.Celsius, Temperature.Romer), "10.3875", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Fahrenheit, Temperature.Romer), "-0.2292", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Kelvin, Temperature.Romer), "-133.0163", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Rankine, Temperature.Romer), "-134.2996", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Delisle, Temperature.Romer), "58.0750", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Newton, Temperature.Romer), "16.2500", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Reaumur, Temperature.Romer), "11.1094", 4)
        assertConversion(TemperatureConverter.convert(value, Temperature.Romer, Temperature.Romer), value)
    }

    @Test
    fun `time conversions`() {
        val value = BigDecimal("5.0")
        assertConversion(Converter.convert(value, Time.Year, Time.Month), "59.9999994")
        assertConversion(Converter.convert(value, Time.Month, Time.Week), "21.7261904762")
        assertConversion(Converter.convert(value, Time.Week, Time.Day), "35.0")
        assertConversion(Converter.convert(value, Time.Day, Time.Hour), "120.0")
        assertConversion(Converter.convert(value, Time.Hour, Time.Minute), "300.0")
        assertConversion(Converter.convert(value, Time.Minute, Time.Second), "300.0")
        assertConversion(Converter.convert(value, Time.Second, Time.Millisecond), "5000.0")
        assertConversion(Converter.convert(value, Time.Millisecond, Time.Nanosecond), "5000000.0")
        assertConversion(Converter.convert(value, Time.Nanosecond, Time.Millisecond), "0.000005")
        assertConversion(Converter.convert(value, Time.Month, Time.Year), "0.4166666667")
    }

    @Test
    fun `torque conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(Converter.convert(value, Torque.NewtonMetres, Torque.FtLbF), "4.0565918220")
        assertConversion(Converter.convert(value, Torque.FtLbF, Torque.InLbF), "66.0000000161")
        assertConversion(Converter.convert(value, Torque.InLbF, Torque.NewtonMetres), "0.6214165597")
    }

    @Test
    fun `volume conversions`() {
        val value = BigDecimal("5.5")
        assertConversion(Converter.convert(value, Volume.Teaspoon, Volume.Tablespoon), "1.8333333334")
        assertConversion(Converter.convert(value, Volume.Tablespoon, Volume.Cup), "0.34375")
        assertConversion(Converter.convert(value, Volume.Cup, Volume.FluidOunce), "44.0")
        assertConversion(Converter.convert(value, Volume.FluidOunce, Volume.FluidOunceUk), "5.7246350193")
        assertConversion(Converter.convert(value, Volume.FluidOunceUk, Volume.Pint), "0.3302612295")
        assertConversion(Converter.convert(value, Volume.Pint, Volume.PintUk), "4.5797080155")
        assertConversion(Converter.convert(value, Volume.PintUk, Volume.Quart), "3.3026122951")
        assertConversion(Converter.convert(value, Volume.Quart, Volume.QuartUk), "4.5797080155")
        assertConversion(Converter.convert(value, Volume.QuartUk, Volume.Gallon), "1.6513061476")
        assertConversion(Converter.convert(value, Volume.Gallon, Volume.GallonUk), "4.5797080155")
        assertConversion(Converter.convert(value, Volume.GallonUk, Volume.Barrel), "0.2096896695")
        assertConversion(Converter.convert(value, Volume.Barrel, Volume.BarrelUk), "4.0072445135")
        assertConversion(Converter.convert(value, Volume.BarrelUk, Volume.Millilitre), "900125.82")
        assertConversion(Converter.convert(value, Volume.Millilitre, Volume.Litre), "0.0055")
        assertConversion(Converter.convert(value, Volume.Litre, Volume.CubicCentimetre), "5500")
        assertConversion(Converter.convert(value, Volume.CubicCentimetre, Volume.CubicMetre), "0.0000055")
        assertConversion(Converter.convert(value, Volume.CubicMetre, Volume.CubicInch), "335630.5925210276")
        assertConversion(Converter.convert(value, Volume.CubicInch, Volume.CubicFoot), "0.0031828704")
        assertConversion(Converter.convert(value, Volume.CubicFoot, Volume.CubicYard), "0.2037037007")
        assertConversion(Converter.convert(value, Volume.CubicYard, Volume.Teaspoon), "853138.2983125043")
    }

    private fun assertConversion(actual: BigDecimal, expected: BigDecimal, scale: Int = 10) {
        assertThat(actual.round(scale)).isEqualTo(expected.round(scale))
    }

    private fun assertConversion(actual: BigDecimal, expected: String, scale: Int = 10) {
        assertConversion(actual, BigDecimal(expected), scale)
    }

    private fun BigDecimal.round(scale: Int): BigDecimal =
        this.setScale(scale, RoundingMode.HALF_UP)
}