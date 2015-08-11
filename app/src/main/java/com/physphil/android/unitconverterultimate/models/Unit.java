package com.physphil.android.unitconverterultimate.models;

import android.support.annotation.IntDef;

/**
 * A unit that can be converted to/from
 * Created by Phizz on 15-07-25.
 */
public class Unit
{
    // make list of units with IntDef. add to getters/setters/db save methods. make radio button id the index of for loop, as unit is already set as tag.
    // can no longer do radioGroup.check(id) from conversion state, as btn ids wont be synced with conversion state ids.  just find btn in group by id and check. this is probably better anyways.
    // add annotations to ConversionState
    // replace toasts
    public static final int SQ_KILOMETRES = 0;
    public static final int SQ_METRES = 1;
    public static final int SQ_CENTIMETRES = 2;
    public static final int HECTARE = 3;
    public static final int SQ_MILE = 4;
    public static final int SQ_YARD = 5;
    public static final int SQ_FOOT = 6;
    public static final int SQ_INCH = 7;
    public static final int ACRE = 8;

    public static final int BIT = 100;
    public static final int BYTE = 101;
    public static final int KILOBIT = 102;
    public static final int KILOBYTE = 103;
    public static final int MEGABIT = 104;
    public static final int MEGABYTE = 105;
    public static final int GIGABIT = 106;
    public static final int GIGABYTE = 107;
    public static final int TERABIT = 108;
    public static final int TERABYTE = 109;

    public static final int JOULE = 200;
    public static final int KILOJOULE = 201;
    public static final int CALORIE = 208;
    public static final int KILOCALORIE = 202;
    public static final int BTU = 203;
    public static final int FT_LBF = 204;
    public static final int IN_LBF = 205;
    public static final int KILOWATT_HOUR = 206;
    public static final int ELECTRON_VOLT = 207;

    public static final int MPG_US = 300;
    public static final int MPG_UK = 301;
    public static final int L_100K = 302;
    public static final int KM_L = 303;
    public static final int MILES_L = 304;

    public static final int KILOMETRE = 400;
    public static final int MILE = 401;
    public static final int METRE = 402;
    public static final int CENTIMETRE = 403;
    public static final int MILLIMETRE = 404;
    public static final int MICROMETRE = 405;
    public static final int NANOMETRE = 406;
    public static final int YARD = 407;
    public static final int FEET = 408;
    public static final int INCH = 409;
    public static final int NAUTICAL_MILE = 410;
    public static final int FURLONG = 411;
    public static final int LIGHT_YEAR = 412;

    public static final int KILOGRAM = 500;
    public static final int POUND = 501;
    public static final int GRAM = 502;
    public static final int MILLIGRAM = 503;
    public static final int OUNCE = 504;
    public static final int GRAIN = 505;
    public static final int STONE = 506;
    public static final int METRIC_TON = 507;
    public static final int SHORT_TON = 508;
    public static final int LONG_TON = 509;

    public static final int WATT = 600;
    public static final int KILOWATT = 601;
    public static final int MEGAWATT = 602;
    public static final int HP = 603;
    public static final int HP_UK = 604;
    public static final int FT_LBF_S = 605;
    public static final int CALORIE_S = 606;
    public static final int BTU_S = 607;
    public static final int KVA = 608;

    public static final int MEGAPASCAL = 700;
    public static final int KILOPASCAL = 701;
    public static final int PASCAL = 702;
    public static final int BAR = 703;
    public static final int PSI = 704;
    public static final int PSF = 705;
    public static final int ATMOSPHERE = 706;
    public static final int MMHG = 707;
    public static final int TORR = 708;

    public static final int KM_HR = 800;
    public static final int MPH = 801;
    public static final int M_S = 802;
    public static final int FPS = 803;
    public static final int KNOT = 804;

    public static final int CELSIUS = 900;
    public static final int FAHRENHEIT = 901;
    public static final int KELVIN = 902;
    public static final int RANKINE = 903;
    public static final int DELISLE = 904;
    public static final int NEWTON = 905;
    public static final int REAUMUR = 906;
    public static final int ROMER = 907;
    public static final int GAS_MARK = 908;

    public static final int YEAR = 1000;
    public static final int MONTH = 1001;
    public static final int WEEK = 1002;
    public static final int DAY = 1003;
    public static final int HOUR = 1004;
    public static final int MINUTE = 1005;
    public static final int SECOND = 1006;
    public static final int MILLISECOND = 1007;
    public static final int NANOSECOND = 1008;

    public static final int N_M = 1100;

    public static final int TEASPOON = 1200;
    public static final int TABLESPOON = 1201;
    public static final int CUP = 1202;
    public static final int FLUID_OUNCE = 1203;
    public static final int QUART = 1204;
    public static final int PINT = 1205;
    public static final int GALLON = 1206;
    public static final int BARREL = 1207;
    public static final int FLUID_OUNCE_UK = 1208;
    public static final int QUART_UK = 1209;
    public static final int PINT_UK = 1210;
    public static final int GALLON_UK = 1211;
    public static final int BARREL_UK = 1212;
    public static final int MILLILITRE = 1213;
    public static final int LITRE = 1214;
    public static final int CUBIC_CM = 1215;
    public static final int CUBIC_M = 1216;
    public static final int CUBIC_INCH = 1217;
    public static final int CUBIC_FOOT = 1218;
    public static final int CUBIC_YARD = 1219;

    @IntDef({SQ_KILOMETRES, SQ_METRES, SQ_CENTIMETRES, HECTARE, SQ_MILE, SQ_YARD, SQ_FOOT, SQ_INCH, ACRE,
    BIT, BYTE, KILOBIT, KILOBYTE, MEGABIT, MEGABYTE, GIGABIT, GIGABYTE, TERABIT, TERABYTE,
    JOULE, KILOJOULE, CALORIE, KILOCALORIE, BTU, FT_LBF, IN_LBF, KILOWATT_HOUR, ELECTRON_VOLT,
    MPG_US, MPG_UK, L_100K, KM_L, MILES_L,
    KILOMETRE, MILE, METRE, CENTIMETRE, MILLIMETRE, MICROMETRE, NANOMETRE, YARD, FEET, INCH, NAUTICAL_MILE, FURLONG, LIGHT_YEAR,
    KILOGRAM, POUND, GRAM, MILLIGRAM, OUNCE, GRAIN, STONE, METRIC_TON, SHORT_TON, LONG_TON,
    WATT, KILOWATT, MEGAWATT, HP, HP_UK, FT_LBF_S, CALORIE_S, BTU_S, KVA,
    MEGAPASCAL, KILOPASCAL, PASCAL, BAR, PSI, PSF, ATMOSPHERE, MMHG, TORR,
    KM_HR, MPH, M_S, FPS, KNOT,
    CELSIUS, FAHRENHEIT, KELVIN, RANKINE, DELISLE, NEWTON, REAUMUR, ROMER, GAS_MARK,
    YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND, MILLISECOND, NANOSECOND,
    N_M,
    TEASPOON, TABLESPOON, CUP, FLUID_OUNCE, QUART, PINT, GALLON, BARREL, FLUID_OUNCE_UK, QUART_UK, PINT_UK,
    GALLON_UK, BARREL_UK, MILLILITRE, LITRE, CUBIC_CM, CUBIC_M, CUBIC_INCH, CUBIC_FOOT, CUBIC_YARD})
    public @interface id{}

    private int id;
    private int labelResource;
    private double conversionToBase;
    private double conversionFromBase;

    /**
     * Create a unit object
     * @param id id of the unit
     * @param labelResource string resource id of the label
     * @param conversionToBase the value to convert to the base unit of the conversion
     * @param conversionFromBase the value to convert from the base unit of the conversion
     */
    public Unit(@id int id, int labelResource, double conversionToBase, double conversionFromBase)
    {
        this.id = id;
        this.labelResource = labelResource;
        this.conversionToBase = conversionToBase;
        this.conversionFromBase = conversionFromBase;
    }

    @id
    public int getId()
    {
        return id;
    }

    public double getConversionToBaseUnit()
    {
        return conversionToBase;
    }

    public double getConversionFromBaseUnit()
    {
        return conversionFromBase;
    }

    public int getLabelResource()
    {
        return labelResource;
    }
}
