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

    private val _viewState = MutableLiveData<State>()
    val viewState: LiveData<State> = _viewState

    private val _resultLiveData = MutableLiveData<BigDecimal>()
    val resultLiveData: LiveData<BigDecimal> = _resultLiveData

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
        val state = State(BigDecimal.ONE, units, initialIndex, finalIndex)
        _viewState.postValue(state)
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

    data class State(
        val value: BigDecimal,
        val units: List<Unit>,
        val initialIndex: Int,
        val finalIndex: Int
    )
}