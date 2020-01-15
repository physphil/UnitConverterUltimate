package com.physphil.android.unitconverterultimate.conversion

import com.physphil.android.unitconverterultimate.data.AreaDataSource
import com.physphil.android.unitconverterultimate.data.DigitalStorageDataSource
import com.physphil.android.unitconverterultimate.data.EnergyDataSource
import com.physphil.android.unitconverterultimate.data.LengthDataSource
import com.physphil.android.unitconverterultimate.data.MassDataSource
import com.physphil.android.unitconverterultimate.data.PowerDataSource
import com.physphil.android.unitconverterultimate.data.PressureDataSource
import com.physphil.android.unitconverterultimate.data.SpeedDataSource
import com.physphil.android.unitconverterultimate.data.TimeDataSource
import com.physphil.android.unitconverterultimate.data.TorqueDataSource
import com.physphil.android.unitconverterultimate.data.VolumeDataSource
import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.DigitalStorage
import com.physphil.android.unitconverterultimate.models.Energy
import com.physphil.android.unitconverterultimate.models.Length
import com.physphil.android.unitconverterultimate.models.Mass
import com.physphil.android.unitconverterultimate.models.Power
import com.physphil.android.unitconverterultimate.models.Pressure
import com.physphil.android.unitconverterultimate.models.Speed
import com.physphil.android.unitconverterultimate.models.Time
import com.physphil.android.unitconverterultimate.models.Torque
import com.physphil.android.unitconverterultimate.models.Volume
import java.math.BigDecimal

const val SCALE_RESULT = 15

object Converter {

    fun convert(value: BigDecimal, initial: Area, final: Area): BigDecimal =
        convertStandardUnit(value, AreaDataSource.getMultiplier(initial, final))

    fun convert(value: BigDecimal, initial: DigitalStorage, final: DigitalStorage): BigDecimal =
        convertStandardUnit(value, DigitalStorageDataSource.getMultiplier(initial, final))

    fun convert(value: BigDecimal, initial: Energy, final: Energy): BigDecimal =
        convertStandardUnit(value, EnergyDataSource.getMultiplier(initial, final))

    fun convert(value: BigDecimal, initial: Length, final: Length): BigDecimal =
        convertStandardUnit(value, LengthDataSource.getMultiplier(initial, final))

    fun convert(value: BigDecimal, initial: Mass, final: Mass): BigDecimal =
        convertStandardUnit(value, MassDataSource.getMultiplier(initial, final))

    fun convert(value: BigDecimal, initial: Power, final: Power): BigDecimal =
        convertStandardUnit(value, PowerDataSource.getMultiplier(initial, final))

    fun convert(value: BigDecimal, initial: Pressure, final: Pressure): BigDecimal =
        convertStandardUnit(value, PressureDataSource.getMultiplier(initial, final))

    fun convert(value: BigDecimal, initial: Speed, final: Speed): BigDecimal =
        convertStandardUnit(value, SpeedDataSource.getMultiplier(initial, final))

    fun convert(value: BigDecimal, initial: Time, final: Time): BigDecimal =
        convertStandardUnit(value, TimeDataSource.getMultiplier(initial, final))

    fun convert(value: BigDecimal, initial: Torque, final: Torque): BigDecimal =
        convertStandardUnit(value, TorqueDataSource.getMultiplier(initial, final))

    fun convert(value: BigDecimal, initial: Volume, final: Volume): BigDecimal =
        convertStandardUnit(value, VolumeDataSource.getMultiplier(initial, final))

    private fun convertStandardUnit(value: BigDecimal, multiplier: BigDecimal): BigDecimal =
        value * multiplier
}