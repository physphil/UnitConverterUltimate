package com.physphil.android.unitconverterultimate.data

import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.DigitalStorage
import com.physphil.android.unitconverterultimate.models.Energy
import com.physphil.android.unitconverterultimate.models.Length
import com.physphil.android.unitconverterultimate.models.Mass
import com.physphil.android.unitconverterultimate.models.Power
import com.physphil.android.unitconverterultimate.models.Pressure
import com.physphil.android.unitconverterultimate.models.Speed
import com.physphil.android.unitconverterultimate.models.Time
import com.physphil.android.unitconverterultimate.models.Unit
import java.math.BigDecimal

/**
 * Represents the multipliers to convert a unit to (first entry) and from (second entry) the base unit.
 */
private typealias Multipliers = Pair<BigDecimal, BigDecimal>

private val baseUnitMultipliers = Multipliers(BigDecimal.ONE, BigDecimal.ONE)

abstract class DataSource<T : Unit> {
    abstract val units: Map<T, Multipliers>

    fun getMultiplier(initial: T, final: T): BigDecimal {
        val toStandard = units[initial]?.first
            ?: throw IllegalArgumentException("Tsk tsk. Unit $initial is not configured correctly in the data source.")

        val fromStandard = units[final]?.second
            ?: throw IllegalArgumentException("Tsk tsk. Unit $final is not configured correctly in the data source.")

        return toStandard * fromStandard
    }
}

object AreaDataSource : DataSource<Area>() {
    override val units: Map<Area, Multipliers> = mapOf(
        Area.SqMetre to baseUnitMultipliers,
        Area.SqKilometre to Multipliers(BigDecimal(1000000), BigDecimal("0.000001")),
        Area.SqCentimetre to Multipliers(BigDecimal("0.0001"), BigDecimal(10000)),
        Area.Hectare to Multipliers(BigDecimal(10000), BigDecimal("0.0001")),
        Area.SqMile to Multipliers(BigDecimal("2589988.110336"), BigDecimal("0.000000386102158542445847")),
        Area.SqYard to Multipliers(BigDecimal("0.83612736"), BigDecimal("1.19599004630108026")),
        Area.SqFoot to Multipliers(BigDecimal("0.09290304"), BigDecimal("10.7639104167097223")),
        Area.SqInch to Multipliers(BigDecimal("0.00064516"), BigDecimal("1550.00310000620001")),
        Area.Acre to Multipliers(BigDecimal("4046.8564224"), BigDecimal("0.000247105381467165342"))
    )
}

object DigitalStorageDataSource : DataSource<DigitalStorage>() {
    override val units: Map<DigitalStorage, Multipliers> = mapOf(
        DigitalStorage.Bit to Multipliers(BigDecimal("0.00000011920928955078"), BigDecimal("8388608.0")),
        DigitalStorage.Byte to Multipliers(BigDecimal("0.00000095367431640625"), BigDecimal("1048576.0")),
        DigitalStorage.Kilobit to Multipliers(BigDecimal("0.0001220703125"), BigDecimal("8192.0")),
        DigitalStorage.Kilobyte to Multipliers(BigDecimal("0.0009765625"), BigDecimal("1024.0")),
        DigitalStorage.Megabit to Multipliers(BigDecimal("0.125"), BigDecimal("8.0")),
        DigitalStorage.Megabyte to baseUnitMultipliers,
        DigitalStorage.Gigabit to Multipliers(BigDecimal("128.0"), BigDecimal("0.0078125")),
        DigitalStorage.Gigabyte to Multipliers(BigDecimal("1024.0"), BigDecimal("0.0009765625")),
        DigitalStorage.Terabit to Multipliers(BigDecimal("131072.0"), BigDecimal("0.00000762939453125")),
        DigitalStorage.Terabyte to Multipliers(BigDecimal("1048576.0"), BigDecimal("0.00000095367431640625"))
    )
}

object EnergyDataSource : DataSource<Energy>() {
    override val units: Map<Energy, Multipliers> = mapOf(
        Energy.Joule to baseUnitMultipliers,
        Energy.Kilojoule to Multipliers(BigDecimal(1000), BigDecimal("0.001")),
        Energy.Calorie to Multipliers(BigDecimal("4.184"), BigDecimal("0.2390057361376673040153")),
        Energy.Kilocalorie to Multipliers(BigDecimal(4184), BigDecimal("0.0002390057361376673040153")),
        Energy.Btu to Multipliers(BigDecimal("1055.05585262"), BigDecimal("0.0009478171203133172000128")),
        Energy.FtLbF to Multipliers(BigDecimal("1.3558179483314004"), BigDecimal("0.7375621494575464935503")),
        Energy.InLbF to Multipliers(BigDecimal("0.1129848290276167"), BigDecimal("8.850745793490557922604")),
        Energy.KilowattHour to Multipliers(BigDecimal("3600000.0"), BigDecimal("0.0000002777777777777777777778"))
    )
}

object LengthDataSource : DataSource<Length>() {
    override val units: Map<Length, Multipliers> = mapOf(
        Length.Kilometre to Multipliers(BigDecimal("1000"), BigDecimal("0.001")),
        Length.Mile to Multipliers(BigDecimal("1609.344"), BigDecimal("0.00062137119223733397")),
        Length.Metre to baseUnitMultipliers,
        Length.Centimetre to Multipliers(BigDecimal("0.01"), BigDecimal(100)),
        Length.Millimetre to Multipliers(BigDecimal("0.001"), BigDecimal(1000)),
        Length.Micrometre to Multipliers(BigDecimal("0.000001"), BigDecimal(1000000)),
        Length.Nanometre to Multipliers(BigDecimal("0.000000001"), BigDecimal(1000000000)),
        Length.Yard to Multipliers(BigDecimal("0.9144"), BigDecimal("1.09361329833770779")),
        Length.Feet to Multipliers(BigDecimal("0.3048"), BigDecimal("3.28083989501312336")),
        Length.Inch to Multipliers(BigDecimal("0.0254"), BigDecimal("39.3700787401574803")),
        Length.NauticalMile to Multipliers(BigDecimal(1852), BigDecimal("0.000539956803455723542")),
        Length.Furlong to Multipliers(BigDecimal("201.168"), BigDecimal("0.0049709695379")),
        Length.LightYear to Multipliers(BigDecimal("9460730472580800"), BigDecimal("0.0000000000000001057000834024615463709"))
    )
}

object MassDataSource : DataSource<Mass>() {
    override val units: Map<Mass, Pair<BigDecimal, BigDecimal>> = mapOf(
        Mass.Kilogram to baseUnitMultipliers,
        Mass.Pound to Multipliers(BigDecimal("0.45359237"), BigDecimal("2.20462262184877581")),
        Mass.Gram to Multipliers(BigDecimal("0.001"), BigDecimal(1000)),
        Mass.Milligram to Multipliers(BigDecimal("0.000001"), BigDecimal(1000000)),
        Mass.Ounce to Multipliers(BigDecimal("0.028349523125"), BigDecimal("35.27396194958041291568")),
        Mass.Grain to Multipliers(BigDecimal("0.00006479891"), BigDecimal("15432.35835294143065061")),
        Mass.Stone to Multipliers(BigDecimal("6.35029318"), BigDecimal("0.15747304441777")),
        Mass.MetricTon to Multipliers(BigDecimal(1000), BigDecimal("0.001")),
        Mass.ShortTon to Multipliers(BigDecimal("907.18474"), BigDecimal("0.0011023113109243879")),
        Mass.LongTon to Multipliers(BigDecimal("1016.0469088"), BigDecimal("0.0009842065276110606282276"))
    )
}

object PowerDataSource : DataSource<Power>() {
    override val units: Map<Power, Multipliers> = mapOf(
        Power.Watt to baseUnitMultipliers,
        Power.Kilowatt to Multipliers(BigDecimal(1000), BigDecimal("0.001")),
        Power.Megawatt to Multipliers(BigDecimal(1000000), BigDecimal("0.000001")),
        Power.Horsepower to Multipliers(BigDecimal("735.49875"), BigDecimal("0.00135962161730390432")),
        Power.HorsepowerUk to Multipliers(BigDecimal("745.69987158227022"), BigDecimal("0.00134102208959502793")),
        Power.FtLbFS to Multipliers(BigDecimal("1.3558179483314004"), BigDecimal("0.737562149277265364")),
        Power.CaloriePerSecond to Multipliers(BigDecimal("4.1868"), BigDecimal("0.23884589662749594")),
        Power.BtuPerSecond to Multipliers(BigDecimal("1055.05585262"), BigDecimal("0.0009478171203133172")),
        Power.Kva to Multipliers(BigDecimal(1000), BigDecimal("0.001"))
    )
}

object PressureDataSource : DataSource<Pressure>() {
    override val units: Map<Pressure, Multipliers> = mapOf(
        Pressure.Megapascal to Multipliers(BigDecimal("1000000.0"), BigDecimal("0.000001")),
        Pressure.Kilopascal to Multipliers(BigDecimal("1000.0"), BigDecimal("0.001")),
        Pressure.Pascal to baseUnitMultipliers,
        Pressure.Bar to Multipliers(BigDecimal("100000.0"), BigDecimal("0.00001")),
        Pressure.Psi to Multipliers(BigDecimal("6894.757293168361"), BigDecimal("0.000145037737730209222")),
        Pressure.Psf to Multipliers(BigDecimal("47.880258980335840277777777778"), BigDecimal("0.020885434233150127968")),
        Pressure.Atmosphere to Multipliers(BigDecimal("101325"), BigDecimal("0.0000098692326671601283")),
        Pressure.TechnicalAtmosphere to Multipliers(BigDecimal("98066.5"), BigDecimal("0.0000101971621297792824257")),
        Pressure.MmHg to Multipliers(BigDecimal("133.322387415"), BigDecimal("0.007500615758456563339513")),
        Pressure.Torr to Multipliers(BigDecimal("133.3223684210526315789"), BigDecimal("0.00750061682704169751"))
    )
}

object SpeedDataSource : DataSource<Speed>() {
    override val units: Map<Speed, Multipliers> = mapOf(
        Speed.KilometresPerHour to Multipliers(BigDecimal("0.27777777777778"), BigDecimal("3.6")),
        Speed.MilesPerHour to Multipliers(BigDecimal("0.44704"), BigDecimal("2.2369362920544")),
        Speed.MetresPerSecond to baseUnitMultipliers,
        Speed.FeetPerSecond to Multipliers(BigDecimal("0.3048"), BigDecimal("3.2808398950131")),
        Speed.Knot to Multipliers(BigDecimal("0.51444444444444"), BigDecimal("1.9438444924406"))
    )
}

object TimeDataSource : DataSource<Time>() {
    override val units: Map<Time, Multipliers> = mapOf(
        Time.Year to multipliers("31536000", "0.0000000317097919837645865"),
        Time.Month to multipliers("2628000", "0.0000003805175"),
        Time.Week to multipliers("604800", "0.00000165343915343915344"),
        Time.Day to multipliers("86400", "0.0000115740740740740741"),
        Time.Hour to multipliers("3600", "0.000277777777777777778"),
        Time.Minute to multipliers("60", "0.0166666666666666667"),
        Time.Second to baseUnitMultipliers,
        Time.Millisecond to multipliers("0.001", "1000"),
        Time.Nanosecond to multipliers("0.000000001", "1000000000")
    )
}

private fun multipliers(toBase: String, fromBase: String): Multipliers =
    BigDecimal(toBase) to BigDecimal(fromBase)