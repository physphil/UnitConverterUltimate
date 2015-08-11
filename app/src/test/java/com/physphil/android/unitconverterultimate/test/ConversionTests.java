package com.physphil.android.unitconverterultimate.test;

import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.Unit;
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
        Conversion area = mConversions.getById(Conversion.AREA);

        // Test each fromBase and toBase value
        mPresenter.convert(5.5, area.getUnitById(Unit.SQ_KILOMETRES), area.getUnitById(Unit.SQ_METRES));
        verify(view).showResult(eq(5500000.0));
        mPresenter.convert(5.5, area.getUnitById(Unit.SQ_METRES), area.getUnitById(Unit.SQ_CENTIMETRES));
        verify(view).showResult(eq(55000.0));
        mPresenter.convert(5.5, area.getUnitById(Unit.SQ_CENTIMETRES), area.getUnitById(Unit.HECTARE));
        verify(view).showResult(eq(0.000000055));
        mPresenter.convert(5.5, area.getUnitById(Unit.HECTARE), area.getUnitById(Unit.SQ_MILE));
        verify(view).showResult(AdditionalMatchers.eq(0.0212, DELTA_4));
        mPresenter.convert(5.5, area.getUnitById(Unit.SQ_MILE), area.getUnitById(Unit.SQ_YARD));
        verify(view).showResult(eq(17036800.0));
        mPresenter.convert(5.5, area.getUnitById(Unit.SQ_YARD), area.getUnitById(Unit.SQ_FOOT));
        verify(view).showResult(eq(49.5));
        mPresenter.convert(5.5, area.getUnitById(Unit.SQ_FOOT), area.getUnitById(Unit.SQ_INCH));
        verify(view).showResult(eq(792.0));
        mPresenter.convert(5.5, area.getUnitById(Unit.SQ_INCH), area.getUnitById(Unit.ACRE));
        verify(view).showResult(AdditionalMatchers.eq(0.0000008768, DELTA_10));
        mPresenter.convert(5.5, area.getUnitById(Unit.ACRE), area.getUnitById(Unit.SQ_KILOMETRES));
        verify(view).showResult(AdditionalMatchers.eq(0.0223, DELTA_4));
    }
}
