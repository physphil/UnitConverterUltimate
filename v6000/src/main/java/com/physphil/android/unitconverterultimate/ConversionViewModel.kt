package com.physphil.android.unitconverterultimate

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.physphil.android.unitconverterultimate.conversion.Converter
import com.physphil.android.unitconverterultimate.conversion.CurrencyConverter
import com.physphil.android.unitconverterultimate.conversion.FuelConsumptionConverter
import com.physphil.android.unitconverterultimate.conversion.TemperatureConverter
import com.physphil.android.unitconverterultimate.data.CurrencyRepository
import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.ConversionType
import com.physphil.android.unitconverterultimate.models.Currency
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
import com.physphil.android.unitconverterultimate.persistence.models.RateEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.math.BigDecimal

@InternalCoroutinesApi
class ConversionViewModel(
    conversionType: ConversionType,
    private val repo: CurrencyRepository
) : ViewModel() {

    private val _viewData = MutableLiveData<ViewData>()
    val viewData: LiveData<ViewData> = _viewData

    private val _resultLiveData = MutableLiveData<BigDecimal>()
    val resultLiveData: LiveData<BigDecimal> = _resultLiveData

    private val _selectedUnitsLiveData = MutableLiveData<SelectedUnits>()
    val selectedUnitsLiveData: LiveData<SelectedUnits> = _selectedUnitsLiveData

    // TODO how much of this do we actually need to save as state?
    private var value: BigDecimal = BigDecimal.ONE
    private var result: BigDecimal = BigDecimal.ZERO
    private var initialIndex: Int = 0
    private var finalIndex: Int = 1

    private lateinit var rates: List<RateEntity>

    private val units = when (conversionType) {
        ConversionType.AREA -> Area.all
        ConversionType.COOKING -> Volume.cooking
        ConversionType.CURRENCY -> Currency.all
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

    init {
        val state = ViewData(BigDecimal.ONE, units)
//        _viewData.postValue(state)
//        _selectedUnitsLiveData.postValue(SelectedUnits(initialIndex, finalIndex))

        // FIXME: Load currency rates from API
        // FIXME: CLEAN THIS UP!!!
        if (conversionType == ConversionType.CURRENCY) {
            Log.d("phil", "We're in Currency fragment!")
            viewModelScope.launch(Dispatchers.IO) {
                // TODO show loading screen
                repo.getRates().collect {
                    // TODO convert value, post update to LD
                    // TODO hide loading screen
//                    currencyDataSource = CurrencyDataSource(it)
                    // TODO save rates from Flow?
                    // Initialize view data in this case, but also have to worry about have it's just an update after view has already been set up
                    rates = it
                    _viewData.postValue(state)
                    _selectedUnitsLiveData.postValue(SelectedUnits(initialIndex, finalIndex))
                }
            }
        }
        else {
            _viewData.postValue(state)
            _selectedUnitsLiveData.postValue(SelectedUnits(initialIndex, finalIndex))
        }
    }

    fun updateValue(value: BigDecimal) {
        this.value = value
        updateResult()
    }

    fun updateInitialIndex(index: Int) {
        initialIndex = index
        updateResult()
    }

    fun updateFinalIndex(index: Int) {
        finalIndex = index
        updateResult()
    }

    fun swapUnits() {
        run {
            val temp = initialIndex
            initialIndex = finalIndex
            finalIndex = temp
        }
        _selectedUnitsLiveData.postValue(SelectedUnits(initialIndex, finalIndex))
        updateResult()
    }

    private fun updateResult() {
        val initialUnit = units[initialIndex]
        val finalUnit = units[finalIndex]

        result = when {
            initialUnit == finalUnit -> value
            initialUnit is Area && finalUnit is Area -> Converter.convert(value, initialUnit, finalUnit)
            initialUnit is Currency && finalUnit is Currency -> CurrencyConverter.convert(value, initialUnit, finalUnit, rates)
            initialUnit is DigitalStorage && finalUnit is DigitalStorage -> Converter.convert(value, initialUnit, finalUnit)
            initialUnit is Energy && finalUnit is Energy -> Converter.convert(value, initialUnit, finalUnit)
            initialUnit is Fuel && finalUnit is Fuel -> FuelConsumptionConverter.convert(value, initialUnit, finalUnit)
            initialUnit is Length && finalUnit is Length -> Converter.convert(value, initialUnit, finalUnit)
            initialUnit is Mass && finalUnit is Mass -> Converter.convert(value, initialUnit, finalUnit)
            initialUnit is Power && finalUnit is Power -> Converter.convert(value, initialUnit, finalUnit)
            initialUnit is Pressure && finalUnit is Pressure -> Converter.convert(value, initialUnit, finalUnit)
            initialUnit is Speed && finalUnit is Speed -> Converter.convert(value, initialUnit, finalUnit)
            initialUnit is Temperature && finalUnit is Temperature -> TemperatureConverter.convert(value, initialUnit, finalUnit)
            initialUnit is Time && finalUnit is Time -> Converter.convert(value, initialUnit, finalUnit)
            initialUnit is Torque && finalUnit is Torque -> Converter.convert(value, initialUnit, finalUnit)
            initialUnit is Volume && finalUnit is Volume -> Converter.convert(value, initialUnit, finalUnit)
            else -> throw IllegalArgumentException("The initial unit $initialUnit and final unit $finalUnit are not of the same type, and cannot be converted.")
        }

        _resultLiveData.postValue(result)
    }

    class Factory(
        private val conversionType: ConversionType,
        private val currencyRepository: CurrencyRepository
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ConversionViewModel::class.java)) {
                return ConversionViewModel(conversionType, currencyRepository) as T
            }

            throw IllegalArgumentException("Cannot instantiate ViewModel class with those arguments")
        }
    }

    data class ViewData(
        val value: BigDecimal,
        val units: List<Unit>
    )

    data class SelectedUnits(
        val initialIndex: Int,
        val finalIndex: Int
    )
}