package com.physphil.android.unitconverterultimate.models

enum class ConversionType {
    AREA,
    COOKING,
    CURRENCY,
    DIGITAL_STORAGE,
    ENERGY,
    FUEL_CONSUMPTION,
    LENGTH,
    MASS,
    POWER,
    PRESSURE,
    SPEED,
    TEMPERATURE,
    TIME,
    TORQUE,
    VOLUME;

    companion object {
        fun fromSpinnerPosition(position: Int): ConversionType =
            when (position) {
                0 -> AREA
                1 -> COOKING
                2 -> CURRENCY
                3 -> DIGITAL_STORAGE
                4 -> ENERGY
                5 -> FUEL_CONSUMPTION
                6 -> LENGTH
                7 -> MASS
                8 -> POWER
                9 -> PRESSURE
                10 -> SPEED
                11 -> TEMPERATURE
                12 -> TIME
                13 -> TORQUE
                14 -> VOLUME
                else -> AREA
            }
    }
}