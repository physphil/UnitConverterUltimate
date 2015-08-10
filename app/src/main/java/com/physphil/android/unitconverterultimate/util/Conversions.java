package com.physphil.android.unitconverterultimate.util;

import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.TemperatureUnit;
import com.physphil.android.unitconverterultimate.models.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains all conversion info
 */
public final class Conversions
{
    // Unit conversion categories
    public static final int AREA = 0;
    public static final int COOKING = 1;
    public static final int STORAGE = 2;
    public static final int ENERGY = 3;
    public static final int FUEL = 4;
    public static final int LENGTH = 5;
    public static final int MASS = 6;
    public static final int POWER = 7;
    public static final int PRESSURE = 8;
    public static final int SPEED = 9;
    public static final int TEMPERATURE = 10;
    public static final int TIME = 11;
    public static final int TORQUE = 12;
    public static final int VOLUME = 13;

    // Temperature units
    public static final int CELSIUS = 0;
    public static final int FAHRENHEIT = 1;
    public static final int KELVIN = 2;
    public static final int RANKINE = 3;
    public static final int DELISLE = 4;
    public static final int NEWTON = 5;
    public static final int REAUMUR = 6;
    public static final int ROMER = 7;
    public static final int GAS_MARK = 8;

    private static Conversions mInstance = null;
    private Map<Integer, Conversion> mConversions = new HashMap<Integer, Conversion>();

    /**
     * Get instance of Conversions objects, which contains mapping of type and Conversion object
     *
     * @return Conversions mInstance
     */
    public static Conversions getInstance()
    {
        //Create singleton to contain all conversions
        initialize();
        return mInstance;
    }

    /**
     * Initialize singleton mInstance with conversion info
     */
    public static void initialize()
    {
        if(mInstance == null)
        {
            mInstance = new Conversions();
        }
    }

    private Conversions()
    {
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
    }

    /**
     * Get Conversion object by its id
     * @param id id of conversion
     * @return Conversion object
     */
    public Conversion getById(int id)
    {
        return mConversions.get(id);
    }

    private void getAreaConversions()
    {
        //Base unit: square metre

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.sq_kilometre, 1000000.0, 0.000001));
        units.add(new Unit(1, R.string.sq_metre, 1.0, 1.0));
        units.add(new Unit(2, R.string.sq_centimetre, 0.0001, 10000.0));
        units.add(new Unit(3, R.string.hectare, 10000.0, 0.0001));
        units.add(new Unit(4, R.string.sq_mile, 2589988.110336, 0.000000386102158542445847));
        units.add(new Unit(5, R.string.sq_yard, 0.83612736, 1.19599004630108026));
        units.add(new Unit(6, R.string.sq_foot, 0.09290304, 10.7639104167097223));
        units.add(new Unit(7, R.string.sq_inch, 0.00064516, 1550.00310000620001));
        units.add(new Unit(8, R.string.acre, 4046.8564224, 0.000247105381467165342));
        mConversions.put(AREA, new Conversion(AREA, R.string.area, units));
    }

    private void getCookingConversions()
    {
        // Base unit - cubic metre
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.teaspoon, 0.0000049289215938, 202884.136211058));
        units.add(new Unit(1, R.string.tablespoon, 0.0000147867647812, 67628.045403686));
        units.add(new Unit(2, R.string.cup, 0.0002365882365, 4226.7528377304));
        units.add(new Unit(3, R.string.fluid_ounce, 0.0000295735295625, 33814.0227018429972));
        units.add(new Unit(4, R.string.fluid_ounce_uk, 0.0000284130625, 35195.07972785404600437));
        units.add(new Unit(5, R.string.pint, 0.000473176473, 2113.37641886518732));
        units.add(new Unit(6, R.string.pint_uk, 0.00056826125, 1759.753986392702300218));
        units.add(new Unit(7, R.string.quart, 0.000946352946, 1056.68820943259366));
        units.add(new Unit(8, R.string.quart_uk, 0.0011365225, 879.8769931963511501092));
        units.add(new Unit(9, R.string.gallon, 0.003785411784, 264.172052358148415));
        units.add(new Unit(10, R.string.gallon_uk, 0.00454609, 219.9692482990877875273));
        units.add(new Unit(11, R.string.millilitre, 0.000001, 1000000.0));
        units.add(new Unit(12, R.string.litre, 0.001, 1000.0));
        mConversions.put(COOKING, new Conversion(COOKING, R.string.cooking, units));
    }

    private void getStorageConversions()
    {
        //Base Unit = megabyte
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.bit, 0.00000011920928955078, 8388608.0));
        units.add(new Unit(1, R.string.Byte, 0.00000095367431640625, 1048576.0));
        units.add(new Unit(2, R.string.kilobit, 0.0001220703125, 8192.0));
        units.add(new Unit(3, R.string.kilobyte, 0.0009765625, 1024.0));
        units.add(new Unit(4, R.string.megabit, 1.0, 1.0));
        units.add(new Unit(5, R.string.megabyte, 0.83612736, 1.19599004630108026));
        units.add(new Unit(6, R.string.gigabit, 128.0, 0.0078125));
        units.add(new Unit(7, R.string.gigabyte, 1024.0, 0.0009765625));
        units.add(new Unit(8, R.string.terabit, 131072.0, 0.00000762939453125));
        units.add(new Unit(9, R.string.terabyte, 1048576.0, 0.00000095367431640625));
        mConversions.put(STORAGE, new Conversion(STORAGE, R.string.storage, units));
    }

    private void getEnergyConversions()
    {
        //Base unit Joules

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.joule, 1.0, 1.0));
        units.add(new Unit(1, R.string.kilojoule, 1000.0, 0.001));
        units.add(new Unit(2, R.string.calorie, 4.184, 0.2390057361376673040153));
        units.add(new Unit(3, R.string.kilocalorie, 4184.0, 0.0002390057361376673040153));
        units.add(new Unit(4, R.string.btu, 1055.05585262, 0.0009478171203133172000128));
        units.add(new Unit(5, R.string.ft_lbF, 1.3558179483314004, 0.7375621494575464935503));
        units.add(new Unit(6, R.string.in_lbF, 0.1129848290276167, 8.850745793490557922604));
        units.add(new Unit(7, R.string.kilowatt_hour, 3600000.0, 0.0000002777777777777777777778));
        units.add(new Unit(8, R.string.electron_volt, 6241509479607718382.9424839, 0.000000000000000000160217653));
        mConversions.put(ENERGY, new Conversion(ENERGY, R.string.energy, units));
    }

    private void getFuelConversions()
    {
        //Base Unit - Miles per Gallon US

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.mpg_us, 1.0, 1.0));
        units.add(new Unit(1, R.string.mpg_uk, 0.83267418460479, 1.2009499255398));
        units.add(new Unit(2, R.string.l_100k, 235.214582, 235.214582));
        units.add(new Unit(3, R.string.km_l, 2.352145833, 0.42514370749052));
        units.add(new Unit(4, R.string.miles_l, 3.7854118, 0.264172052));
        mConversions.put(FUEL, new Conversion(FUEL, R.string.fuel_consumption, units));
    }

    private void getLengthConversions()
    {
        //Base unit - Metres

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.kilometre, 1000.0, 0.001));
        units.add(new Unit(1, R.string.mile, 1609.344, 0.00062137119223733397));
        units.add(new Unit(2, R.string.metre, 1.0, 1.0));
        units.add(new Unit(3, R.string.centimetre, 0.01, 100.0));
        units.add(new Unit(4, R.string.millimetre, 0.001, 1000.0));
        units.add(new Unit(5, R.string.micrometre, 0.000001, 1000000.0));
        units.add(new Unit(6, R.string.nanometre, 0.000000001, 1000000000.0));
        units.add(new Unit(7, R.string.yard, 0.9144, 1.09361329833770779));
        units.add(new Unit(8, R.string.feet, 0.3048, 3.28083989501312336));
        units.add(new Unit(9, R.string.inch, 0.0254, 39.3700787401574803));
        units.add(new Unit(10, R.string.nautical_mile, 1852.0, 0.000539956803455723542));
        units.add(new Unit(11, R.string.furlong, 201.168, 0.0049709695379));
        units.add(new Unit(12, R.string.light_year, 9460730472580800.0, 0.0000000000000001057000834024615463709));
        mConversions.put(LENGTH, new Conversion(LENGTH, R.string.length, units));
    }

    private void getMassConversions()
    {
        //Base unit - Kilograms

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.kilogram, 1.0, 1.0));
        units.add(new Unit(1, R.string.pound, 0.45359237, 2.20462262184877581));
        units.add(new Unit(2, R.string.gram, 0.001, 1000.0));
        units.add(new Unit(3, R.string.milligram, 0.000001, 1000000.0));
        units.add(new Unit(4, R.string.ounce, 0.028349523125, 35.27396194958041291568));
        units.add(new Unit(5, R.string.grain, 0.00006479891, 15432.35835294143065061));
        units.add(new Unit(6, R.string.stone, 6.35029318, 0.15747304441777));
        units.add(new Unit(7, R.string.metric_ton, 1000.0, 0.001));
        units.add(new Unit(8, R.string.short_ton, 907.18474, 0.0011023113109243879));
        units.add(new Unit(9, R.string.long_ton, 1016.0469088, 0.0009842065276110606282276));
        mConversions.put(MASS, new Conversion(MASS, R.string.mass, units));
    }

    private void getPowerConversions()
    {
        //Base unit - Watt

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.watt, 1.0, 1.0));
        units.add(new Unit(1, R.string.kilowatt, 1000.0, 0.001));
        units.add(new Unit(2, R.string.megawatt, 1000000.0, 0.000001));
        units.add(new Unit(3, R.string.hp, 735.49875, 0.00135962161730390432));
        units.add(new Unit(4, R.string.hp_uk, 745.69987158227022, 0.00134102208959502793));
        units.add(new Unit(5, R.string.ft_lbf_s, 1.3558179483314004, 0.737562149277265364));
        units.add(new Unit(6, R.string.calorie_s, 4.1868, 0.23884589662749594));
        units.add(new Unit(7, R.string.btu_s, 1055.05585262, 0.0009478171203133172));
        units.add(new Unit(8, R.string.kva, 1000.0, 0.001));
        units.add(new Unit(9, R.string.electron_volt, 6241509479607718382.9424839, 0.000000000000000000160217653));
        mConversions.put(POWER, new Conversion(POWER, R.string.power, units));
    }

    private void getPressureConversions()
    {
        //Base unit - Pa

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.megapascal, 1000000.0, 0.000001));
        units.add(new Unit(1, R.string.kilopascal, 1000.0, 0.001));
        units.add(new Unit(2, R.string.pascal, 1.0, 1.0));
        units.add(new Unit(3, R.string.bar, 100000.0, 0.00001));
        units.add(new Unit(4, R.string.psi, 6894.757293168361, 0.000145037737730209222));
        units.add(new Unit(5, R.string.psf, 47.880258980335840277777777778, 0.020885434233150127968));
        units.add(new Unit(6, R.string.atmosphere, 101325.0, 0.0000098692326671601283));
        units.add(new Unit(7, R.string.mmhg, 133.322387415, 0.007500615758456563339513));
        units.add(new Unit(8, R.string.torr, 133.3223684210526315789, 0.00750061682704169751));
        mConversions.put(PRESSURE, new Conversion(PRESSURE, R.string.pressure, units));
    }

    private void getSpeedConversions()
    {
        //base unit - m/s

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.km_h, 0.27777777777778, 3.6));
        units.add(new Unit(1, R.string.mph, 0.44704, 2.2369362920544));
        units.add(new Unit(2, R.string.m_s, 1.0, 1.0));
        units.add(new Unit(3, R.string.fps, 0.3048, 3.2808398950131));
        units.add(new Unit(4, R.string.knot, 0.51444444444444, 1.9438444924406));
        mConversions.put(SPEED, new Conversion(SPEED, R.string.speed, units));
    }

    private void getTemperatureConversions()
    {
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
        mConversions.put(TEMPERATURE, new Conversion(TEMPERATURE, R.string.temperature, units));
    }

    private void getTimeConversions()
    {
        //Base unit - seconds
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.year, 31536000.0, 0.0000000317097919837645865));
        units.add(new Unit(1, R.string.month, 2628000.0, 0.0000003805175));
        units.add(new Unit(2, R.string.week, 604800.0, 0.00000165343915343915344));
        units.add(new Unit(3, R.string.day, 86400.0, 0.0000115740740740740741));
        units.add(new Unit(4, R.string.hour, 3600.0, 0.000277777777777777778));
        units.add(new Unit(5, R.string.minute, 60.0, 0.0166666666666666667));
        units.add(new Unit(6, R.string.second, 1.0, 1.0));
        units.add(new Unit(7, R.string.millisecond, 0.001, 1000.0));
        units.add(new Unit(8, R.string.nanosecond, 0.000000001, 1000000000.0));
        mConversions.put(TIME, new Conversion(TIME, R.string.time, units));
    }

    private void getTorqueConversions()
    {
        // Base unit - Newton-metres
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.n_m, 31536000.0, 0.0000000317097919837645865));
        units.add(new Unit(1, R.string.ft_lbF, 1.3558179483314004, 0.7375621494575464935503));
        units.add(new Unit(2, R.string.in_lbF, 0.1129848290276167, 8.850745793490557922604));
        mConversions.put(TORQUE, new Conversion(TORQUE, R.string.torque, units));
    }

    private void getVolumeConversions()
    {
        // Base unit - cubic metre
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(0, R.string.teaspoon, 0.0000049289215938, 202884.136211058));
        units.add(new Unit(1, R.string.tablespoon, 0.0000147867647812, 67628.045403686));
        units.add(new Unit(2, R.string.cup, 0.0002365882365, 4226.7528377304));
        units.add(new Unit(3, R.string.fluid_ounce, 0.0000295735295625, 33814.0227018429972));
        units.add(new Unit(4, R.string.fluid_ounce_uk, 0.0000284130625, 35195.07972785404600437));
        units.add(new Unit(5, R.string.pint, 0.000473176473, 2113.37641886518732));
        units.add(new Unit(6, R.string.pint_uk, 0.00056826125, 1759.753986392702300218));
        units.add(new Unit(7, R.string.quart, 0.000946352946, 1056.68820943259366));
        units.add(new Unit(8, R.string.quart_uk, 0.0011365225, 879.8769931963511501092));
        units.add(new Unit(9, R.string.gallon, 0.003785411784, 264.172052358148415));
        units.add(new Unit(10, R.string.gallon_uk, 0.00454609, 219.9692482990877875273));
        units.add(new Unit(11, R.string.barrel, 0.119240471196, 8.38641436057614017079));
        units.add(new Unit(12, R.string.barrel_uk, 0.16365924, 6.11025689719688298687));
        units.add(new Unit(13, R.string.millilitre, 0.000001, 1000000.0));
        units.add(new Unit(14, R.string.litre, 0.001, 1000.0));
        units.add(new Unit(15, R.string.cubic_cm, 0.000001, 1000000.0));
        units.add(new Unit(16, R.string.cubic_m, 1.0, 1.0));
        units.add(new Unit(17, R.string.cubic_inch, 0.000016387064, 61023.744094732284));
        units.add(new Unit(18, R.string.cubic_foot, 0.028316846592, 35.3146667214885903));
        units.add(new Unit(19, R.string.cubic_yard, 0.7645548692741148, 1.3079506));
        mConversions.put(VOLUME, new Conversion(VOLUME, R.string.volume, units));
    }
}
