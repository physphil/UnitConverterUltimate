package com.physphil.android.unitconverterultimate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.physphil.android.unitconverterultimate.conversion.ConversionRepository
import com.physphil.android.unitconverterultimate.models.ConversionType
import com.physphil.android.unitconverterultimate.models.Unit
import java.math.BigDecimal

class ConversionViewModel(
    conversionType: ConversionType,
    private val repo: ConversionRepository
) : ViewModel() {

    private val _viewData = MutableLiveData<ViewData>()
    val viewData: LiveData<ViewData> = _viewData

    private val _resultLiveData = MutableLiveData<BigDecimal>()
    val resultLiveData: LiveData<BigDecimal> = _resultLiveData

    private val _selectedUnitsLiveData = MutableLiveData<SelectedUnits>()
    val selectedUnitsLiveData: LiveData<SelectedUnits> = _selectedUnitsLiveData

    private var value: BigDecimal = BigDecimal.ONE
    private var result: BigDecimal = BigDecimal.ZERO
    private var initialIndex: Int = 0
    private var finalIndex: Int = 1
    private val units = repo.unitsFor(conversionType)
    private val initialUnit: Unit
        get() = units[initialIndex]
    private val finalUnit: Unit
        get() = units[finalIndex]

    init {
        val state = ViewData(BigDecimal.ONE, units)
        _viewData.postValue(state)
        _selectedUnitsLiveData.postValue(SelectedUnits(initialIndex, finalIndex))
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
        result = repo.convert(
            value = value,
            initial = initialUnit,
            final = finalUnit
        )
        _resultLiveData.postValue(result)
    }

    class Factory(
        private val conversionType: ConversionType,
        private val conversionRepository: ConversionRepository
    ) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ConversionViewModel::class.java)) {
                return ConversionViewModel(conversionType, conversionRepository) as T
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