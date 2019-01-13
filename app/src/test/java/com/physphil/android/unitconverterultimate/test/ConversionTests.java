/*
 * Copyright 2016 Phil Shadlyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physphil.android.unitconverterultimate.test;

import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.presenters.ConversionPresenter;
import com.physphil.android.unitconverterultimate.presenters.ConversionView;
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
    @Mock
    ConversionView view;

    private final double DELTA_4 = 0.0001;
    private final double DELTA_6 = 0.000001;
    private final double DELTA_7 = 0.0000001;
    private final double DELTA_9 = 0.000000001;
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
        mPresenter.convert(1.0, storage.getUnitById(GIGABYTE), storage.getUnitById(MEGABYTE));
        verify(view, atLeastOnce()).showResult(eq(1024.0));
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
        mPresenter.convertFuelValue(0, fuel.getUnitById(L_100K), fuel.getUnitById(MPG_UK));
        verify(view, atLeastOnce()).showResult(0.0);
        mPresenter.convertFuelValue(0, fuel.getUnitById(MPG_UK), fuel.getUnitById(L_100K));
        verify(view, atLeastOnce()).showResult(0.0);
    }

    @Test
    public void testLength()
    {
        Conversion length = mConversions.getById(Conversion.LENGTH);

        mPresenter.convert(5.5, length.getUnitById(KILOMETRE), length.getUnitById(MILE));
        verify(view).showResult(AdditionalMatchers.eq(3.4175415573, DELTA_10));
        mPresenter.convert(5.5, length.getUnitById(MILE), length.getUnitById(METRE));
        verify(view).showResult(eq(8851.392));
        mPresenter.convert(5.5, length.getUnitById(METRE), length.getUnitById(CENTIMETRE));
        verify(view).showResult(eq(550.0));
        mPresenter.convert(5.5, length.getUnitById(CENTIMETRE), length.getUnitById(MILLIMETRE));
        verify(view).showResult(eq(55.0));
        mPresenter.convert(5.5, length.getUnitById(MILLIMETRE), length.getUnitById(MICROMETRE));
        verify(view).showResult(eq(5500.0));
        mPresenter.convert(5.5, length.getUnitById(MICROMETRE), length.getUnitById(NANOMETRE));
        verify(view, atLeastOnce()).showResult(eq(5500.0));
        mPresenter.convert(5558, length.getUnitById(NANOMETRE), length.getUnitById(YARD));
        verify(view).showResult(AdditionalMatchers.eq(0.0000060783, DELTA_10));
        mPresenter.convert(5.5, length.getUnitById(YARD), length.getUnitById(FEET));
        verify(view).showResult(eq(16.5));
        mPresenter.convert(5.5, length.getUnitById(FEET), length.getUnitById(INCH));
        verify(view).showResult(AdditionalMatchers.eq(66.0, DELTA_10));
        mPresenter.convert(5.5, length.getUnitById(MILLIMETRE), length.getUnitById(MICROMETRE));
        verify(view, atLeastOnce()).showResult(eq(5500.0));
        mPresenter.convert(5.5, length.getUnitById(INCH), length.getUnitById(NAUTICAL_MILE));
        verify(view).showResult(AdditionalMatchers.eq(0.000075432, DELTA_10));
        mPresenter.convert(5.5, length.getUnitById(NAUTICAL_MILE), length.getUnitById(FURLONG));
        verify(view).showResult(AdditionalMatchers.eq(50.634295713, DELTA_10));
        mPresenter.convert(123456789.0, length.getUnitById(FURLONG), length.getUnitById(LIGHT_YEAR));
        verify(view).showResult(AdditionalMatchers.eq(0.0000026251, DELTA_10));
        mPresenter.convert(5.5, length.getUnitById(LIGHT_YEAR), length.getUnitById(KILOMETRE));
        verify(view).showResult(eq(52034017599194.4));
    }

    @Test
    public void testMass()
    {
        Conversion mass = mConversions.getById(Conversion.MASS);

        mPresenter.convert(5.5, mass.getUnitById(KILOGRAM), mass.getUnitById(POUND));
        verify(view).showResult(AdditionalMatchers.eq(12.1254244202, DELTA_10));
        mPresenter.convert(5.5, mass.getUnitById(POUND), mass.getUnitById(GRAM));
        verify(view).showResult(AdditionalMatchers.eq(2494.758035, DELTA_6));
        mPresenter.convert(5.5, mass.getUnitById(GRAM), mass.getUnitById(MILLIGRAM));
        verify(view).showResult(eq(5500.0));
        mPresenter.convert(5.5, mass.getUnitById(MILLIGRAM), mass.getUnitById(OUNCE));
        verify(view).showResult(AdditionalMatchers.eq(0.0001940068, DELTA_10));
        mPresenter.convert(5.5, mass.getUnitById(OUNCE), mass.getUnitById(GRAIN));
        verify(view).showResult(eq(2406.25));
        mPresenter.convert(5.5, mass.getUnitById(GRAIN), mass.getUnitById(STONE));
        verify(view).showResult(AdditionalMatchers.eq(0.0000561224, DELTA_10));
        mPresenter.convert(5.5, mass.getUnitById(STONE), mass.getUnitById(METRIC_TON));
        verify(view).showResult(AdditionalMatchers.eq(0.0349266125, DELTA_10));
        mPresenter.convert(5.5, mass.getUnitById(METRIC_TON), mass.getUnitById(SHORT_TON));
        verify(view).showResult(AdditionalMatchers.eq(6.0627122101, DELTA_10));
        mPresenter.convert(5.5, mass.getUnitById(SHORT_TON), mass.getUnitById(LONG_TON));
        verify(view).showResult(AdditionalMatchers.eq(4.9107142857, DELTA_10));
        mPresenter.convert(5.5, mass.getUnitById(LONG_TON), mass.getUnitById(KILOGRAM));
        verify(view).showResult(AdditionalMatchers.eq(5588.2579984, DELTA_7));
    }

    @Test
    public void testPower()
    {
        Conversion power = mConversions.getById(Conversion.POWER);

        mPresenter.convert(5.5, power.getUnitById(WATT), power.getUnitById(KILOWATT));
        verify(view).showResult(eq(0.0055));
        mPresenter.convert(5.5, power.getUnitById(KILOWATT), power.getUnitById(MEGAWATT));
        verify(view, atLeastOnce()).showResult(eq(0.0055));
        mPresenter.convert(5.5, power.getUnitById(MEGAWATT), power.getUnitById(HP));
        verify(view).showResult(AdditionalMatchers.eq(7477.9188951715, DELTA_10));
        mPresenter.convert(5.5, power.getUnitById(HP), power.getUnitById(HP_UK));
        verify(view).showResult(AdditionalMatchers.eq(5.4247603884, DELTA_10));
        mPresenter.convert(5.5, power.getUnitById(HP_UK), power.getUnitById(FT_LBF_S));
        verify(view).showResult(eq(3025.0));
        mPresenter.convert(5.5, power.getUnitById(FT_LBF_S), power.getUnitById(CALORIE_S));
        verify(view).showResult(AdditionalMatchers.eq(1.7810735444, DELTA_10));
        mPresenter.convert(5.5, power.getUnitById(CALORIE_S), power.getUnitById(BTU_S));
        verify(view).showResult(AdditionalMatchers.eq(0.021825764, DELTA_9));
        mPresenter.convert(5.5, power.getUnitById(BTU_S), power.getUnitById(KVA));
        verify(view).showResult(AdditionalMatchers.eq(5.8028071894, DELTA_10));
        mPresenter.convert(5.5, power.getUnitById(KVA), power.getUnitById(WATT));
        verify(view).showResult(eq(5500.0));
    }

    @Test
    public void testPressure()
    {
        Conversion pressure = mConversions.getById(Conversion.PRESSURE);

        mPresenter.convert(5.5, pressure.getUnitById(MEGAPASCAL), pressure.getUnitById(KILOPASCAL));
        verify(view).showResult(eq(5500.0));
        mPresenter.convert(5.5, pressure.getUnitById(KILOPASCAL), pressure.getUnitById(PASCAL));
        verify(view, atLeastOnce()).showResult(eq(5500.0));
        mPresenter.convert(5.5, pressure.getUnitById(PASCAL), pressure.getUnitById(BAR));
        verify(view).showResult(eq(0.000055));
        mPresenter.convert(5.5, pressure.getUnitById(BAR), pressure.getUnitById(PSI));
        verify(view).showResult(AdditionalMatchers.eq(79.7707557516, DELTA_10));
        mPresenter.convert(5.5, pressure.getUnitById(PSI), pressure.getUnitById(PSF));
        verify(view).showResult(eq(792.0));
        mPresenter.convert(5.5, pressure.getUnitById(PSF), pressure.getUnitById(ATMOSPHERE));
        verify(view).showResult(AdditionalMatchers.eq(0.0025989778, DELTA_10));
        mPresenter.convert(5.5, pressure.getUnitById(ATMOSPHERE), pressure.getUnitById(MMHG));
        verify(view).showResult(AdditionalMatchers.eq(4179.9994044909, DELTA_10));
        mPresenter.convert(5.5, pressure.getUnitById(MMHG), pressure.getUnitById(TORR));
        verify(view).showResult(AdditionalMatchers.eq(5.5000007836, DELTA_10));
        mPresenter.convert(5.5, pressure.getUnitById(TORR), pressure.getUnitById(TECHNICAL_ATMOSPHERE));
        verify(view).showResult(AdditionalMatchers.eq(0.007477303934736015598438, DELTA_10));
        mPresenter.convert(5.5, pressure.getUnitById(TECHNICAL_ATMOSPHERE), pressure.getUnitById(MEGAPASCAL));
        verify(view).showResult((0.53936575));
    }

    @Test
    public void testSpeed()
    {
        Conversion speed = mConversions.getById(Conversion.SPEED);

        mPresenter.convert(5.5, speed.getUnitById(KM_HR), speed.getUnitById(MPH));
        verify(view).showResult(AdditionalMatchers.eq(3.4175415573, DELTA_10));
        mPresenter.convert(5.5, speed.getUnitById(MPH), speed.getUnitById(M_S));
        verify(view).showResult(eq(2.45872));
        mPresenter.convert(5.5, speed.getUnitById(M_S), speed.getUnitById(FPS));
        verify(view).showResult(AdditionalMatchers.eq(18.0446194226, DELTA_10));
        mPresenter.convert(5.5, speed.getUnitById(FPS), speed.getUnitById(KNOT));
        verify(view).showResult(AdditionalMatchers.eq(3.2586609071, DELTA_10));
        mPresenter.convert(5.5, speed.getUnitById(KNOT), speed.getUnitById(KM_HR));
        verify(view).showResult(AdditionalMatchers.eq(10.1860, DELTA_4));
    }

    @Test
    public void testTemperature()
    {
        Conversion temp = mConversions.getById(Conversion.TEMPERATURE);

        mPresenter.convertTemperatureValue(5.5, temp.getUnitById(CELSIUS), temp.getUnitById(FAHRENHEIT));
        verify(view).showResult(eq(41.9));
        mPresenter.convertTemperatureValue(5.5, temp.getUnitById(FAHRENHEIT), temp.getUnitById(KELVIN));
        verify(view).showResult(AdditionalMatchers.eq(258.4277777778, DELTA_10));
        mPresenter.convertTemperatureValue(5.5, temp.getUnitById(KELVIN), temp.getUnitById(RANKINE));
        verify(view).showResult(eq(9.9));
        mPresenter.convertTemperatureValue(5.5, temp.getUnitById(RANKINE), temp.getUnitById(DELISLE));
        verify(view).showResult(AdditionalMatchers.eq(555.1416666667, DELTA_10));
        mPresenter.convertTemperatureValue(5.5, temp.getUnitById(DELISLE), temp.getUnitById(NEWTON));
        verify(view).showResult(eq(31.79));
        mPresenter.convertTemperatureValue(5.5, temp.getUnitById(NEWTON), temp.getUnitById(REAUMUR));
        verify(view).showResult(AdditionalMatchers.eq(13.3333333333, DELTA_10));
        mPresenter.convertTemperatureValue(5.5, temp.getUnitById(REAUMUR), temp.getUnitById(ROMER));
        verify(view).showResult(eq(11.109375));
        mPresenter.convertTemperatureValue(10.0, temp.getUnitById(GAS_MARK), temp.getUnitById(CELSIUS));
        verify(view).showResult(eq(260.0));
    }

    @Test
    public void testTime()
    {
        Conversion time = mConversions.getById(Conversion.TIME);

        mPresenter.convert(5.0, time.getUnitById(YEAR), time.getUnitById(MONTH));
        verify(view).showResult(AdditionalMatchers.eq(59.9999994, DELTA_7));
        mPresenter.convert(5.0, time.getUnitById(MONTH), time.getUnitById(WEEK));
        verify(view).showResult(AdditionalMatchers.eq(21.7261904762, DELTA_10));
        mPresenter.convert(5.0, time.getUnitById(WEEK), time.getUnitById(DAY));
        verify(view).showResult(eq(35.0));
        mPresenter.convert(5.0, time.getUnitById(DAY), time.getUnitById(HOUR));
        verify(view).showResult(eq(120.0));
        mPresenter.convert(5.0, time.getUnitById(HOUR), time.getUnitById(MINUTE));
        verify(view).showResult(eq(300.0));
        mPresenter.convert(5.0, time.getUnitById(MINUTE), time.getUnitById(SECOND));
        verify(view, atLeastOnce()).showResult(eq(300.0));
        mPresenter.convert(5.0, time.getUnitById(SECOND), time.getUnitById(MILLISECOND));
        verify(view).showResult(eq(5000.0));
        mPresenter.convert(5.0, time.getUnitById(MILLISECOND), time.getUnitById(NANOSECOND));
        verify(view).showResult(eq(5000000.0));
        mPresenter.convert(5.0, time.getUnitById(NANOSECOND), time.getUnitById(MILLISECOND));
        verify(view).showResult(AdditionalMatchers.eq(0.000005, DELTA_7));
        mPresenter.convert(5.0, time.getUnitById(MONTH), time.getUnitById(YEAR));
        verify(view).showResult(AdditionalMatchers.eq(0.4166666667, DELTA_10));
    }

    @Test
    public void testTorque()
    {
        Conversion torque = mConversions.getById(Conversion.TORQUE);

        mPresenter.convert(5.5, torque.getUnitById(N_M), torque.getUnitById(FT_LBF));
        verify(view).showResult(AdditionalMatchers.eq(4.056591822, DELTA_10));
        mPresenter.convert(5.5, torque.getUnitById(FT_LBF), torque.getUnitById(IN_LBF));
        verify(view).showResult(AdditionalMatchers.eq(66.0000000161, DELTA_10));
        mPresenter.convert(5.5, torque.getUnitById(IN_LBF), torque.getUnitById(N_M));
        verify(view).showResult(AdditionalMatchers.eq(0.6214165597, DELTA_10));
    }

    @Test
    public void testVolume()
    {
        Conversion volume = mConversions.getById(Conversion.VOLUME);

        mPresenter.convert(5.5, volume.getUnitById(TEASPOON), volume.getUnitById(TABLESPOON));
        verify(view).showResult(AdditionalMatchers.eq(1.8333333334, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(TABLESPOON), volume.getUnitById(CUP));
        verify(view).showResult(AdditionalMatchers.eq(0.34375, DELTA_6));
        mPresenter.convert(5.5, volume.getUnitById(CUP), volume.getUnitById(FLUID_OUNCE));
        verify(view).showResult(eq(44.0));
        mPresenter.convert(5.5, volume.getUnitById(FLUID_OUNCE), volume.getUnitById(FLUID_OUNCE_UK));
        verify(view).showResult(AdditionalMatchers.eq(5.7246350193, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(FLUID_OUNCE_UK), volume.getUnitById(PINT));
        verify(view).showResult(AdditionalMatchers.eq(0.3302612295, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(PINT), volume.getUnitById(PINT_UK));
        verify(view).showResult(AdditionalMatchers.eq(4.5797080155, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(PINT_UK), volume.getUnitById(QUART));
        verify(view).showResult(AdditionalMatchers.eq(3.3026122951, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(QUART), volume.getUnitById(QUART_UK));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(4.5797080155, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(QUART_UK), volume.getUnitById(GALLON));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(1.6513061476, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(GALLON), volume.getUnitById(GALLON_UK));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(4.5797080155, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(GALLON_UK), volume.getUnitById(BARREL));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(0.2096896695, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(BARREL), volume.getUnitById(BARREL_UK));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(4.0072445135, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(BARREL_UK), volume.getUnitById(MILLILITRE));
        verify(view).showResult(AdditionalMatchers.eq(900125.82, DELTA_6));
        mPresenter.convert(5.5, volume.getUnitById(MILLILITRE), volume.getUnitById(LITRE));
        verify(view).showResult(eq(0.0055));
        mPresenter.convert(5.5, volume.getUnitById(LITRE), volume.getUnitById(CUBIC_CM));
        verify(view).showResult(eq(5500.0));
        mPresenter.convert(5.5, volume.getUnitById(CUBIC_CM), volume.getUnitById(CUBIC_M));
        verify(view).showResult(eq(0.0000055));
        mPresenter.convert(5.5, volume.getUnitById(CUBIC_M), volume.getUnitById(CUBIC_INCH));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(335630.592521028, DELTA_9));
        mPresenter.convert(5.5, volume.getUnitById(CUBIC_INCH), volume.getUnitById(CUBIC_FOOT));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(0.0031828704, DELTA_9));
        mPresenter.convert(5.5, volume.getUnitById(CUBIC_FOOT), volume.getUnitById(CUBIC_YARD));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(0.2037037007, DELTA_10));
        mPresenter.convert(5.5, volume.getUnitById(CUBIC_YARD), volume.getUnitById(TEASPOON));
        verify(view, atLeastOnce()).showResult(AdditionalMatchers.eq(853138.298312504, DELTA_9));
    }
}