package com.physphil.android.unitconverterultimate.data

import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.DigitalStorage
import com.physphil.android.unitconverterultimate.models.Mass
import com.physphil.android.unitconverterultimate.models.Unit
import java.math.BigDecimal

/**
 * Represents the multipliers to convert a unit to (first entry) and from (second entry) the base unit.
 */
private typealias Multipliers = Pair<BigDecimal, BigDecimal>

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
        Area.SqMetre to Multipliers(BigDecimal.ONE, BigDecimal.ONE),
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
        DigitalStorage.Megabyte to Multipliers(BigDecimal.ONE, BigDecimal.ONE),
        DigitalStorage.Gigabit to Multipliers(BigDecimal("128.0"), BigDecimal("0.0078125")),
        DigitalStorage.Gigabyte to Multipliers(BigDecimal("1024.0"), BigDecimal("0.0009765625")),
        DigitalStorage.Terabit to Multipliers(BigDecimal("131072.0"), BigDecimal("0.00000762939453125")),
        DigitalStorage.Terabyte to Multipliers(BigDecimal("1048576.0"), BigDecimal("0.00000095367431640625"))
    )
}

object MassDataSource : DataSource<Mass>() {
    override val units: Map<Mass, Pair<BigDecimal, BigDecimal>> = mapOf(
        Mass.Kilogram to Multipliers(BigDecimal.ONE, BigDecimal.ONE),
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