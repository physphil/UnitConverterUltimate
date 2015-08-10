package com.physphil.android.unitconverterultimate.test;

import com.physphil.android.unitconverterultimate.models.TemperatureUnit;
import com.physphil.android.unitconverterultimate.presenters.ConversionPresenter;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit tests for unit conversions
 * Created by pshadlyn on 8/10/2015.
 */
public class ConversionTests
{
    private static ConversionPresenter mPresenter;

    @BeforeClass
    public static void setup()
    {
        mPresenter = new ConversionPresenter(new ConversionPresenter.ConversionView()
        {
            @Override
            public void showResult(double result)
            {
                assertEquals(5.0, result, 0.5);
            }
        });
    }

    @Test
    public void testArea()
    {
        mPresenter.convert(5.0, new TemperatureUnit(1, 0), new TemperatureUnit(1, 0));
    }
}
