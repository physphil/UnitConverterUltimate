package com.physphil.android.unitconverterultimate.models

import com.physphil.android.unitconverterultimate.R
import java.math.BigDecimal

sealed class Unit {
    abstract val conversionType: ConversionType
    abstract val displayStringResId: Int
}

/**
 * A unit that is converted by the standard method of first converting the value to a base unit, and
 * then to the final requested unit. This is most units, but does not include Temperature and Fuel
 * Efficiency, for example.
 */
sealed class StandardUnit : Unit() {
    /**
     * A [BigDecimal] multiplier to convert a value from its current unit into the base [Unit] for its [ConversionType].
     */
    abstract val toStandard: BigDecimal
    /**
     * A [BigDecimal] multiplier to convert a value into its current unit from the base [Unit] for its [ConversionType].
     */
    abstract val fromStandard: BigDecimal
}

sealed class Area(
    override val displayStringResId: Int,
    override val toStandard: BigDecimal,
    override val fromStandard: BigDecimal
) : StandardUnit() {

    override val conversionType = ConversionType.AREA

    object SqMetre : Area(
        displayStringResId = R.string.sq_metre,
        toStandard = BigDecimal.ONE,
        fromStandard = BigDecimal.ONE
    )

    object SqKilometre : Area(
        displayStringResId = R.string.sq_kilometre,
        toStandard = BigDecimal(1000000),
        fromStandard = BigDecimal("0.000001")
    )

    object SqCentimetre : Area(
        displayStringResId = R.string.sq_centimetre,
        toStandard = BigDecimal("0.0001"),
        fromStandard = BigDecimal(10000)
    )

    object Hectare : Area(
        displayStringResId = R.string.hectare,
        toStandard = BigDecimal(10000),
        fromStandard = BigDecimal("0.0001")
    ) 
    
    object SqMile : Area(
        displayStringResId = R.string.sq_mile,
        toStandard = BigDecimal("2589988.110336"),
        fromStandard = BigDecimal("0.000000386102158542445847")
    )
    
    object SqYard : Area(
        displayStringResId = R.string.sq_yard,
        toStandard = BigDecimal("0.83612736"),
        fromStandard = BigDecimal("1.19599004630108026")
    )

    object SqFoot : Area(
        displayStringResId = R.string.sq_foot,
        toStandard = BigDecimal("0.09290304"),
        fromStandard = BigDecimal("10.7639104167097223")
    )

    object SqInch : Area(
        displayStringResId = R.string.sq_inch,
        toStandard = BigDecimal("0.00064516"),
        fromStandard = BigDecimal("1550.00310000620001")
    )

    object Acre : Area(
        displayStringResId = R.string.acre,
        toStandard = BigDecimal("4046.8564224"),
        fromStandard = BigDecimal("0.000247105381467165342")
    )
}

sealed class Temperature(override val displayStringResId: Int) : Unit() {
    override val conversionType: ConversionType = ConversionType.TEMPERATURE

    object Celsius : Temperature(R.string.celsius)
    object Fahrenheit : Temperature(R.string.fahrenheit)
    object Kelvin : Temperature(R.string.kelvin)
    object Rankine : Temperature(R.string.rankine)
    object Delisle : Temperature(R.string.delisle)
    object Newton : Temperature(R.string.newton)
    object Reaumur : Temperature(R.string.reaumur)
    object Romer : Temperature(R.string.romer)
    object GasMark : Temperature(R.string.gas_mark)
}