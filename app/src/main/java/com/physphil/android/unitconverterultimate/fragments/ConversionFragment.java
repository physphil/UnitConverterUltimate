package com.physphil.android.unitconverterultimate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.Unit;
import com.physphil.android.unitconverterultimate.presenters.ConversionPresenter;
import com.physphil.android.unitconverterultimate.util.Conversions;

/**
 * Base fragment to display units to convert
 * Created by Phizz on 15-07-28.
 */
public final class ConversionFragment extends Fragment implements ConversionPresenter.ConversionView
{
    private static final String ARGS_CONVERSION_ID = "conversion_id";

    private ConversionPresenter mConversionPresenter;
    private RadioGroup mGrpFrom, mGrpTo;
    private EditText mTxtValue, mTxtResult;
    private int mConversionId;

    /**
     * Create a new ConversionFragment to display
     * @param id id of the conversion it will handle
     * @return new ConversionFragment instance
     */
    public static ConversionFragment newInstance(int id)
    {
        ConversionFragment f = new ConversionFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_CONVERSION_ID, id);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mConversionId = getArguments().getInt(ARGS_CONVERSION_ID);
        mConversionPresenter = new ConversionPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_conversion, container, false);

        mTxtValue = (EditText) v.findViewById(R.id.header_value_from);
        mTxtResult = (EditText) v.findViewById(R.id.header_value_to);
        mGrpFrom = (RadioGroup) v.findViewById(R.id.radio_group_from);
        mGrpTo = (RadioGroup) v.findViewById(R.id.radio_group_to);
        addUnits();
        mGrpFrom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                convert();
            }
        });

        mGrpTo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                convert();
            }
        });

        return v;
    }

    /**
     * Convert value from one unit to another
     */
    private void convert()
    {
        double value = Double.parseDouble(mTxtValue.getText().toString());
        mConversionPresenter.convert(value, getCheckedUnit(mGrpFrom), getCheckedUnit(mGrpTo));
    }

    /**
     * Get the Unit associated with the checked button in a radio group
     * @param group RadioGroup which contains the button
     * @return Unit associated with checked button
     */
    private Unit getCheckedUnit(RadioGroup group)
    {
        int index = group.getCheckedRadioButtonId();
        RadioButton btn = (RadioButton) group.findViewById(index);
        return (Unit) btn.getTag();
    }

    /**
     * Add units to From and To radio groups
     */
    private void addUnits()
    {
        Conversion c = Conversions.getInstance().getById(mConversionId);
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        lp.bottomMargin = getResources().getDimensionPixelSize(R.dimen.margin_view_small);
        lp.topMargin = getResources().getDimensionPixelSize(R.dimen.margin_view_small);

        for (int i = 0; i < c.getUnits().size(); i++)
        {
            Unit u = c.getUnits().get(i);
            RadioButton fromUnit = getRadioButton(u);
            if(i == 0)
            {
                fromUnit.setChecked(true);
            }
            mGrpFrom.addView(fromUnit, lp);

            RadioButton toUnit = getRadioButton(u);
            if(i == 1)
            {
                toUnit.setChecked(true);
            }
            mGrpTo.addView(toUnit, lp);
        }
    }

    /**
     * Create Radio Button to display in group
     * @param u unit which the button represents
     * @return RadioButton to display
     */
    private RadioButton getRadioButton(Unit u)
    {
        RadioButton btn = new RadioButton(getActivity());
        btn.setId(u.getId());
        btn.setTag(u);
        btn.setText(u.getLabelResource());
        return btn;
    }

    @Override
    public void showResult(String result)
    {
        mTxtResult.setText(result);
    }
}
