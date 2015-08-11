package com.physphil.android.unitconverterultimate.test;

import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.presenters.ConversionPresenter;
import com.physphil.android.unitconverterultimate.util.Conversions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * JUnit tests for unit conversions
 * Created by pshadlyn on 8/10/2015.
 */
public class ConversionTests
{
    @Mock ConversionPresenter.ConversionView view;

    private final double DELTA_4 = 0.0001;
    private final double DELTA_10 = 0.0000000001;
    private Conversions mConversions;
    private ConversionPresenter mPresenter;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        mConversions = Conversions.getInstance();
        mPresenter = new ConversionPresenter(view);
    }

    @Test
    public void testArea()
    {
        Conversion area = mConversions.getById(Conversions.AREA);

        // Test each fromBase and toBase value
        mPresenter.convert(5.5, area.getUnitById(0), area.getUnitById(1));
        verify(view).showResult(eq(5500000.0));
        mPresenter.convert(5.5, area.getUnitById(1), area.getUnitById(2));
        verify(view).showResult(eq(55000.0));
        mPresenter.convert(5.5, area.getUnitById(2), area.getUnitById(3));
        verify(view).showResult(eq(0.000000055));
        mPresenter.convert(5.5, area.getUnitById(3), area.getUnitById(4));
        verify(view).showResult(AdditionalMatchers.eq(0.0212, DELTA_4));
        mPresenter.convert(5.5, area.getUnitById(4), area.getUnitById(5));
        verify(view).showResult(eq(17036800.0));
        mPresenter.convert(5.5, area.getUnitById(5), area.getUnitById(6));
        verify(view).showResult(eq(49.5));
        mPresenter.convert(5.5, area.getUnitById(6), area.getUnitById(7));
        verify(view).showResult(eq(792.0));
        mPresenter.convert(5.5, area.getUnitById(7), area.getUnitById(8));
        verify(view).showResult(AdditionalMatchers.eq(0.0000008768, DELTA_10));
        mPresenter.convert(5.5, area.getUnitById(8), area.getUnitById(0));
        verify(view).showResult(AdditionalMatchers.eq(0.0223, DELTA_4));
    }
}
