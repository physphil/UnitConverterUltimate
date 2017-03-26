/*
 * Copyright 2017 Phil Shadlyn
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

package com.physphil.android.unitconverterultimate.util;

import android.content.Context;

import com.physphil.android.unitconverterultimate.Preferences;
import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.api.models.CurrencyResponse;
import com.physphil.android.unitconverterultimate.api.models.Rates;
import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.TemperatureUnit;
import com.physphil.android.unitconverterultimate.models.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.physphil.android.unitconverterultimate.models.Unit.*;

/**
 * Contains all conversion info
 */
public final class Conversions {

    private static Conversions mInstance = null;
    private Map<Integer, Conversion> mConversions = new HashMap<>();
    private boolean mCurrencyUpdated;

    /**
     * Get instance of Conversions objects, which contains mapping of type and Conversion object
     *
     * @return Conversions mInstance
     */
    public static Conversions getInstance() {
        //Create singleton to contain all conversions
        if (mInstance == null) {
            mInstance = new Conversions();
        }
        return mInstance;
    }

    private Conversions() {
        //Fill conversions HashMap
        getAreaConversions();
        getCookingConversions();
        getStorageConversions();
        getEnergyConversions();
        getFuelConversions();
        getLengthConversions();
        getMassConversions();
        getPowerConversions();
        getPressureConversions();
        getSpeedConversions();
        getTemperatureConversions();
        getTimeConversions();
        getTorqueConversions();
        getVolumeConversions();
        mCurrencyUpdated = false;
    }

    /**
     * Get Conversion object by its id
     *
     * @param id id of conversion
     * @return Conversion object
     */
    public Conversion getById(@Conversion.id int id) {
        return mConversions.get(id);
    }

    /**
     * Method to add conversion to hashmap, encapsulated in a separate method for type safety
     *
     * @param id         conversion id
     * @param conversion Conversion object
     */
    private void addConversion(@Conversion.id int id, Conversion conversion) {
        mConversions.put(id, conversion);
    }

    private void getAreaConversions() {
        //Base unit: square metre

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(SQ_KILOMETRES, R.string.sq_kilometre, 1000000.0, 0.000001));
        units.add(new Unit(SQ_METRES, R.string.sq_metre, 1.0, 1.0));
        units.add(new Unit(SQ_CENTIMETRES, R.string.sq_centimetre, 0.0001, 10000.0));
        units.add(new Unit(HECTARE, R.string.hectare, 10000.0, 0.0001));
        units.add(new Unit(SQ_MILE, R.string.sq_mile, 2589988.110336, 0.000000386102158542445847));
        units.add(new Unit(SQ_YARD, R.string.sq_yard, 0.83612736, 1.19599004630108026));
        units.add(new Unit(SQ_FOOT, R.string.sq_foot, 0.09290304, 10.7639104167097223));
        units.add(new Unit(SQ_INCH, R.string.sq_inch, 0.00064516, 1550.00310000620001));
        units.add(new Unit(ACRE, R.string.acre, 4046.8564224, 0.000247105381467165342));
        addConversion(Conversion.AREA, new Conversion(Conversion.AREA, R.string.area, units));
    }

    private void getCookingConversions() {
        // Base unit - cubic metre
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(TEASPOON, R.string.teaspoon, 0.0000049289215938, 202884.136211058));
        units.add(new Unit(TABLESPOON, R.string.tablespoon, 0.0000147867647812, 67628.045403686));
        units.add(new Unit(CUP, R.string.cup, 0.0002365882365, 4226.7528377304));
        units.add(new Unit(FLUID_OUNCE, R.string.fluid_ounce, 0.0000295735295625, 33814.0227018429972));
        units.add(new Unit(FLUID_OUNCE_UK, R.string.fluid_ounce_uk, 0.0000284130625, 35195.07972785404600437));
        units.add(new Unit(PINT, R.string.pint, 0.000473176473, 2113.37641886518732));
        units.add(new Unit(PINT_UK, R.string.pint_uk, 0.00056826125, 1759.753986392702300218));
        units.add(new Unit(QUART, R.string.quart, 0.000946352946, 1056.68820943259366));
        units.add(new Unit(QUART_UK, R.string.quart_uk, 0.0011365225, 879.8769931963511501092));
        units.add(new Unit(GALLON, R.string.gallon, 0.003785411784, 264.172052358148415));
        units.add(new Unit(GALLON_UK, R.string.gallon_uk, 0.00454609, 219.9692482990877875273));
        units.add(new Unit(MILLILITRE, R.string.millilitre, 0.000001, 1000000.0));
        units.add(new Unit(LITRE, R.string.litre, 0.001, 1000.0));
        addConversion(Conversion.COOKING, new Conversion(Conversion.COOKING, R.string.cooking, units));
    }

    public void updateCurrencyConversions(Context context) {
        // Base unit - Euro
        final List<Unit> units = new ArrayList<>();
        final CurrencyResponse response = Preferences.getInstance(context).getLatestCurrency();
        if (Preferences.getInstance(context).hasLatestCurrency() && response != null) {
            Rates currency = response.getRates();
            units.add(new Unit(USD, R.string.usd, 1 / currency.getUsd(), currency.getUsd()));
            units.add(new Unit(AUD, R.string.aud, 1 / currency.getAud(), currency.getAud()));
            units.add(new Unit(GBP, R.string.gbp, 1 / currency.getGbp(), currency.getGbp()));
            units.add(new Unit(BRL, R.string.brl, 1 / currency.getBrl(), currency.getBrl()));
            units.add(new Unit(BGN, R.string.bgn, 1 / currency.getBgn(), currency.getBgn()));
            units.add(new Unit(CDN, R.string.cdn, 1 / currency.getCad(), currency.getCad()));
            units.add(new Unit(CNY, R.string.cny, 1 / currency.getCny(), currency.getCny()));
            units.add(new Unit(HRK, R.string.hrk, 1 / currency.getHrk(), currency.getHrk()));
            units.add(new Unit(CZK, R.string.czk, 1 / currency.getCzk(), currency.getCzk()));
            units.add(new Unit(DKK, R.string.dkk, 1 / currency.getDkk(), currency.getDkk()));
            units.add(new Unit(EUR, R.string.eur, 1.0, 1.0));
            units.add(new Unit(HKD, R.string.hkd, 1 / currency.getHkd(), currency.getHkd()));
            units.add(new Unit(HUF, R.string.huf, 1 / currency.getHuf(), currency.getHuf()));
            units.add(new Unit(INR, R.string.inr, 1 / currency.getInr(), currency.getInr()));
            units.add(new Unit(IDR, R.string.idr, 1 / currency.getIdr(), currency.getIdr()));
            units.add(new Unit(ILS, R.string.ils, 1 / currency.getIls(), currency.getIls()));
            units.add(new Unit(JPY, R.string.jpy, 1 / currency.getJpy(), currency.getJpy()));
            units.add(new Unit(KRW, R.string.krw, 1 / currency.getKrw(), currency.getKrw()));
            units.add(new Unit(MYR, R.string.myr, 1 / currency.getMyr(), currency.getMyr()));
            units.add(new Unit(MXN, R.string.mxn, 1 / currency.getMxn(), currency.getMxn()));
            units.add(new Unit(NZD, R.string.nzd, 1 / currency.getNzd(), currency.getNzd()));
            units.add(new Unit(NOK, R.string.nok, 1 / currency.getNok(), currency.getNok()));
            units.add(new Unit(PHP, R.string.php, 1 / currency.getPhp(), currency.getPhp()));
            units.add(new Unit(PLN, R.string.pln, 1 / currency.getPln(), currency.getPln()));
            units.add(new Unit(RON, R.string.ron, 1 / currency.getRon(), currency.getRon()));
            units.add(new Unit(RUB, R.string.rub, 1 / currency.getRub(), currency.getRub()));
            units.add(new Unit(SGD, R.string.sgd, 1 / currency.getSgd(), currency.getSgd()));
            units.add(new Unit(ZAR, R.string.zar, 1 / currency.getZar(), currency.getZar()));
            units.add(new Unit(SEK, R.string.sek, 1 / currency.getSek(), currency.getSek()));
            units.add(new Unit(CHF, R.string.chf, 1 / currency.getChf(), currency.getChf()));
            units.add(new Unit(THB, R.string.thb, 1 / currency.getThb(), currency.getThb()));
            units.add(new Unit(LIRA, R.string.lira, 1 / currency.getLira(), currency.getLira()));
        }

        addConversion(Conversion.CURRENCY, new Conversion(Conversion.CURRENCY, R.string.currency, units));
    }

    private void getStorageConversions() {
        //Base Unit = megabyte
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(BIT, R.string.bit, 0.00000011920928955078, 8388608.0));
        units.add(new Unit(BYTE, R.string.Byte, 0.00000095367431640625, 1048576.0));
        units.add(new Unit(KILOBIT, R.string.kilobit, 0.0001220703125, 8192.0));
        units.add(new Unit(KILOBYTE, R.string.kilobyte, 0.0009765625, 1024.0));
        units.add(new Unit(MEGABIT, R.string.megabit, 0.125, 8.0));
        units.add(new Unit(MEGABYTE, R.string.megabyte, 1.0, 1.0));
        units.add(new Unit(GIGABIT, R.string.gigabit, 128.0, 0.0078125));
        units.add(new Unit(GIGABYTE, R.string.gigabyte, 1024.0, 0.0009765625));
        units.add(new Unit(TERABIT, R.string.terabit, 131072.0, 0.00000762939453125));
        units.add(new Unit(TERABYTE, R.string.terabyte, 1048576.0, 0.00000095367431640625));
        addConversion(Conversion.STORAGE, new Conversion(Conversion.STORAGE, R.string.storage, units));
    }

    private void getEnergyConversions() {
        //Base unit Joules

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(JOULE, R.string.joule, 1.0, 1.0));
        units.add(new Unit(KILOJOULE, R.string.kilojoule, 1000.0, 0.001));
        units.add(new Unit(CALORIE, R.string.calorie, 4.184, 0.2390057361376673040153));
        units.add(new Unit(KILOCALORIE, R.string.kilocalorie, 4184.0, 0.0002390057361376673040153));
        units.add(new Unit(BTU, R.string.btu, 1055.05585262, 0.0009478171203133172000128));
        units.add(new Unit(FT_LBF, R.string.ft_lbF, 1.3558179483314004, 0.7375621494575464935503));
        units.add(new Unit(IN_LBF, R.string.in_lbF, 0.1129848290276167, 8.850745793490557922604));
        units.add(new Unit(KILOWATT_HOUR, R.string.kilowatt_hour, 3600000.0, 0.0000002777777777777777777778));
        addConversion(Conversion.ENERGY, new Conversion(Conversion.ENERGY, R.string.energy, units));
    }

    private void getFuelConversions() {
        //Base Unit - Miles per Gallon US

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(MPG_US, R.string.mpg_us, 1.0, 1.0));
        units.add(new Unit(MPG_UK, R.string.mpg_uk, 0.83267418460479, 1.2009499255398));
        units.add(new Unit(L_100K, R.string.l_100k, 235.214582, 235.214582));
        units.add(new Unit(KM_L, R.string.km_l, 2.352145833, 0.42514370749052));
        units.add(new Unit(MILES_L, R.string.miles_l, 3.7854118, 0.264172052));
        addConversion(Conversion.FUEL, new Conversion(Conversion.FUEL, R.string.fuel_consumption, units));
    }

    private void getLengthConversions() {
        //Base unit - Metres

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(KILOMETRE, R.string.kilometre, 1000.0, 0.001));
        units.add(new Unit(MILE, R.string.mile, 1609.344, 0.00062137119223733397));
        units.add(new Unit(METRE, R.string.metre, 1.0, 1.0));
        units.add(new Unit(CENTIMETRE, R.string.centimetre, 0.01, 100.0));
        units.add(new Unit(MILLIMETRE, R.string.millimetre, 0.001, 1000.0));
        units.add(new Unit(MICROMETRE, R.string.micrometre, 0.000001, 1000000.0));
        units.add(new Unit(NANOMETRE, R.string.nanometre, 0.000000001, 1000000000.0));
        units.add(new Unit(YARD, R.string.yard, 0.9144, 1.09361329833770779));
        units.add(new Unit(FEET, R.string.feet, 0.3048, 3.28083989501312336));
        units.add(new Unit(INCH, R.string.inch, 0.0254, 39.3700787401574803));
        units.add(new Unit(NAUTICAL_MILE, R.string.nautical_mile, 1852.0, 0.000539956803455723542));
        units.add(new Unit(FURLONG, R.string.furlong, 201.168, 0.0049709695379));
        units.add(new Unit(LIGHT_YEAR, R.string.light_year, 9460730472580800.0, 0.0000000000000001057000834024615463709));
        addConversion(Conversion.LENGTH, new Conversion(Conversion.LENGTH, R.string.length, units));
    }

    private void getMassConversions() {
        //Base unit - Kilograms

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(KILOGRAM, R.string.kilogram, 1.0, 1.0));
        units.add(new Unit(POUND, R.string.pound, 0.45359237, 2.20462262184877581));
        units.add(new Unit(GRAM, R.string.gram, 0.001, 1000.0));
        units.add(new Unit(MILLIGRAM, R.string.milligram, 0.000001, 1000000.0));
        units.add(new Unit(OUNCE, R.string.ounce, 0.028349523125, 35.27396194958041291568));
        units.add(new Unit(GRAIN, R.string.grain, 0.00006479891, 15432.35835294143065061));
        units.add(new Unit(STONE, R.string.stone, 6.35029318, 0.15747304441777));
        units.add(new Unit(METRIC_TON, R.string.metric_ton, 1000.0, 0.001));
        units.add(new Unit(SHORT_TON, R.string.short_ton, 907.18474, 0.0011023113109243879));
        units.add(new Unit(LONG_TON, R.string.long_ton, 1016.0469088, 0.0009842065276110606282276));
        addConversion(Conversion.MASS, new Conversion(Conversion.MASS, R.string.mass, units));
    }

    private void getPowerConversions() {
        //Base unit - Watt

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(WATT, R.string.watt, 1.0, 1.0));
        units.add(new Unit(KILOWATT, R.string.kilowatt, 1000.0, 0.001));
        units.add(new Unit(MEGAWATT, R.string.megawatt, 1000000.0, 0.000001));
        units.add(new Unit(HP, R.string.hp, 735.49875, 0.00135962161730390432));
        units.add(new Unit(HP_UK, R.string.hp_uk, 745.69987158227022, 0.00134102208959502793));
        units.add(new Unit(FT_LBF_S, R.string.ft_lbf_s, 1.3558179483314004, 0.737562149277265364));
        units.add(new Unit(CALORIE_S, R.string.calorie_s, 4.1868, 0.23884589662749594));
        units.add(new Unit(BTU_S, R.string.btu_s, 1055.05585262, 0.0009478171203133172));
        units.add(new Unit(KVA, R.string.kva, 1000.0, 0.001));
        addConversion(Conversion.POWER, new Conversion(Conversion.POWER, R.string.power, units));
    }

    private void getPressureConversions() {
        //Base unit - Pa

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(MEGAPASCAL, R.string.megapascal, 1000000.0, 0.000001));
        units.add(new Unit(KILOPASCAL, R.string.kilopascal, 1000.0, 0.001));
        units.add(new Unit(PASCAL, R.string.pascal, 1.0, 1.0));
        units.add(new Unit(BAR, R.string.bar, 100000.0, 0.00001));
        units.add(new Unit(PSI, R.string.psi, 6894.757293168361, 0.000145037737730209222));
        units.add(new Unit(PSF, R.string.psf, 47.880258980335840277777777778, 0.020885434233150127968));
        units.add(new Unit(ATMOSPHERE, R.string.atmosphere, 101325.0, 0.0000098692326671601283));
        units.add(new Unit(TECHNICAL_ATMOSPHERE, R.string.technical_atmosphere, 98066.5, 0.0000101971621297792824257));
        units.add(new Unit(MMHG, R.string.mmhg, 133.322387415, 0.007500615758456563339513));
        units.add(new Unit(TORR, R.string.torr, 133.3223684210526315789, 0.00750061682704169751));
        addConversion(Conversion.PRESSURE, new Conversion(Conversion.PRESSURE, R.string.pressure, units));
    }

    private void getSpeedConversions() {
        //base unit - m/s

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(KM_HR, R.string.km_h, 0.27777777777778, 3.6));
        units.add(new Unit(MPH, R.string.mph, 0.44704, 2.2369362920544));
        units.add(new Unit(M_S, R.string.m_s, 1.0, 1.0));
        units.add(new Unit(FPS, R.string.fps, 0.3048, 3.2808398950131));
        units.add(new Unit(KNOT, R.string.knot, 0.51444444444444, 1.9438444924406));
        addConversion(Conversion.SPEED, new Conversion(Conversion.SPEED, R.string.speed, units));
    }

    private void getTemperatureConversions() {
        List<Unit> units = new ArrayList<Unit>();
        units.add(new TemperatureUnit(CELSIUS, R.string.celsius));
        units.add(new TemperatureUnit(FAHRENHEIT, R.string.fahrenheit));
        units.add(new TemperatureUnit(KELVIN, R.string.kelvin));
        units.add(new TemperatureUnit(RANKINE, R.string.rankine));
        units.add(new TemperatureUnit(DELISLE, R.string.delisle));
        units.add(new TemperatureUnit(NEWTON, R.string.newton));
        units.add(new TemperatureUnit(REAUMUR, R.string.reaumur));
        units.add(new TemperatureUnit(ROMER, R.string.romer));
        units.add(new TemperatureUnit(GAS_MARK, R.string.gas_mark));
        addConversion(Conversion.TEMPERATURE, new Conversion(Conversion.TEMPERATURE, R.string.temperature, units));
    }

    private void getTimeConversions() {
        //Base unit - seconds
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(YEAR, R.string.year, 31536000.0, 0.0000000317097919837645865));
        units.add(new Unit(MONTH, R.string.month, 2628000.0, 0.0000003805175));
        units.add(new Unit(WEEK, R.string.week, 604800.0, 0.00000165343915343915344));
        units.add(new Unit(DAY, R.string.day, 86400.0, 0.0000115740740740740741));
        units.add(new Unit(HOUR, R.string.hour, 3600.0, 0.000277777777777777778));
        units.add(new Unit(MINUTE, R.string.minute, 60.0, 0.0166666666666666667));
        units.add(new Unit(SECOND, R.string.second, 1.0, 1.0));
        units.add(new Unit(MILLISECOND, R.string.millisecond, 0.001, 1000.0));
        units.add(new Unit(NANOSECOND, R.string.nanosecond, 0.000000001, 1000000000.0));
        addConversion(Conversion.TIME, new Conversion(Conversion.TIME, R.string.time, units));
    }

    private void getTorqueConversions() {
        // Base unit - Newton-metres
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(N_M, R.string.n_m, 1.0, 1.0));
        units.add(new Unit(FT_LBF, R.string.ft_lbF, 1.3558179483314004, 0.7375621494575464935503));
        units.add(new Unit(IN_LBF, R.string.in_lbF, 0.1129848290276167, 8.850745793490557922604));
        addConversion(Conversion.TORQUE, new Conversion(Conversion.TORQUE, R.string.torque, units));
    }

    private void getVolumeConversions() {
        // Base unit - cubic metre
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(TEASPOON, R.string.teaspoon, 0.0000049289215938, 202884.136211058));
        units.add(new Unit(TABLESPOON, R.string.tablespoon, 0.0000147867647812, 67628.045403686));
        units.add(new Unit(CUP, R.string.cup, 0.0002365882365, 4226.7528377304));
        units.add(new Unit(FLUID_OUNCE, R.string.fluid_ounce, 0.0000295735295625, 33814.0227018429972));
        units.add(new Unit(FLUID_OUNCE_UK, R.string.fluid_ounce_uk, 0.0000284130625, 35195.07972785404600437));
        units.add(new Unit(PINT, R.string.pint, 0.000473176473, 2113.37641886518732));
        units.add(new Unit(PINT_UK, R.string.pint_uk, 0.00056826125, 1759.753986392702300218));
        units.add(new Unit(QUART, R.string.quart, 0.000946352946, 1056.68820943259366));
        units.add(new Unit(QUART_UK, R.string.quart_uk, 0.0011365225, 879.8769931963511501092));
        units.add(new Unit(GALLON, R.string.gallon, 0.003785411784, 264.172052358148415));
        units.add(new Unit(GALLON_UK, R.string.gallon_uk, 0.00454609, 219.9692482990877875273));
        units.add(new Unit(BARREL, R.string.barrel, 0.119240471196, 8.38641436057614017079));
        units.add(new Unit(BARREL_UK, R.string.barrel_uk, 0.16365924, 6.11025689719688298687));
        units.add(new Unit(MILLILITRE, R.string.millilitre, 0.000001, 1000000.0));
        units.add(new Unit(LITRE, R.string.litre, 0.001, 1000.0));
        units.add(new Unit(CUBIC_CM, R.string.cubic_cm, 0.000001, 1000000.0));
        units.add(new Unit(CUBIC_M, R.string.cubic_m, 1.0, 1.0));
        units.add(new Unit(CUBIC_INCH, R.string.cubic_inch, 0.000016387064, 61023.744094732284));
        units.add(new Unit(CUBIC_FOOT, R.string.cubic_foot, 0.028316846592, 35.3146667214885903));
        units.add(new Unit(CUBIC_YARD, R.string.cubic_yard, 0.7645548692741148, 1.3079506));
        addConversion(Conversion.VOLUME, new Conversion(Conversion.VOLUME, R.string.volume, units));
    }

    public boolean hasCurrency() {
        return mConversions.get(Conversion.CURRENCY).getUnits().size() > 0;
    }

    public void setCurrencyUpdated(final boolean currencyUpdated) {
        mCurrencyUpdated = currencyUpdated;
    }

    public boolean isCurrencyUpdated() {
        return mCurrencyUpdated;
    }
}
