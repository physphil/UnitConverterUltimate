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
        void updateResult(String result);
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
     * Convert a value from one unit to another
     * @param value the value to convert
     * @param from the unit to be converted from
     * @param to the unit to be converted to
     */
    public void convert(double value, Unit from, Unit to)
    {
        Log.d("PS", "Converting from " + from.getId() + " to " + to.getId());
        // TODO do conversion, then update result

        mView.updateResult("");
    }
}
