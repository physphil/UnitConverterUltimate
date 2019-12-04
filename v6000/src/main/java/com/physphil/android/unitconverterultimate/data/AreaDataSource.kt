package com.physphil.android.unitconverterultimate.data

import com.physphil.android.unitconverterultimate.models.Area
import java.math.BigDecimal

object AreaDataSource {
    private val units: Map<Area, Pair<BigDecimal, BigDecimal>> = mapOf(
        Area.SqMetre to Pair(BigDecimal.ONE, BigDecimal.ONE),
        Area.SqKilometre to Pair(BigDecimal(1000000), BigDecimal("0.000001")),
        Area.SqCentimetre to Pair(BigDecimal("0.0001"), BigDecimal(10000)),
        Area.Hectare to Pair(BigDecimal(10000), BigDecimal("0.0001")),
        Area.SqMile to Pair(BigDecimal("2589988.110336"), BigDecimal("0.000000386102158542445847")),
        Area.SqYard to Pair(BigDecimal("0.83612736"), BigDecimal("1.19599004630108026")),
        Area.SqFoot to Pair(BigDecimal("0.09290304"), BigDecimal("10.7639104167097223")),
        Area.SqInch to Pair(BigDecimal("0.00064516"), BigDecimal("1550.00310000620001")),
        Area.Acre to Pair(BigDecimal("4046.8564224"), BigDecimal("0.000247105381467165342"))
    )

    // TODO in future abstract this into an interface and have AreaDataSource implement?
    fun getMultiplier(initial: Area, final: Area): BigDecimal {
        val toStandard = units[initial]?.first
            ?: throw IllegalArgumentException("Tsk tsk. Unit $initial is not configured correctly in the data source.")

        val fromStandard = units[final]?.second
            ?: throw IllegalArgumentException("Tsk tsk. Unit $final is not configured correctly in the data source.")

        return toStandard * fromStandard
    }
}