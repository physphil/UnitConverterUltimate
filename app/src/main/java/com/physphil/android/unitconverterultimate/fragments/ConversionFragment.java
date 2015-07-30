package com.physphil.android.unitconverterultimate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.Unit;
import com.physphil.android.unitconverterultimate.util.Conversions;

/**
 * Base fragment to display units to convert
 * Created by Phizz on 15-07-28.
 */
public final class ConversionFragment extends Fragment
{
    private RadioGroup mGrpFrom, mGrpTo;

    public static ConversionFragment newInstance()
    {
        return new ConversionFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_conversion, container, false);

        mGrpFrom = (RadioGroup) v.findViewById(R.id.radio_group_from);
        mGrpTo = (RadioGroup) v.findViewById(R.id.radio_group_to);
        addUnits();

        return v;
    }

    /**
     * Add units to From and To radio groups
     */
    private void addUnits()
    {
        Conversion c = Conversions.getInstance().getConversion().get(0);
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT);
        lp.bottomMargin = getResources().getDimensionPixelSize(R.dimen.margin_view_small);
        lp.topMargin = getResources().getDimensionPixelSize(R.dimen.margin_view_small);

        for (int i = 0; i < c.getUnits().size(); i++)
        {
            Unit u = c.getUnits().get(i);
            mGrpFrom.addView(getRadioButton(u), lp);
            mGrpTo.addView(getRadioButton(u), lp);
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
        btn.setTag(u);
        btn.setText(u.getLabelResource());
        btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton btn, boolean isChecked)
            {
                if (isChecked)
                {
                    Unit u = (Unit) btn.getTag();
                    Toast.makeText(getActivity(), "selected " + getActivity().getString(u.getLabelResource()), Toast.LENGTH_LONG).show();
                }
            }
        });
        return btn;
    }
}
