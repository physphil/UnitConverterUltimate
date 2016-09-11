package com.physphil.android.unitconverterultimate.presenters;

import android.content.Context;

import com.physphil.android.unitconverterultimate.models.Conversion;

/**
 * View methods to be implemented for Conversion Fragment
 * Created by Phizz on 16-09-10.
 */
public interface ConversionView
{
    Context getContext();
    void showUnitsList(Conversion conversion);
    void showProgressCircle();
    void showLoadingError(int message);
    void showResult(double result);
    void refreshCurrencyConversion();
}
