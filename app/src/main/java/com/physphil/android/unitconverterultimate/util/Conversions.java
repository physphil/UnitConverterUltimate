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
    public Conversion getById(@Conversion.id int id)
    {
        return mConversions.get(id);
    }

    /**
     * Method to add conversion to hashmap, encapsulated in a separate method for type safety
     * @param id conversion id
     * @param conversion Conversion object
     */
    private void addConversion(@Conversion.id int id, Conversion conversion)
    {
        mConversions.put(id, conversion);
    }

    private void getAreaConversions()
    {
        //Base unit: square metre

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.SQ_KILOMETRES, R.string.sq_kilometre, 1000000.0, 0.000001));
        units.add(new Unit(Unit.SQ_METRES, R.string.sq_metre, 1.0, 1.0));
        units.add(new Unit(Unit.SQ_CENTIMETRES, R.string.sq_centimetre, 0.0001, 10000.0));
        units.add(new Unit(Unit.HECTARE, R.string.hectare, 10000.0, 0.0001));
        units.add(new Unit(Unit.SQ_MILE, R.string.sq_mile, 2589988.110336, 0.000000386102158542445847));
        units.add(new Unit(Unit.SQ_YARD, R.string.sq_yard, 0.83612736, 1.19599004630108026));
        units.add(new Unit(Unit.SQ_FOOT, R.string.sq_foot, 0.09290304, 10.7639104167097223));
        units.add(new Unit(Unit.SQ_INCH, R.string.sq_inch, 0.00064516, 1550.00310000620001));
        units.add(new Unit(Unit.ACRE, R.string.acre, 4046.8564224, 0.000247105381467165342));
        addConversion(Conversion.AREA, new Conversion(Conversion.AREA, R.string.area, units));
    }

    private void getCookingConversions()
    {
        // Base unit - cubic metre
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.TEASPOON, R.string.teaspoon, 0.0000049289215938, 202884.136211058));
        units.add(new Unit(Unit.TABLESPOON, R.string.tablespoon, 0.0000147867647812, 67628.045403686));
        units.add(new Unit(Unit.CUP, R.string.cup, 0.0002365882365, 4226.7528377304));
        units.add(new Unit(Unit.FLUID_OUNCE, R.string.fluid_ounce, 0.0000295735295625, 33814.0227018429972));
        units.add(new Unit(Unit.FLUID_OUNCE_UK, R.string.fluid_ounce_uk, 0.0000284130625, 35195.07972785404600437));
        units.add(new Unit(Unit.PINT, R.string.pint, 0.000473176473, 2113.37641886518732));
        units.add(new Unit(Unit.PINT_UK, R.string.pint_uk, 0.00056826125, 1759.753986392702300218));
        units.add(new Unit(Unit.QUART, R.string.quart, 0.000946352946, 1056.68820943259366));
        units.add(new Unit(Unit.QUART_UK, R.string.quart_uk, 0.0011365225, 879.8769931963511501092));
        units.add(new Unit(Unit.GALLON, R.string.gallon, 0.003785411784, 264.172052358148415));
        units.add(new Unit(Unit.GALLON_UK, R.string.gallon_uk, 0.00454609, 219.9692482990877875273));
        units.add(new Unit(Unit.MILLILITRE, R.string.millilitre, 0.000001, 1000000.0));
        units.add(new Unit(Unit.LITRE, R.string.litre, 0.001, 1000.0));
        addConversion(Conversion.COOKING, new Conversion(Conversion.COOKING, R.string.cooking, units));
    }

    private void getStorageConversions()
    {
        //Base Unit = megabyte
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.BIT, R.string.bit, 0.00000011920928955078, 8388608.0));
        units.add(new Unit(Unit.BYTE, R.string.Byte, 0.00000095367431640625, 1048576.0));
        units.add(new Unit(Unit.KILOBIT, R.string.kilobit, 0.0001220703125, 8192.0));
        units.add(new Unit(Unit.KILOBYTE, R.string.kilobyte, 0.0009765625, 1024.0));
        units.add(new Unit(Unit.MEGABIT, R.string.megabit, 1.0, 1.0));
        units.add(new Unit(Unit.MEGABYTE, R.string.megabyte, 0.83612736, 1.19599004630108026));
        units.add(new Unit(Unit.GIGABIT, R.string.gigabit, 128.0, 0.0078125));
        units.add(new Unit(Unit.GIGABYTE, R.string.gigabyte, 1024.0, 0.0009765625));
        units.add(new Unit(Unit.TERABIT, R.string.terabit, 131072.0, 0.00000762939453125));
        units.add(new Unit(Unit.TERABYTE, R.string.terabyte, 1048576.0, 0.00000095367431640625));
        addConversion(Conversion.STORAGE, new Conversion(Conversion.STORAGE, R.string.storage, units));
    }

    private void getEnergyConversions()
    {
        //Base unit Joules

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.JOULE, R.string.joule, 1.0, 1.0));
        units.add(new Unit(Unit.KILOJOULE, R.string.kilojoule, 1000.0, 0.001));
        units.add(new Unit(Unit.CALORIE, R.string.calorie, 4.184, 0.2390057361376673040153));
        units.add(new Unit(Unit.KILOCALORIE, R.string.kilocalorie, 4184.0, 0.0002390057361376673040153));
        units.add(new Unit(Unit.BTU, R.string.btu, 1055.05585262, 0.0009478171203133172000128));
        units.add(new Unit(Unit.FT_LBF, R.string.ft_lbF, 1.3558179483314004, 0.7375621494575464935503));
        units.add(new Unit(Unit.IN_LBF, R.string.in_lbF, 0.1129848290276167, 8.850745793490557922604));
        units.add(new Unit(Unit.KILOWATT_HOUR, R.string.kilowatt_hour, 3600000.0, 0.0000002777777777777777777778));
        units.add(new Unit(Unit.ELECTRON_VOLT, R.string.electron_volt, 6241509479607718382.9424839, 0.000000000000000000160217653));
        addConversion(Conversion.ENERGY, new Conversion(Conversion.ENERGY, R.string.energy, units));
    }

    private void getFuelConversions()
    {
        //Base Unit - Miles per Gallon US

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.MPG_US, R.string.mpg_us, 1.0, 1.0));
        units.add(new Unit(Unit.MPG_UK, R.string.mpg_uk, 0.83267418460479, 1.2009499255398));
        units.add(new Unit(Unit.L_100K, R.string.l_100k, 235.214582, 235.214582));
        units.add(new Unit(Unit.KM_L, R.string.km_l, 2.352145833, 0.42514370749052));
        units.add(new Unit(Unit.MILES_L, R.string.miles_l, 3.7854118, 0.264172052));
        addConversion(Conversion.FUEL, new Conversion(Conversion.FUEL, R.string.fuel_consumption, units));
    }

    private void getLengthConversions()
    {
        //Base unit - Metres

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.KILOMETRE, R.string.kilometre, 1000.0, 0.001));
        units.add(new Unit(Unit.MILE, R.string.mile, 1609.344, 0.00062137119223733397));
        units.add(new Unit(Unit.METRE, R.string.metre, 1.0, 1.0));
        units.add(new Unit(Unit.CENTIMETRE, R.string.centimetre, 0.01, 100.0));
        units.add(new Unit(Unit.MILLIMETRE, R.string.millimetre, 0.001, 1000.0));
        units.add(new Unit(Unit.MICROMETRE, R.string.micrometre, 0.000001, 1000000.0));
        units.add(new Unit(Unit.NANOMETRE, R.string.nanometre, 0.000000001, 1000000000.0));
        units.add(new Unit(Unit.YARD, R.string.yard, 0.9144, 1.09361329833770779));
        units.add(new Unit(Unit.FEET, R.string.feet, 0.3048, 3.28083989501312336));
        units.add(new Unit(Unit.INCH, R.string.inch, 0.0254, 39.3700787401574803));
        units.add(new Unit(Unit.NAUTICAL_MILE, R.string.nautical_mile, 1852.0, 0.000539956803455723542));
        units.add(new Unit(Unit.FURLONG, R.string.furlong, 201.168, 0.0049709695379));
        units.add(new Unit(Unit.LIGHT_YEAR, R.string.light_year, 9460730472580800.0, 0.0000000000000001057000834024615463709));
        addConversion(Conversion.LENGTH, new Conversion(Conversion.LENGTH, R.string.length, units));
    }

    private void getMassConversions()
    {
        //Base unit - Kilograms

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.KILOGRAM, R.string.kilogram, 1.0, 1.0));
        units.add(new Unit(Unit.POUND, R.string.pound, 0.45359237, 2.20462262184877581));
        units.add(new Unit(Unit.GRAM, R.string.gram, 0.001, 1000.0));
        units.add(new Unit(Unit.MILLIGRAM, R.string.milligram, 0.000001, 1000000.0));
        units.add(new Unit(Unit.OUNCE, R.string.ounce, 0.028349523125, 35.27396194958041291568));
        units.add(new Unit(Unit.GRAIN, R.string.grain, 0.00006479891, 15432.35835294143065061));
        units.add(new Unit(Unit.STONE, R.string.stone, 6.35029318, 0.15747304441777));
        units.add(new Unit(Unit.METRIC_TON, R.string.metric_ton, 1000.0, 0.001));
        units.add(new Unit(Unit.SHORT_TON, R.string.short_ton, 907.18474, 0.0011023113109243879));
        units.add(new Unit(Unit.LONG_TON, R.string.long_ton, 1016.0469088, 0.0009842065276110606282276));
        addConversion(Conversion.MASS, new Conversion(Conversion.MASS, R.string.mass, units));
    }

    private void getPowerConversions()
    {
        //Base unit - Watt

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.WATT, R.string.watt, 1.0, 1.0));
        units.add(new Unit(Unit.KILOWATT, R.string.kilowatt, 1000.0, 0.001));
        units.add(new Unit(Unit.MEGAWATT, R.string.megawatt, 1000000.0, 0.000001));
        units.add(new Unit(Unit.HP, R.string.hp, 735.49875, 0.00135962161730390432));
        units.add(new Unit(Unit.HP_UK, R.string.hp_uk, 745.69987158227022, 0.00134102208959502793));
        units.add(new Unit(Unit.FT_LBF_S, R.string.ft_lbf_s, 1.3558179483314004, 0.737562149277265364));
        units.add(new Unit(Unit.CALORIE_S, R.string.calorie_s, 4.1868, 0.23884589662749594));
        units.add(new Unit(Unit.BTU_S, R.string.btu_s, 1055.05585262, 0.0009478171203133172));
        units.add(new Unit(Unit.KVA, R.string.kva, 1000.0, 0.001));
        units.add(new Unit(Unit.ELECTRON_VOLT, R.string.electron_volt, 6241509479607718382.9424839, 0.000000000000000000160217653));
        addConversion(Conversion.POWER, new Conversion(Conversion.POWER, R.string.power, units));
    }

    private void getPressureConversions()
    {
        //Base unit - Pa

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.MEGAPASCAL, R.string.megapascal, 1000000.0, 0.000001));
        units.add(new Unit(Unit.KILOPASCAL, R.string.kilopascal, 1000.0, 0.001));
        units.add(new Unit(Unit.PASCAL, R.string.pascal, 1.0, 1.0));
        units.add(new Unit(Unit.BAR, R.string.bar, 100000.0, 0.00001));
        units.add(new Unit(Unit.PSI, R.string.psi, 6894.757293168361, 0.000145037737730209222));
        units.add(new Unit(Unit.PSF, R.string.psf, 47.880258980335840277777777778, 0.020885434233150127968));
        units.add(new Unit(Unit.ATMOSPHERE, R.string.atmosphere, 101325.0, 0.0000098692326671601283));
        units.add(new Unit(Unit.MMHG, R.string.mmhg, 133.322387415, 0.007500615758456563339513));
        units.add(new Unit(Unit.TORR, R.string.torr, 133.3223684210526315789, 0.00750061682704169751));
        addConversion(Conversion.PRESSURE, new Conversion(Conversion.PRESSURE, R.string.pressure, units));
    }

    private void getSpeedConversions()
    {
        //base unit - m/s

        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.KM_HR, R.string.km_h, 0.27777777777778, 3.6));
        units.add(new Unit(Unit.MPH, R.string.mph, 0.44704, 2.2369362920544));
        units.add(new Unit(Unit.M_S, R.string.m_s, 1.0, 1.0));
        units.add(new Unit(Unit.FPS, R.string.fps, 0.3048, 3.2808398950131));
        units.add(new Unit(Unit.KNOT, R.string.knot, 0.51444444444444, 1.9438444924406));
        addConversion(Conversion.SPEED, new Conversion(Conversion.SPEED, R.string.speed, units));
    }

    private void getTemperatureConversions()
    {
        List<Unit> units = new ArrayList<Unit>();
        units.add(new TemperatureUnit(Unit.CELSIUS, R.string.celsius));
        units.add(new TemperatureUnit(Unit.FAHRENHEIT, R.string.fahrenheit));
        units.add(new TemperatureUnit(Unit.KELVIN, R.string.kelvin));
        units.add(new TemperatureUnit(Unit.RANKINE, R.string.rankine));
        units.add(new TemperatureUnit(Unit.DELISLE, R.string.delisle));
        units.add(new TemperatureUnit(Unit.NEWTON, R.string.newton));
        units.add(new TemperatureUnit(Unit.REAUMUR, R.string.reaumur));
        units.add(new TemperatureUnit(Unit.ROMER, R.string.romer));
        units.add(new TemperatureUnit(Unit.GAS_MARK, R.string.gas_mark));
        addConversion(Conversion.TEMPERATURE, new Conversion(Conversion.TEMPERATURE, R.string.temperature, units));
    }

    private void getTimeConversions()
    {
        //Base unit - seconds
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.YEAR, R.string.year, 31536000.0, 0.0000000317097919837645865));
        units.add(new Unit(Unit.MONTH, R.string.month, 2628000.0, 0.0000003805175));
        units.add(new Unit(Unit.WEEK, R.string.week, 604800.0, 0.00000165343915343915344));
        units.add(new Unit(Unit.DAY, R.string.day, 86400.0, 0.0000115740740740740741));
        units.add(new Unit(Unit.HOUR, R.string.hour, 3600.0, 0.000277777777777777778));
        units.add(new Unit(Unit.MINUTE, R.string.minute, 60.0, 0.0166666666666666667));
        units.add(new Unit(Unit.SECOND, R.string.second, 1.0, 1.0));
        units.add(new Unit(Unit.MILLISECOND, R.string.millisecond, 0.001, 1000.0));
        units.add(new Unit(Unit.NANOSECOND, R.string.nanosecond, 0.000000001, 1000000000.0));
        addConversion(Conversion.TIME, new Conversion(Conversion.TIME, R.string.time, units));
    }

    private void getTorqueConversions()
    {
        // Base unit - Newton-metres
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.N_M, R.string.n_m, 31536000.0, 0.0000000317097919837645865));
        units.add(new Unit(Unit.FT_LBF, R.string.ft_lbF, 1.3558179483314004, 0.7375621494575464935503));
        units.add(new Unit(Unit.IN_LBF, R.string.in_lbF, 0.1129848290276167, 8.850745793490557922604));
        addConversion(Conversion.TORQUE, new Conversion(Conversion.TORQUE, R.string.torque, units));
    }

    private void getVolumeConversions()
    {
        // Base unit - cubic metre
        List<Unit> units = new ArrayList<Unit>();
        units.add(new Unit(Unit.TEASPOON, R.string.teaspoon, 0.0000049289215938, 202884.136211058));
        units.add(new Unit(Unit.TABLESPOON, R.string.tablespoon, 0.0000147867647812, 67628.045403686));
        units.add(new Unit(Unit.CUP, R.string.cup, 0.0002365882365, 4226.7528377304));
        units.add(new Unit(Unit.FLUID_OUNCE, R.string.fluid_ounce, 0.0000295735295625, 33814.0227018429972));
        units.add(new Unit(Unit.FLUID_OUNCE_UK, R.string.fluid_ounce_uk, 0.0000284130625, 35195.07972785404600437));
        units.add(new Unit(Unit.PINT, R.string.pint, 0.000473176473, 2113.37641886518732));
        units.add(new Unit(Unit.PINT_UK, R.string.pint_uk, 0.00056826125, 1759.753986392702300218));
        units.add(new Unit(Unit.QUART, R.string.quart, 0.000946352946, 1056.68820943259366));
        units.add(new Unit(Unit.QUART_UK, R.string.quart_uk, 0.0011365225, 879.8769931963511501092));
        units.add(new Unit(Unit.GALLON, R.string.gallon, 0.003785411784, 264.172052358148415));
        units.add(new Unit(Unit.GALLON_UK, R.string.gallon_uk, 0.00454609, 219.9692482990877875273));
        units.add(new Unit(Unit.BARREL, R.string.barrel, 0.119240471196, 8.38641436057614017079));
        units.add(new Unit(Unit.BARREL_UK, R.string.barrel_uk, 0.16365924, 6.11025689719688298687));
        units.add(new Unit(Unit.MILLILITRE, R.string.millilitre, 0.000001, 1000000.0));
        units.add(new Unit(Unit.LITRE, R.string.litre, 0.001, 1000.0));
        units.add(new Unit(Unit.CUBIC_CM, R.string.cubic_cm, 0.000001, 1000000.0));
        units.add(new Unit(Unit.CUBIC_M, R.string.cubic_m, 1.0, 1.0));
        units.add(new Unit(Unit.CUBIC_INCH, R.string.cubic_inch, 0.000016387064, 61023.744094732284));
        units.add(new Unit(Unit.CUBIC_FOOT, R.string.cubic_foot, 0.028316846592, 35.3146667214885903));
        units.add(new Unit(Unit.CUBIC_YARD, R.string.cubic_yard, 0.7645548692741148, 1.3079506));
        addConversion(Conversion.VOLUME, new Conversion(Conversion.VOLUME, R.string.volume, units));
    }
}
