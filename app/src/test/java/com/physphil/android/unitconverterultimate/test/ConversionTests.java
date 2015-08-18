package com.physphil.android.unitconverterultimate.test;

import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.presenters.ConversionPresenter;
import com.physphil.android.unitconverterultimate.util.Conversions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.physphil.android.unitconverterultimate.models.Unit.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
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
        mPresenter.convert(5.5, area.getUnitById(SQ_KILOMETRES), area.getUnitById(SQ_METRES));
        verify(view, atLeastOnce()).showResult(eq(5500000.0));
        mPresenter.convert(5.5, area.getUnitById(SQ_METRES), area.getUnitById(SQ_CENTIMETRES));
        verify(view, atLeastOnce()).showResult(eq(55000.0));
        mPresenter.convert(5.5, area.getUnitById(SQ_CENTIMETRES), area.getUnitById(HECTARE));
        verify(view, atLeastOnce()).showResult(eq(0.000000055));
        mPresenter.convert(5.5, area.getUnitById(HECTARE), area.getUnitById(SQ_MILE));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(0.0212, DELTA_4));
        mPresenter.convert(5.5, area.getUnitById(SQ_MILE), area.getUnitById(SQ_YARD));
        verify(view, atLeastOnce()).showResult(eq(17036800.0));
        mPresenter.convert(5.5, area.getUnitById(SQ_YARD), area.getUnitById(SQ_FOOT));
        verify(view, atLeastOnce()).showResult(eq(49.5));
        mPresenter.convert(5.5, area.getUnitById(SQ_FOOT), area.getUnitById(SQ_INCH));
        verify(view, atLeastOnce()).showResult(eq(792.0));
        mPresenter.convert(5.5, area.getUnitById(SQ_INCH), area.getUnitById(ACRE));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(0.0000008768, DELTA_10));
        mPresenter.convert(5.5, area.getUnitById(ACRE), area.getUnitById(SQ_KILOMETRES));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(0.0223, DELTA_4));
    }

    @Test
    public void testStorage()
    {
        Conversion storage = mConversions.getById(Conversion.STORAGE);

        mPresenter.convert(4.0, storage.getUnitById(BYTE), storage.getUnitById(BIT));
        verify(view, atLeastOnce()).showResult(eq(32.0));
        mPresenter.convert(4.0, storage.getUnitById(KILOBIT), storage.getUnitById(BYTE));
        verify(view, atLeastOnce()).showResult(eq(512.0));
        mPresenter.convert(4.0, storage.getUnitById(KILOBYTE), storage.getUnitById(KILOBIT));
        verify(view, atLeastOnce()).showResult(eq(32.0));
        mPresenter.convert(4.0, storage.getUnitById(MEGABIT), storage.getUnitById(KILOBYTE));
        verify(view, atLeastOnce()).showResult(eq(512.0));
        mPresenter.convert(4.0, storage.getUnitById(MEGABYTE), storage.getUnitById(MEGABIT));
        verify(view, atLeastOnce()).showResult(eq(32.0));
        mPresenter.convert(4.0, storage.getUnitById(GIGABIT), storage.getUnitById(MEGABYTE));
        verify(view, atLeastOnce()).showResult(eq(512.0));
        mPresenter.convert(4.0, storage.getUnitById(GIGABYTE), storage.getUnitById(GIGABIT));
        verify(view, atLeastOnce()).showResult(eq(32.0));
        mPresenter.convert(4.0, storage.getUnitById(TERABIT), storage.getUnitById(GIGABYTE));
        verify(view, atLeastOnce()).showResult(eq(512.0));
        mPresenter.convert(4.0, storage.getUnitById(TERABIT), storage.getUnitById(TERABYTE));
        verify(view, atLeastOnce()).showResult(eq(0.5));
        mPresenter.convert(4.0, storage.getUnitById(BIT), storage.getUnitById(BYTE));
        verify(view, atLeastOnce()).showResult(eq(0.5));
    }
    
    @Test
    public void testEnergy()
    {
        Conversion energy = mConversions.getById(Conversion.ENERGY);
        
        mPresenter.convert(5.5, energy.getUnitById(JOULE), energy.getUnitById(KILOJOULE));
        verify(view).showResult(eq(0.0055));
        mPresenter.convert(5.5, energy.getUnitById(KILOJOULE), energy.getUnitById(CALORIE));
        verify(view).showResult(AdditionalMatchers.eq(1314.5315, DELTA_4));
        mPresenter.convert(5.5, energy.getUnitById(CALORIE), energy.getUnitById(KILOCALORIE));
        verify(view, atLeastOnce()).showResult(eq(0.0055));
        mPresenter.convert(5.5, energy.getUnitById(KILOCALORIE), energy.getUnitById(BTU));
        verify(view).showResult(AdditionalMatchers.eq(21.8112, DELTA_4));
        mPresenter.convert(5.5, energy.getUnitById(BTU), energy.getUnitById(FT_LBF));
        verify(view).showResult(AdditionalMatchers.eq(4279.9309, DELTA_4));
        mPresenter.convert(5.5, energy.getUnitById(FT_LBF), energy.getUnitById(IN_LBF));
        verify(view).showResult(AdditionalMatchers.eq(66.0000000161, DELTA_10));
        mPresenter.convert(5555555.0, energy.getUnitById(IN_LBF), energy.getUnitById(KILOWATT_HOUR));
        verify(view).showResult(AdditionalMatchers.eq(0.1744, DELTA_4));
        mPresenter.convert(5.5, energy.getUnitById(KILOWATT_HOUR), energy.getUnitById(JOULE));
        verify(view).showResult(eq(19800000.0));
    }

    @Test
    public void testFuelConsumption()
    {
        Conversion fuel = mConversions.getById(Conversion.FUEL);

        mPresenter.convertFuelValue(5.5, fuel.getUnitById(MPG_US), fuel.getUnitById(MPG_UK));
        verify(view).showResult(AdditionalMatchers.eq(6.6052245905, DELTA_10));
        mPresenter.convertFuelValue(5.5, fuel.getUnitById(MPG_UK), fuel.getUnitById(L_100K));
        verify(view).showResult(AdditionalMatchers.eq(51.3601699525, DELTA_10));
        mPresenter.convertFuelValue(5.5, fuel.getUnitById(L_100K), fuel.getUnitById(KM_L));
        verify(view).showResult(AdditionalMatchers.eq(18.1818180813, DELTA_10));
        mPresenter.convertFuelValue(5.5, fuel.getUnitById(KM_L), fuel.getUnitById(MILES_L));
        verify(view).showResult(AdditionalMatchers.eq(3.4175415522, DELTA_10));
        mPresenter.convertFuelValue(5.5, fuel.getUnitById(MILES_L), fuel.getUnitById(MPG_US));
        verify(view).showResult(AdditionalMatchers.eq(20.8197649, DELTA_10));
    }
}
