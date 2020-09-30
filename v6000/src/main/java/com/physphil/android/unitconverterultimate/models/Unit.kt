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

sealed class Fuel(override val displayStringResId: Int) : Unit() {
    object MilesPerGallonUs : Fuel(R.string.mpg_us)
    object MilesPerGallonUk : Fuel(R.string.mpg_uk)
    object LitresPer100k : Fuel(R.string.l_100k)
    object KilometresPerLitre : Fuel(R.string.km_l)
    object MilesPerLitre : Fuel(R.string.miles_l)

    companion object {
        val all: List<Fuel> = listOf(
            MilesPerGallonUs, MilesPerGallonUk, LitresPer100k, KilometresPerLitre, MilesPerLitre
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

sealed class Speed(override val displayStringResId: Int) : Unit() {
    object KilometresPerHour : Speed(R.string.km_h)
    object MilesPerHour : Speed(R.string.mph)
    object MetresPerSecond : Speed(R.string.m_s)
    object FeetPerSecond : Speed(R.string.fps)
    object Knot : Speed(R.string.knot)

    companion object {
        val all: List<Speed> = listOf(
            KilometresPerHour, MilesPerHour, MetresPerSecond, FeetPerSecond, Knot
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

sealed class Time(override val displayStringResId: Int) : Unit() {
    object Year : Time(R.string.year)
    object Month : Time(R.string.month)
    object Week : Time(R.string.week)
    object Day : Time(R.string.day)
    object Hour : Time(R.string.hour)
    object Minute : Time(R.string.minute)
    object Second : Time(R.string.second)
    object Millisecond : Time(R.string.millisecond)
    object Nanosecond : Time(R.string.nanosecond)

    companion object {
        val all: List<Time> = listOf(
            Year, Month, Week, Day, Hour, Minute, Second, Millisecond, Nanosecond
        )
    }
}

sealed class Torque(override val displayStringResId: Int) : Unit() {
    object NewtonMetres : Torque(R.string.n_m)
    object FtLbF : Torque(R.string.ft_lbF)
    object InLbF : Torque(R.string.in_lbF)

    companion object {
        val all: List<Torque> = listOf(
            NewtonMetres, FtLbF, InLbF
        )
    }
}

sealed class Volume(override val displayStringResId: Int) : Unit() {
    object Teaspoon : Volume(R.string.teaspoon)
    object Tablespoon : Volume(R.string.tablespoon)
    object Cup : Volume(R.string.cup)
    object FluidOunce : Volume(R.string.fluid_ounce)
    object Quart : Volume(R.string.quart)
    object Pint : Volume(R.string.pint)
    object Gallon : Volume(R.string.gallon)
    object Barrel : Volume(R.string.barrel)
    object FluidOunceUk : Volume(R.string.fluid_ounce_uk)
    object QuartUk : Volume(R.string.quart_uk)
    object PintUk : Volume(R.string.pint_uk)
    object GallonUk : Volume(R.string.gallon_uk)
    object BarrelUk : Volume(R.string.barrel_uk)
    object Millilitre : Volume(R.string.millilitre)
    object Litre : Volume(R.string.litre)
    object CubicCentimetre : Volume(R.string.cubic_cm)
    object CubicMetre : Volume(R.string.cubic_m)
    object CubicInch : Volume(R.string.cubic_inch)
    object CubicFoot : Volume(R.string.cubic_foot)
    object CubicYard : Volume(R.string.cubic_yard)

    companion object {
        val all: List<Volume> = listOf(
            Teaspoon, Tablespoon, Cup, FluidOunce, Quart, Pint, Gallon, FluidOunceUk, QuartUk, PintUk, GallonUk, BarrelUk,
            Millilitre, Litre, CubicCentimetre, CubicMetre, CubicInch, CubicFoot, CubicYard
        )

        val cooking: List<Volume> = listOf(
            Teaspoon, Tablespoon, Cup, FluidOunce, FluidOunceUk, Pint, PintUk, Quart, QuartUk, Gallon, GallonUk, Millilitre, Litre
        )
    }
}