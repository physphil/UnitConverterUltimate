package com.physphil.android.unitconverterultimate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.physphil.android.unitconverterultimate.conversion.ConversionRepository
import kotlinx.android.synthetic.main.fragment_conversion.*

class ConversionFragment : Fragment() {

    private lateinit var conversionViewModel: ConversionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_conversion, container, false)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val factory = ConversionViewModel.Factory(ConversionRepository())
        conversionViewModel = ViewModelProviders.of(this, factory).get(ConversionViewModel::class.java)
        conversionViewModel.viewState.observe(this, Observer {
            populateViewState(it)
        })
    }

    // FIXME: move to View class
    private fun populateViewState(state: ConversionViewModel.State) {
        state.units.forEach { unit ->
            val fromButton = RadioButton(this.context).apply {
                text = getString(unit.displayStringResId)
            }
            fromGroup.addView(fromButton)

            val toButton = RadioButton(this.context).apply {
                text = getString(unit.displayStringResId)
            }
            toGroup.addView(toButton)
        }
        fromGroup.check(fromGroup[state.fromSelectedIndex].id)
        toGroup.check(toGroup[state.toSelectedIndex].id)
    }
}