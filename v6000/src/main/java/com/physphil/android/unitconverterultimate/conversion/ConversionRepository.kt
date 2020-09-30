package com.physphil.android.unitconverterultimate.conversion

import com.physphil.android.unitconverterultimate.data.AreaDataSource
import com.physphil.android.unitconverterultimate.data.DigitalStorageDataSource
import com.physphil.android.unitconverterultimate.data.EnergyDataSource
import com.physphil.android.unitconverterultimate.data.FuelConsumptionConverter
import com.physphil.android.unitconverterultimate.data.LengthDataSource
import com.physphil.android.unitconverterultimate.data.MassDataSource
import com.physphil.android.unitconverterultimate.data.PowerDataSource
import com.physphil.android.unitconverterultimate.data.PressureDataSource
import com.physphil.android.unitconverterultimate.data.SpeedDataSource
import com.physphil.android.unitconverterultimate.data.TemperatureConverter
import com.physphil.android.unitconverterultimate.data.TimeDataSource
import com.physphil.android.unitconverterultimate.data.TorqueDataSource
import com.physphil.android.unitconverterultimate.data.VolumeDataSource
import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.ConversionType
import com.physphil.android.unitconverterultimate.models.DigitalStorage
import com.physphil.android.unitconverterultimate.models.Energy
import com.physphil.android.unitconverterultimate.models.Fuel
import com.physphil.android.unitconverterultimate.models.Length
import com.physphil.android.unitconverterultimate.models.Mass
import com.physphil.android.unitconverterultimate.models.Power
import com.physphil.android.unitconverterultimate.models.Pressure
import com.physphil.android.unitconverterultimate.models.Speed
import com.physphil.android.unitconverterultimate.models.Temperature
import com.physphil.android.unitconverterultimate.models.Time
import com.physphil.android.unitconverterultimate.models.Torque
import com.physphil.android.unitconverterultimate.models.Unit
import com.physphil.android.unitconverterultimate.models.Volume
import java.math.BigDecimal

class ConversionRepository {

    fun unitsFor(type: ConversionType): List<Unit> =
        when (type) {
            ConversionType.AREA -> Area.all
            ConversionType.COOKING -> Volume.cooking
            ConversionType.CURRENCY -> TODO()
            ConversionType.DIGITAL_STORAGE -> DigitalStorage.all
            ConversionType.ENERGY -> Energy.all
            ConversionType.FUEL_CONSUMPTION -> Fuel.all
            ConversionType.LENGTH -> Length.all
            ConversionType.POWER -> Power.all
            ConversionType.PRESSURE -> Pressure.all
            ConversionType.MASS -> Mass.all
            ConversionType.SPEED -> Speed.all
            ConversionType.TEMPERATURE -> Temperature.all
            ConversionType.TIME -> Time.all
            ConversionType.TORQUE -> Torque.all
            ConversionType.VOLUME -> Volume.all
        }

    fun convert(value: BigDecimal, initial: Unit, final: Unit): BigDecimal =
        when {
            initial == final -> value
            initial is Area && final is Area -> convertArea(value, initial, final)
            initial is DigitalStorage && final is DigitalStorage -> convertDigitalStorage(value, initial, final)
            initial is Energy && final is Energy -> convertEnergy(value, initial, final)
            initial is Fuel && final is Fuel -> FuelConsumptionConverter.convert(value, initial, final)
            initial is Length && final is Length -> convertLength(value, initial, final)
            initial is Mass && final is Mass -> convertMass(value, initial, final)
            initial is Power && final is Power -> convertPower(value, initial, final)
            initial is Pressure && final is Pressure -> convertPressure(value, initial, final)
            initial is Speed && final is Speed -> convertSpeed(value, initial, final)
            initial is Temperature && final is Temperature -> TemperatureConverter.convert(value, initial, final)
            initial is Time && final is Time -> convertTime(value, initial, final)
            initial is Torque && final is Torque -> convertTorque(value, initial, final)
            initial is Volume && final is Volume -> convertVolume(value, initial, final)
            else -> throw IllegalArgumentException("The initial unit $initial and final unit $final are not of the same type, and cannot be converted.")
        }

    private fun convertArea(value: BigDecimal, initial: Area, final: Area): BigDecimal =
        convertStandardUnit(value, AreaDataSource.getMultiplier(initial, final))

    private fun convertDigitalStorage(value: BigDecimal, initial: DigitalStorage, final: DigitalStorage): BigDecimal =
        convertStandardUnit(value, DigitalStorageDataSource.getMultiplier(initial, final))

    private fun convertEnergy(value: BigDecimal, initial: Energy, final: Energy): BigDecimal =
        convertStandardUnit(value, EnergyDataSource.getMultiplier(initial, final))

    private fun convertLength(value: BigDecimal, initial: Length, final: Length): BigDecimal =
        convertStandardUnit(value, LengthDataSource.getMultiplier(initial, final))

    private fun convertMass(value: BigDecimal, initial: Mass, final: Mass): BigDecimal =
        convertStandardUnit(value, MassDataSource.getMultiplier(initial, final))

    private fun convertPower(value: BigDecimal, initial: Power, final: Power): BigDecimal =
        convertStandardUnit(value, PowerDataSource.getMultiplier(initial, final))

    private fun convertPressure(value: BigDecimal, initial: Pressure, final: Pressure): BigDecimal =
        convertStandardUnit(value, PressureDataSource.getMultiplier(initial, final))

    private fun convertSpeed(value: BigDecimal, initial: Speed, final: Speed): BigDecimal =
        convertStandardUnit(value, SpeedDataSource.getMultiplier(initial, final))

    private fun convertTime(value: BigDecimal, initial: Time, final: Time): BigDecimal =
        convertStandardUnit(value, TimeDataSource.getMultiplier(initial, final))

    private fun convertTorque(value: BigDecimal, initial: Torque, final: Torque): BigDecimal =
        convertStandardUnit(value, TorqueDataSource.getMultiplier(initial, final))

    private fun convertVolume(value: BigDecimal, initial: Volume, final: Volume): BigDecimal =
        convertStandardUnit(value, VolumeDataSource.getMultiplier(initial, final))

    private fun convertStandardUnit(value: BigDecimal, multiplier: BigDecimal): BigDecimal =
        value * multiplier
}
