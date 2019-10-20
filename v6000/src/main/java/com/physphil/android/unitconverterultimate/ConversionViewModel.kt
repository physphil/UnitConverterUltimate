package com.physphil.android.unitconverterultimate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.physphil.android.unitconverterultimate.conversion.ConversionRepository
import com.physphil.android.unitconverterultimate.models.Area
import com.physphil.android.unitconverterultimate.models.Unit

class ConversionViewModel(private val repo: ConversionRepository) : ViewModel() {

    private val _viewState = MutableLiveData<State>()
    val viewState: LiveData<State> = _viewState

    private var fromSelectedUnit: Unit
    private var toSelectedUnit: Unit

    init {
        val units = listOf(
            Area.SqMetre,
            Area.SqKilometre,
            Area.SqCentimetre,
            Area.Hectare,
            Area.SqMile,
            Area.SqYard,
            Area.SqFoot,
            Area.SqInch,
            Area.Acre
        )
        fromSelectedUnit = units[0]
        toSelectedUnit = units[1]

        val state = State(units, 0, 1)
        _viewState.postValue(state)
    }

    class Factory(private val conversionRepository: ConversionRepository) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ConversionViewModel::class.java)) {
                return ConversionViewModel(conversionRepository) as T
            }

            throw IllegalArgumentException("Cannot instantiate ViewModel class with those arguments")
        }
    }

    data class State(
        val units: List<Unit>,
        val fromSelectedIndex: Int,
        val toSelectedIndex: Int
    )
}