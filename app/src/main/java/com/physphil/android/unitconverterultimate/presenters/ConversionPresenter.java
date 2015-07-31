package com.physphil.android.unitconverterultimate.presenters;

import android.util.Log;

import com.physphil.android.unitconverterultimate.models.Unit;

/**
 * Presenter to handle unit conversions
 * Created by Phizz on 15-07-29.
 */
public class ConversionPresenter
{
    public interface ConversionView
    {
        void showResult(double result);
    }

    private ConversionView mView;

    /**
     * Create a new ConversionPresenter
     * @param mView ConversionView callback
     */
    public ConversionPresenter(ConversionView mView)
    {
        this.mView = mView;
    }

    /**
     * Convert a Fuel Consumption value from one unit to another
     * @param value the value to convert
     * @param from the unit to be converted from
     * @param to the unit to be converted to
     */
    public void convertFuelValue(double value, Unit from, Unit to)
    {
        double result = value;
        if(from.getId() != to.getId())
        {
            if (from.getId() == 2)   // Litres/100km
            {
                Log.d("PS", "from = " + from.getConversionFromBaseUnit());
                Log.d("PS", "value = " + value);
                Log.d("PS", "to = " + to.getConversionToBaseUnit());
                result = (from.getConversionToBaseUnit() / value) * to.getConversionFromBaseUnit();
            }
            else if (to.getId() == 2)   // Litres/100km
            {
                result = to.getConversionFromBaseUnit() / (value * from.getConversionToBaseUnit());
            }
            else
            {
                double multiplier = from.getConversionToBaseUnit() * to.getConversionFromBaseUnit();
                result = value * multiplier;
            }
        }

        mView.showResult(result);
    }

    /**
     * Convert a value from one unit to another
     * @param value the value to convert
     * @param from the unit to be converted from
     * @param to the unit to be converted to
     */
    public void convert(double value, Unit from, Unit to)
    {
        double result = value;
        if(from.getId() != to.getId())
        {
            double multiplier = from.getConversionToBaseUnit() * to.getConversionFromBaseUnit();
            result = value * multiplier;
        }
        mView.showResult(result);
    }
}
