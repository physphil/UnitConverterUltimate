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

    private final double DOUBLE_DELTA = 0.0001;
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

        // Test fromBase values
        mPresenter.convert(5.5, area.getUnitById(0), area.getUnitById(0));
        verify(view).showResult(eq(5.5));
        mPresenter.convert(5.5, area.getUnitById(0), area.getUnitById(1));
        verify(view).showResult(eq(5500000.0));
        mPresenter.convert(5.5, area.getUnitById(0), area.getUnitById(2));
        verify(view).showResult(eq(55000000000.0));
        mPresenter.convert(5.5, area.getUnitById(0), area.getUnitById(3));
        verify(view).showResult(eq(550.0));
        mPresenter.convert(5.5, area.getUnitById(0), area.getUnitById(4));
        verify(view).showResult(AdditionalMatchers.eq(2.1236, DOUBLE_DELTA));
        mPresenter.convert(5.5, area.getUnitById(0), area.getUnitById(5));
        verify(view).showResult(AdditionalMatchers.eq(6577945.2546559, DOUBLE_DELTA));
        mPresenter.convert(5.5, area.getUnitById(0), area.getUnitById(6));
        verify(view).showResult(AdditionalMatchers.eq(59201507.291903, DOUBLE_DELTA));
        mPresenter.convert(5.5, area.getUnitById(0), area.getUnitById(7));
        verify(view).showResult(AdditionalMatchers.eq(8525017050.0341, DOUBLE_DELTA));
        mPresenter.convert(5.5, area.getUnitById(0), area.getUnitById(8));
        verify(view).showResult(AdditionalMatchers.eq(1359.0795980694, DOUBLE_DELTA));

        // Test toBase values
        mPresenter.convert(5.5, area.getUnitById(1), area.getUnitById(0));
        verify(view).showResult(eq(0.0000055));
        mPresenter.convert(5.5, area.getUnitById(2), area.getUnitById(0));
        verify(view).showResult(eq(0.00000000055));
        mPresenter.convert(5.5, area.getUnitById(3), area.getUnitById(0));
        verify(view).showResult(eq(0.055));
        mPresenter.convert(5.5, area.getUnitById(4), area.getUnitById(0));
        verify(view).showResult(AdditionalMatchers.eq(14.244934606848, DOUBLE_DELTA));
        mPresenter.convert(5.5, area.getUnitById(5), area.getUnitById(0));
        verify(view).showResult(AdditionalMatchers.eq(0.00000459870048, DOUBLE_DELTA));
        mPresenter.convert(5.5, area.getUnitById(6), area.getUnitById(0));
        verify(view).showResult(AdditionalMatchers.eq(0.00000051096672, DOUBLE_DELTA));
        mPresenter.convert(5.5, area.getUnitById(7), area.getUnitById(0));
        verify(view).showResult(AdditionalMatchers.eq(0.00000000354838, DOUBLE_DELTA));
        mPresenter.convert(5.5, area.getUnitById(8), area.getUnitById(0));
        verify(view).showResult(AdditionalMatchers.eq(0.0222577103232, DOUBLE_DELTA));
    }
}
