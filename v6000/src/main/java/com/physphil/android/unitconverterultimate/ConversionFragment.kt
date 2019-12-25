package com.physphil.android.unitconverterultimate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.get
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.physphil.android.unitconverterultimate.conversion.ConversionRepository
import com.physphil.android.unitconverterultimate.models.ConversionType
import kotlinx.android.synthetic.main.fragment_conversion.*
import java.math.BigDecimal

class ConversionFragment : Fragment() {

    companion object {
        const val ARGS_CONVERSION_TYPE = "argConversionType"
    }

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
        val conversionType = arguments?.getSerializable(ARGS_CONVERSION_TYPE) as? ConversionType
            ?: throw IllegalArgumentException("Proper conversion type not specified when starting fragment")

        val factory = ConversionViewModel.Factory(conversionType, ConversionRepository())
        conversionViewModel = ViewModelProviders.of(this, factory).get(ConversionViewModel::class.java)
        conversionViewModel.init(this)
        initViewListeners()
    }

    private fun initViewListeners() {
        valueTextView.doOnTextChanged { text, _, _, _ ->
            val input = when {
                text.isNullOrEmpty() -> BigDecimal.ZERO
                else -> BigDecimal(text.toString())
            }
            conversionViewModel.updateValue(input)
        }

        initialRadioGroupView.setOnCheckedChangeListener { group, checkedId ->
            conversionViewModel.updateInitialIndex(checkedId)
        }

        finalRadioGroupView.setOnCheckedChangeListener { _, checkedId ->
            conversionViewModel.updateFinalIndex(checkedId)
        }

        swapUnitsButtonView.setOnClickListener {
            conversionViewModel.swapUnits()
        }
    }

    // FIXME: move to View class
    private fun renderView(state: ConversionViewModel.ViewData) {
        state.units.forEachIndexed { index, unit  ->
            val initialButton = RadioButton(this.context).apply {
                id = index
                text = getString(unit.displayStringResId)
            }
            initialRadioGroupView.addView(initialButton)

            val finalButton = RadioButton(this.context).apply {
                id = index
                text = getString(unit.displayStringResId)
            }
            finalRadioGroupView.addView(finalButton)
        }

        valueTextView.setText(state.value.toPlainString())
    }

    private fun ConversionViewModel.init(lifecycleOwner: LifecycleOwner) {
        viewData.observe(lifecycleOwner, Observer {
            renderView(it)
        })

        selectedUnitsLiveData.observe(lifecycleOwner, Observer {
            initialRadioGroupView.checkIndex(it.initialIndex)
            finalRadioGroupView.checkIndex(it.finalIndex)
        })

        resultLiveData.observe(lifecycleOwner, Observer {
            resultTextView.text = it.toPlainString()
        })
    }

    private fun RadioGroup.checkIndex(index: Int) {
        this.check(this[index].id)
    }
}