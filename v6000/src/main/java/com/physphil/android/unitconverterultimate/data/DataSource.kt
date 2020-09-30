package com.physphil.android.unitconverterultimate.data

import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.DigitalStorage
import com.physphil.android.unitconverterultimate.models.Energy
import com.physphil.android.unitconverterultimate.models.Fuel
import com.physphil.android.unitconverterultimate.models.Length
import com.physphil.android.unitconverterultimate.models.Mass
import com.physphil.android.unitconverterultimate.models.Power
import com.physphil.android.unitconverterultimate.models.Pressure
import com.physphil.android.unitconverterultimate.models.Speed
import com.physphil.android.unitconverterultimate.models.Time
import com.physphil.android.unitconverterultimate.models.Torque
import com.physphil.android.unitconverterultimate.models.Unit
import com.physphil.android.unitconverterultimate.models.Volume
import java.math.BigDecimal

/**
 * Represents the multipliers to convert a unit to (first entry) and from (second entry) the base unit.
 */
typealias Multipliers = Pair<BigDecimal, BigDecimal>

private val baseUnitMultipliers = Multipliers(BigDecimal.ONE, BigDecimal.ONE)

abstract class DataSource<T : Unit> {
    abstract val units: Map<T, Multipliers>

    operator fun get(unit: T): Multipliers = units[unit]
        ?: throw IllegalArgumentException("Tsk task. Unit '$unit' is not configured correctly in the data source.")

    fun getMultiplier(initial: T, final: T): BigDecimal {
        val toStandard = this[initial].first
        val fromStandard = this[final].second
        return toStandard * fromStandard
    }
}

object AreaDataSource : DataSource<Area>() {
    override val units: Map<Area, Multipliers> = mapOf(
        Area.SqMetre to baseUnitMultipliers,
        Area.SqKilometre to multipliers("1000000", "0.000001"),
        Area.SqCentimetre to multipliers("0.0001", "10000"),
        Area.Hectare to multipliers("10000", "0.0001"),
        Area.SqMile to multipliers("2589988.110336", "0.000000386102158542445847"),
        Area.SqYard to multipliers("0.83612736", "1.19599004630108026"),
        Area.SqFoot to multipliers("0.09290304", "10.7639104167097223"),
        Area.SqInch to multipliers("0.00064516", "1550.00310000620001"),
        Area.Acre to multipliers("4046.8564224", "0.000247105381467165342")
    )
}

object DigitalStorageDataSource : DataSource<DigitalStorage>() {
    override val units: Map<DigitalStorage, Multipliers> = mapOf(
        DigitalStorage.Bit to multipliers("0.00000011920928955078", "8388608.0"),
        DigitalStorage.Byte to multipliers("0.00000095367431640625", "1048576.0"),
        DigitalStorage.Kilobit to multipliers("0.0001220703125", "8192.0"),
        DigitalStorage.Kilobyte to multipliers("0.0009765625", "1024.0"),
        DigitalStorage.Megabit to multipliers("0.125", "8.0"),
        DigitalStorage.Megabyte to baseUnitMultipliers,
        DigitalStorage.Gigabit to multipliers("128.0", "0.0078125"),
        DigitalStorage.Gigabyte to multipliers("1024.0", "0.0009765625"),
        DigitalStorage.Terabit to multipliers("131072.0", "0.00000762939453125"),
        DigitalStorage.Terabyte to multipliers("1048576.0", "0.00000095367431640625")
    )
}

object EnergyDataSource : DataSource<Energy>() {
    override val units: Map<Energy, Multipliers> = mapOf(
        Energy.Joule to baseUnitMultipliers,
        Energy.Kilojoule to multipliers("1000", "0.001"),
        Energy.Calorie to multipliers("4.184", "0.2390057361376673040153"),
        Energy.Kilocalorie to multipliers("4184", "0.0002390057361376673040153"),
        Energy.Btu to multipliers("1055.05585262", "0.0009478171203133172000128"),
        Energy.FtLbF to multipliers("1.3558179483314004", "0.7375621494575464935503"),
        Energy.InLbF to multipliers("0.1129848290276167", "8.850745793490557922604"),
        Energy.KilowattHour to multipliers("3600000.0", "0.0000002777777777777777777778")
    )
}

object FuelConsumptionDataSource : DataSource<Fuel>() {
    override val units: Map<Fuel, Multipliers> = mapOf(
        Fuel.MilesPerGallonUs to baseUnitMultipliers,
        Fuel.MilesPerGallonUk to multipliers("0.83267418460479", "1.2009499255398"),
        Fuel.LitresPer100k to multipliers("235.214582", "235.214582"),
        Fuel.KilometresPerLitre to multipliers("2.352145833", "0.42514370749052"),
        Fuel.MilesPerLitre to multipliers("3.7854118", "0.264172052")
    )
}

object LengthDataSource : DataSource<Length>() {
    override val units: Map<Length, Multipliers> = mapOf(
        Length.Kilometre to multipliers("1000", "0.001"),
        Length.Mile to multipliers("1609.344", "0.00062137119223733397"),
        Length.Metre to baseUnitMultipliers,
        Length.Centimetre to multipliers("0.01", "100"),
        Length.Millimetre to multipliers("0.001", "1000"),
        Length.Micrometre to multipliers("0.000001", "1000000"),
        Length.Nanometre to multipliers("0.000000001", "1000000000"),
        Length.Yard to multipliers("0.9144", "1.09361329833770779"),
        Length.Feet to multipliers("0.3048", "3.28083989501312336"),
        Length.Inch to multipliers("0.0254", "39.3700787401574803"),
        Length.NauticalMile to multipliers("1852", "0.000539956803455723542"),
        Length.Furlong to multipliers("201.168", "0.0049709695379"),
        Length.LightYear to multipliers("9460730472580800", "0.0000000000000001057000834024615463709")
    )
}

object MassDataSource : DataSource<Mass>() {
    override val units: Map<Mass, Pair<BigDecimal, BigDecimal>> = mapOf(
        Mass.Kilogram to baseUnitMultipliers,
        Mass.Pound to multipliers("0.45359237", "2.20462262184877581"),
        Mass.Gram to multipliers("0.001", "1000"),
        Mass.Milligram to multipliers("0.000001", "1000000"),
        Mass.Ounce to multipliers("0.028349523125", "35.27396194958041291568"),
        Mass.Grain to multipliers("0.00006479891", "15432.35835294143065061"),
        Mass.Stone to multipliers("6.35029318", "0.15747304441777"),
        Mass.MetricTon to multipliers("1000", "0.001"),
        Mass.ShortTon to multipliers("907.18474", "0.0011023113109243879"),
        Mass.LongTon to multipliers("1016.0469088", "0.0009842065276110606282276")
    )
}

object PowerDataSource : DataSource<Power>() {
    override val units: Map<Power, Multipliers> = mapOf(
        Power.Watt to baseUnitMultipliers,
        Power.Kilowatt to multipliers("1000", "0.001"),
        Power.Megawatt to multipliers("1000000", "0.000001"),
        Power.Horsepower to multipliers("735.49875", "0.00135962161730390432"),
        Power.HorsepowerUk to multipliers("745.69987158227022", "0.00134102208959502793"),
        Power.FtLbFS to multipliers("1.3558179483314004", "0.737562149277265364"),
        Power.CaloriePerSecond to multipliers("4.1868", "0.23884589662749594"),
        Power.BtuPerSecond to multipliers("1055.05585262", "0.0009478171203133172"),
        Power.Kva to multipliers("1000", "0.001")
    )
}

object PressureDataSource : DataSource<Pressure>() {
    override val units: Map<Pressure, Multipliers> = mapOf(
        Pressure.Megapascal to multipliers("1000000.0", "0.000001"),
        Pressure.Kilopascal to multipliers("1000.0", "0.001"),
        Pressure.Pascal to baseUnitMultipliers,
        Pressure.Bar to multipliers("100000.0", "0.00001"),
        Pressure.Psi to multipliers("6894.757293168361", "0.000145037737730209222"),
        Pressure.Psf to multipliers("47.880258980335840277777777778", "0.020885434233150127968"),
        Pressure.Atmosphere to multipliers("101325", "0.0000098692326671601283"),
        Pressure.TechnicalAtmosphere to multipliers("98066.5", "0.0000101971621297792824257"),
        Pressure.MmHg to multipliers("133.322387415", "0.007500615758456563339513"),
        Pressure.Torr to multipliers("133.3223684210526315789", "0.00750061682704169751")
    )
}

object SpeedDataSource : DataSource<Speed>() {
    override val units: Map<Speed, Multipliers> = mapOf(
        Speed.KilometresPerHour to multipliers("0.27777777777778", "3.6"),
        Speed.MilesPerHour to multipliers("0.44704", "2.2369362920544"),
        Speed.MetresPerSecond to baseUnitMultipliers,
        Speed.FeetPerSecond to multipliers("0.3048", "3.2808398950131"),
        Speed.Knot to multipliers("0.51444444444444", "1.9438444924406")
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

object TorqueDataSource : DataSource<Torque>() {
    override val units: Map<Torque, Multipliers> = mapOf(
        Torque.NewtonMetres to baseUnitMultipliers,
        Torque.FtLbF to multipliers("1.3558179483314004", "0.7375621494575464935503"),
        Torque.InLbF to multipliers("0.1129848290276167", "8.850745793490557922604")
    )
}

object VolumeDataSource : DataSource<Volume>() {
    override val units: Map<Volume, Multipliers> = mapOf(
        Volume.Teaspoon to multipliers("0.0000049289215938", "202884.136211058"),
        Volume.Tablespoon to multipliers("0.0000147867647812", "67628.045403686"),
        Volume.Cup to multipliers("0.0002365882365", "4226.7528377304"),
        Volume.FluidOunce to multipliers("0.0000295735295625", "33814.0227018429972"),
        Volume.FluidOunceUk to multipliers("0.0000284130625", "35195.07972785404600437"),
        Volume.Pint to multipliers("0.000473176473", "2113.37641886518732"),
        Volume.PintUk to multipliers("0.00056826125", "1759.753986392702300218"),
        Volume.Quart to multipliers("0.000946352946", "1056.68820943259366"),
        Volume.QuartUk to multipliers("0.0011365225", "879.8769931963511501092"),
        Volume.Gallon to multipliers("0.003785411784", "264.172052358148415"),
        Volume.GallonUk to multipliers("0.00454609", "219.9692482990877875273"),
        Volume.Barrel to multipliers("0.119240471196", "8.38641436057614017079"),
        Volume.BarrelUk to multipliers("0.16365924", "6.11025689719688298687"),
        Volume.Millilitre to multipliers("0.000001", "1000000.0"),
        Volume.Litre to multipliers("0.001", "1000.0"),
        Volume.CubicCentimetre to multipliers("0.000001", "1000000.0"),
        Volume.CubicMetre to baseUnitMultipliers,
        Volume.CubicInch to multipliers("0.000016387064", "61023.744094732284"),
        Volume.CubicFoot to multipliers("0.028316846592", "35.3146667214885903"),
        Volume.CubicYard to multipliers("0.7645548692741148", "1.3079506")
    )
}

private fun multipliers(toBase: String, fromBase: String): Multipliers =
    BigDecimal(toBase) to BigDecimal(fromBase)