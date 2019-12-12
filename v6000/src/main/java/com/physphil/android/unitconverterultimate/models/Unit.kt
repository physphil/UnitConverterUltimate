package com.physphil.android.unitconverterultimate.models

import com.physphil.android.unitconverterultimate.R

sealed class Unit {
    abstract val displayStringResId: Int
}

sealed class Area(override val displayStringResId: Int) : Unit() {
    object SqKilometre : Area(R.string.sq_kilometre)
    object SqMetre : Area(R.string.sq_metre)
    object SqCentimetre : Area(R.string.sq_centimetre)
    object Hectare : Area(R.string.hectare)
    object SqMile : Area(R.string.sq_mile)
    object SqYard : Area(R.string.sq_yard)
    object SqFoot : Area(R.string.sq_foot)
    object SqInch : Area(R.string.sq_inch)
    object Acre : Area(R.string.acre)

    companion object {
        val all: List<Area> = listOf(
            SqKilometre, SqMetre, SqCentimetre, Hectare, SqMile, SqYard, SqFoot, SqInch, Acre
        )
    }
}

sealed class DigitalStorage(override val displayStringResId: Int) : Unit() {
    object Bit : DigitalStorage(R.string.bit)
    object Byte : DigitalStorage(R.string.Byte)
    object Kilobit : DigitalStorage(R.string.kilobit)
    object Kilobyte : DigitalStorage(R.string.kilobyte)
    object Megabit : DigitalStorage(R.string.megabit)
    object Megabyte : DigitalStorage(R.string.megabyte)
    object Gigabit : DigitalStorage(R.string.gigabit)
    object Gigabyte : DigitalStorage(R.string.gigabyte)
    object Terabit : DigitalStorage(R.string.terabit)
    object Terabyte : DigitalStorage(R.string.terabyte)

    companion object {
        val all: List<DigitalStorage> = listOf(
            Bit, Byte, Kilobit, Kilobyte, Megabit, Megabyte, Gigabit, Gigabyte, Terabit, Terabyte
        )
    }
}

sealed class Energy(override val displayStringResId: Int) : Unit() {
    object Joule : Energy(R.string.joule)
    object Kilojoule : Energy(R.string.kilojoule)
    object Calorie : Energy(R.string.calorie)
    object Kilocalorie : Energy(R.string.kilocalorie)
    object Btu : Energy(R.string.btu)
    object FtLbF : Energy(R.string.ft_lbF)
    object InLbF : Energy(R.string.in_lbF)
    object KilowattHour : Energy(R.string.kilowatt_hour)

    companion object {
        val all: List<Energy> = listOf(
            Joule, Kilojoule, Calorie, Kilocalorie, Btu, FtLbF, InLbF, KilowattHour
        )
    }
}

sealed class Length(override val displayStringResId: Int) : Unit() {
    object Kilometre : Length(R.string.kilometre)
    object Mile : Length(R.string.mile)
    object Metre : Length(R.string.metre)
    object Centimetre : Length(R.string.centimetre)
    object Millimetre : Length(R.string.millimetre)
    object Micrometre : Length(R.string.micrometre)
    object Nanometre : Length(R.string.nanometre)
    object Yard : Length(R.string.yard)
    object Feet : Length(R.string.feet)
    object Inch : Length(R.string.inch)
    object NauticalMile : Length(R.string.nautical_mile)
    object Furlong : Length(R.string.furlong)
    object LightYear : Length(R.string.light_year)

    companion object {
        val all: List<Length> = listOf(
            Kilometre, Mile, Metre, Centimetre, Millimetre, Micrometre, Nanometre, Yard, Feet, Inch, NauticalMile, Furlong, LightYear
        )
    }
}

sealed class Mass(override val displayStringResId: Int) : Unit() {
    object Kilogram : Mass(R.string.kilogram)
    object Pound : Mass(R.string.pound)
    object Gram : Mass(R.string.gram)
    object Milligram : Mass(R.string.milligram)
    object Ounce : Mass(R.string.ounce)
    object Grain : Mass(R.string.grain)
    object Stone : Mass(R.string.stone)
    object MetricTon : Mass(R.string.metric_ton)
    object ShortTon : Mass(R.string.short_ton)
    object LongTon : Mass(R.string.long_ton)

    companion object {
        val all: List<Mass> = listOf(
            Kilogram, Pound, Gram, Milligram, Ounce, Grain, Stone, MetricTon, ShortTon, LongTon
        )
    }
}

sealed class Power(override val displayStringResId: Int) : Unit() {
    object Watt : Power(R.string.watt)
    object Kilowatt : Power(R.string.kilowatt)
    object Megawatt : Power(R.string.megawatt)
    object Horsepower : Power(R.string.hp)
    object HorsepowerUk : Power(R.string.hp_uk)
    object FtLbFS : Power(R.string.ft_lbf_s)
    object CaloriePerSecond : Power(R.string.calorie_s)
    object BtuPerSecond : Power(R.string.btu_s)
    object Kva : Power(R.string.kva)

    companion object {
        val all: List<Power> = listOf(
            Watt, Kilowatt, Megawatt, Horsepower, HorsepowerUk, FtLbFS, CaloriePerSecond, BtuPerSecond, Kva
        )
    }
}

sealed class Pressure(override val displayStringResId: Int) : Unit() {
    object Megapascal : Pressure(R.string.megapascal)
    object Kilopascal : Pressure(R.string.kilopascal)
    object Pascal : Pressure(R.string.pascal)
    object Bar : Pressure(R.string.bar)
    object Psi : Pressure(R.string.psi)
    object Psf : Pressure(R.string.psf)
    object Atmosphere : Pressure(R.string.atmosphere)
    object TechnicalAtmosphere : Pressure(R.string.technical_atmosphere)
    object MmHg : Pressure(R.string.mmhg)
    object Torr : Pressure(R.string.torr)

    companion object {
        val all: List<Pressure> = listOf(
            Megapascal, Kilopascal, Pascal, Bar, Psi, Psf, Atmosphere, TechnicalAtmosphere, MmHg, Torr
        )
    }
}

sealed class Temperature(override val displayStringResId: Int) : Unit() {
    object Celsius : Temperature(R.string.celsius)
    object Fahrenheit : Temperature(R.string.fahrenheit)
    object Kelvin : Temperature(R.string.kelvin)
    object Rankine : Temperature(R.string.rankine)
    object Delisle : Temperature(R.string.delisle)
    object Newton : Temperature(R.string.newton)
    object Reaumur : Temperature(R.string.reaumur)
    object Romer : Temperature(R.string.romer)

    companion object {
        val all: List<Temperature> = listOf(
            Celsius, Fahrenheit, Kelvin, Rankine, Delisle, Newton, Reaumur, Romer
        )
    }
}