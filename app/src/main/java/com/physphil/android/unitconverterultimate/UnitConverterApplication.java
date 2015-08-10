package com.physphil.android.unitconverterultimate;

import android.app.Application;

import com.physphil.android.unitconverterultimate.util.Conversions;

/**
 * Application class
 * Created by pshadlyn on 8/10/2015.
 */
public class UnitConverterApplication extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        Conversions.initialize();
    }
}
