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
            SqKilometre,
            SqMetre,
            SqCentimetre,
            Hectare,
            SqMile,
            SqYard,
            SqFoot,
            SqInch,
            Acre
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
    object GasMark : Temperature(R.string.gas_mark)
}