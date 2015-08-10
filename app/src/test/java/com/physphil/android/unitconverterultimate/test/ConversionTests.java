package com.physphil.android.unitconverterultimate.test;

import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.presenters.ConversionPresenter;
import com.physphil.android.unitconverterultimate.util.Conversions;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit tests for unit conversions
 * Created by pshadlyn on 8/10/2015.
 */
public class ConversionTests
{
    private static Conversions mConversions;
    private static ConversionPresenter mPresenter;

    @BeforeClass
    public static void setup()
    {
        mConversions = Conversions.getInstance();
        mPresenter = new ConversionPresenter(new ConversionPresenter.ConversionView()
        {
            @Override
            public void showResult(double result)
            {
                assertEquals(5.0, result, 0.1);
            }
        });
    }

    @Test
    public void testArea()
    {
        Conversion area = mConversions.getById(Conversions.AREA);
        mPresenter.convert(5.0, area.getUnitById(0), area.getUnitById(0));
    }
}
