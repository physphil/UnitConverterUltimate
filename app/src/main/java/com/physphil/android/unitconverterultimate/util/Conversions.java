package com.physphil.android.unitconverterultimate.util;

import com.physphil.android.unitconverterultimate.R;
import com.physphil.android.unitconverterultimate.models.Conversion;
import com.physphil.android.unitconverterultimate.models.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Contains HashMaps for all conversions. Key is the name of the radio button ID
 */

public class Conversions {
	
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
    
    public static final String FROM = "from";
    public static final String TO = "to";
    
    public static final String SQ_KILOMETRE = "SqKilometre";
    public static final String SQ_METRE = "SqMetre";
    public static final String SQ_CENTIMETRE = "SqCentimetre";
    public static final String HECTARE = "Hectare";
    public static final String SQ_MILE = "SqMile";
    public static final String SQ_YARD = "SqYard";
    public static final String SQ_FOOT = "SqFoot";
    public static final String SQ_INCH = "SqInch";
    public static final String ACRE = "Acre";
    
    public static final String TEASPOON = "Teaspoon";
    public static final String TABLESPOON = "Tablespoon";
    public static final String FLUID_OUNCE = "FluidOunce";
    public static final String CUP = "Cup";
    public static final String PINT = "Pint";
    public static final String QUART = "Quart";
    public static final String GALLON = "Gallon";
    public static final String BARREL = "Barrel";
    public static final String FLUID_OUNCEUK = "FluidOunceUK";
    public static final String PINTUK = "PintUK";
    public static final String QUARTUK = "QuartUK";
    public static final String GALLONUK = "GallonUK";
    public static final String BARRELUK = "BarrelUK";
    public static final String MILLILITRE = "Millilitre";
    public static final String LITRE = "Litre";
    public static final String CUBIC_CENTIMETRE = "CubicCm";
    public static final String CUBIC_METRE = "CubicM";
    public static final String CUBIC_INCH = "CubicInch";
    public static final String CUBIC_FOOT = "CubicFoot";
    public static final String CUBIC_YARD = "CubicYard";
    
    public static final String BIT = "Bit";
    public static final String BYTE = "Byte";
    public static final String KILOBIT = "Kilobit";
    public static final String KILOBYTE = "Kilobyte";
    public static final String MEGABIT = "Megabit";
    public static final String MEGABYTE = "Megabyte";
    public static final String GIGABIT = "Gigabit";
    public static final String GIGABYTE = "Gigabyte";
    public static final String TERABIT = "Terabit";
    public static final String TERABYTE = "Terabyte";
    
    public static final String JOULE = "Joule";
    public static final String KILOJOULE = "Kilojoule";
    public static final String CALORIE = "Calorie";
    public static final String KILOCALORIE = "Kilocalorie";
    public static final String BTU = "Btu";
    public static final String FT_LB_F = "FtlbF";
    public static final String IN_LB_F = "InlbF";
    public static final String KILOWATT_HOUR = "KilowattHour";
    public static final String ELECTRON_VOLT = "ElectronVolt";
    public static final String N_M = "Nm";
    
    public static final String MILES_PER_GALLONUS = "MpgUS";
    public static final String MILES_PER_GALLONUK = "MpgUK";
    public static final String LITRES_PER_100K = "Lp100k";
    public static final String KILOMETRES_PER_LITRE = "Kmpl";
    public static final String MILES_PER_LITRE = "Mpl";
    
    public static final String METRE = "Metre";
    public static final String KILOMETRE = "Kilometre";
    public static final String MILE = "Mile";
    public static final String CENTIMETRE = "Centimetre";
    public static final String MILLIMETRE = "Millimetre";
    public static final String MICROMETRE = "Micrometre";
    public static final String NANOMETRE = "Nanometre";
    public static final String YARD = "Yard";
    public static final String FEET = "Feet";
    public static final String INCH = "Inch";
    public static final String NAUTICAL_MILE = "NauticalMile";
    public static final String FURLONG = "Furlong";
    public static final String LIGHT_YEAR = "LightYear";
    
    public static final String KILOGRAM = "Kilogram";
    public static final String POUND = "Pound";
    public static final String GRAM = "Gram";
    public static final String MILLIGRAM = "Milligram";
    public static final String OUNCE = "Ounce";
    public static final String GRAIN = "Grain";
    public static final String STONE = "Stone";
    public static final String METRIC_TON = "MetricTon";
    public static final String SHORT_TON = "ShortTon";
    public static final String LONG_TON = "LongTon";
    
    public static final String WATT = "Watt";
    public static final String KILOWATT = "Kilowatt";
    public static final String MEGAWATT = "Megawatt";
    public static final String HORSEPOWER = "Hp";
    public static final String HORSEPOWERUK = "HpUK";
    public static final String FOOT_LB_FORCE_PER_SECOND = "FtLbfps";
    public static final String CALORIE_PER_SECOND = "Calps";
    public static final String BTU_PER_SECOND = "Btups";
    public static final String KILOVOLT_AMPERE = "kVA";
    
    public static final String PASCAL = "Pascal";
    public static final String KILOPASCAL = "kPascal";
    public static final String MEGAPASCAL = "MPascal";
    public static final String BAR = "Bar";
    public static final String PSI = "Psi";
    public static final String PSF = "Psf";
    public static final String ATMOSPHERE = "Atmosphere";
    public static final String MM_MERCURY = "Mmhg";
    public static final String TORR = "Torr";
    
    public static final String KILOMETRES_PER_HOUR = "Kmph";
    public static final String MILES_PER_HOUR = "Mph";
    public static final String METRES_PER_SECOND = "Mps";
    public static final String FEET_PER_SECOND = "Fps";
    public static final String KNOT = "Knot";
    
    public static final String YEAR = "Year";
    public static final String MONTH = "Month";
    public static final String WEEK = "Week";
    public static final String DAY = "Day";
    public static final String HOUR = "Hour";
    public static final String MINUTE = "Minute";
    public static final String SECOND = "Second";
    public static final String MILLISECOND = "Millisecond";
    public static final String NANOSECOND = "Nanosecond";
    
    private static Conversions instance = null;
    private List<Conversion> mConversions = new ArrayList<Conversion>();
    private HashMap<String, Double> conversions = new HashMap<String, Double>();
    
    private Conversions(){
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
    	getTimeConversions();
        getTorqueConversions();
        getVolumeConversions();
    }
    
    public static Conversions getInstance(){
    	
    	//Create singleton to contain all conversions
    	if(instance == null){
    		instance = new Conversions();
    	}
    	
    	return instance;
    }

    public List<Conversion> getConversion()
    {
        return mConversions;
    }

    public HashMap<String, Double> getConversions(){
    	return conversions;
    }

    private void getAreaConversions(){
        //Base unit: square metre
   
        conversions.put(FROM + SQ_KILOMETRE, 1000000.0);
        conversions.put(FROM + SQ_METRE, 1.0);
        conversions.put(FROM + SQ_CENTIMETRE, 0.0001);
        conversions.put(FROM + HECTARE, 10000.0);
        conversions.put(FROM + SQ_MILE, 2589988.110336);
        conversions.put(FROM + SQ_YARD, 0.83612736);
        conversions.put(FROM + SQ_FOOT, 0.09290304);
        conversions.put(FROM + SQ_INCH, 0.00064516);
        conversions.put(FROM + ACRE, 4046.8564224);

        conversions.put(TO + SQ_KILOMETRE, 0.000001);
        conversions.put(TO + SQ_METRE, 1.0);
        conversions.put(TO + SQ_CENTIMETRE, 10000.0);
        conversions.put(TO + HECTARE, 0.0001);
        conversions.put(TO + SQ_MILE, 0.000000386102158542445847);
        conversions.put(TO + SQ_YARD, 1.19599004630108026);
        conversions.put(TO + SQ_FOOT, 10.7639104167097223);
        conversions.put(TO + SQ_INCH, 1550.00310000620001);
        conversions.put(TO + ACRE, 0.000247105381467165342);
    }
    
    private void getCookingConversions(){
    	//Base Unit = cubic metres
	
//    	conversions.put(FROM + TEASPOON, 0.0000049289215938);
//    	conversions.put(FROM + TABLESPOON, 0.0000147867647812);
//    	conversions.put(FROM + FLUID_OUNCE, 0.0000295735295625);
//    	conversions.put(FROM + FLUID_OUNCEUK, 0.0000284130625);
//    	conversions.put(FROM + CUP, 0.0002365882365);
//    	conversions.put(FROM + PINT, 0.000473176473);
//    	conversions.put(FROM + PINTUK, 0.00056826125);
//    	conversions.put(FROM + QUART, 0.000946352946);
//    	conversions.put(FROM + QUARTUK, 0.0011365225);
//    	conversions.put(FROM + GALLON, 0.003785411784);
//    	conversions.put(FROM + GALLONUK, 0.00454609);
//    	conversions.put(FROM + BARREL, 0.119240471196);
//    	conversions.put(FROM + BARRELUK, 0.16365924);
//    	conversions.put(FROM + MILLILITRE, 0.000001);
//    	conversions.put(FROM + LITRE, 0.001);
//    	conversions.put(FROM + CUBIC_CENTIMETRE, 0.000001);
//    	conversions.put(FROM + CUBIC_METRE, 1.0);
//    	conversions.put(FROM + CUBIC_INCH, 0.000016387064);
//    	conversions.put(FROM + CUBIC_FOOT, 0.028316846592);
//    	conversions.put(FROM + CUBIC_YARD, 0.7645548692741148);
//
//    	conversions.put(TO + TEASPOON, 202884.136211058);
//    	conversions.put(TO + TABLESPOON, 67628.045403686);
//    	conversions.put(TO + FLUID_OUNCE, 33814.0227018429972);
//    	conversions.put(TO + FLUID_OUNCEUK, 35195.07972785404600437);
//    	conversions.put(TO + CUP, 4226.7528377304);
//    	conversions.put(TO + PINT, 2113.37641886518732);
//    	conversions.put(TO + PINTUK, 1759.753986392702300218);
//    	conversions.put(TO + QUART, 1056.68820943259366);
//    	conversions.put(TO + QUARTUK, 879.8769931963511501092);
//    	conversions.put(TO + GALLON, 264.172052358148415);
//    	conversions.put(TO + GALLONUK, 219.9692482990877875273);
//    	conversions.put(TO + BARREL, 8.38641436057614017079);
//    	conversions.put(TO + BARRELUK, 6.11025689719688298687);
//    	conversions.put(TO + MILLILITRE, 1000000.0);
//    	conversions.put(TO + LITRE, 1000.0);
//    	conversions.put(TO + CUBIC_CENTIMETRE, 1000000.0);
//    	conversions.put(TO + CUBIC_METRE, 1.0);
//    	conversions.put(TO + CUBIC_INCH, 61023.744094732284);
//    	conversions.put(TO + CUBIC_FOOT, 35.3146667214885903);
//    	conversions.put(TO + CUBIC_YARD, 1.3079506);
    }
    
    private void getStorageConversions(){
    	//Base Unit = megabyte
	
    	conversions.put(FROM + BIT, 0.00000011920928955078);
    	conversions.put(FROM + BYTE, 0.00000095367431640625);
    	conversions.put(FROM + KILOBIT, 0.0001220703125);
    	conversions.put(FROM + KILOBYTE, 0.0009765625);
    	conversions.put(FROM + MEGABIT, 0.125);
    	conversions.put(FROM + MEGABYTE, 1.0);
    	conversions.put(FROM + GIGABIT, 128.0);
    	conversions.put(FROM + GIGABYTE, 1024.0);
    	conversions.put(FROM + TERABIT, 131072.0);
    	conversions.put(FROM + TERABYTE, 1048576.0);
    	
    	conversions.put(TO + BIT, 8388608.0);
    	conversions.put(TO + BYTE, 1048576.0);
    	conversions.put(TO + KILOBIT, 8192.0);
    	conversions.put(TO + KILOBYTE, 1024.0);
    	conversions.put(TO + MEGABIT, 8.0);
    	conversions.put(TO + MEGABYTE, 1.0);
    	conversions.put(TO + GIGABIT, 0.0078125);
    	conversions.put(TO + GIGABYTE, 0.0009765625);
    	conversions.put(TO + TERABIT, 0.00000762939453125);
    	conversions.put(TO + TERABYTE, 0.00000095367431640625);
    }
    
    private void getEnergyConversions(){
    	//Base unit Joules
	
    	conversions.put(FROM + JOULE, 1.0);
    	conversions.put(FROM + KILOJOULE, 1000.0);
    	conversions.put(FROM + CALORIE, 4.184);
    	conversions.put(FROM + KILOCALORIE, 4184.0);
    	conversions.put(FROM + BTU, 1055.05585262);
    	conversions.put(FROM + FT_LB_F, 1.3558179483314004);
    	conversions.put(FROM + IN_LB_F, 0.1129848290276167);
    	conversions.put(FROM + KILOWATT_HOUR, 3600000.0);
    	conversions.put(FROM + ELECTRON_VOLT, 6241509479607718382.9424839);
    	
    	conversions.put(TO + JOULE, 1.0);
    	conversions.put(TO + KILOJOULE, 0.001);
    	conversions.put(TO + CALORIE, 0.2390057361376673040153);
    	conversions.put(TO + KILOCALORIE, 0.0002390057361376673040153);
    	conversions.put(TO + BTU, 0.0009478171203133172000128);
    	conversions.put(TO + FT_LB_F, 0.7375621494575464935503);
    	conversions.put(TO + IN_LB_F, 8.850745793490557922604);
    	conversions.put(TO + KILOWATT_HOUR, 0.0000002777777777777777777778);
    	conversions.put(TO + ELECTRON_VOLT, 0.000000000000000000160217653);
    }
    
    private void getFuelConversions(){
    	//Base Unit - Miles per Gallon US
    	
    	conversions.put(FROM + MILES_PER_GALLONUS, 1.0);
    	conversions.put(FROM + MILES_PER_GALLONUK, 0.83267418460479);
    	conversions.put(FROM + LITRES_PER_100K, 235.214582);
    	conversions.put(FROM + KILOMETRES_PER_LITRE, 2.352145833);
    	conversions.put(FROM + MILES_PER_LITRE, 3.7854118);
    	
    	conversions.put(TO + MILES_PER_GALLONUS, 1.0);
    	conversions.put(TO + MILES_PER_GALLONUK, 1.2009499255398);
    	conversions.put(TO + LITRES_PER_100K, 235.214582);
    	conversions.put(TO + KILOMETRES_PER_LITRE, 0.42514370749052);
    	conversions.put(TO + MILES_PER_LITRE, 0.264172052);    	
    }
    
    private void getLengthConversions(){
    	//Base unit - Metres
    	
    	conversions.put(FROM + KILOMETRE, 1000.0);
    	conversions.put(FROM + MILE, 1609.344);
    	conversions.put(FROM + METRE, 1.0);
    	conversions.put(FROM + CENTIMETRE, 0.01);
    	conversions.put(FROM + MILLIMETRE, 0.001);
    	conversions.put(FROM + MICROMETRE, 0.000001);
    	conversions.put(FROM + NANOMETRE, 0.000000001);
    	conversions.put(FROM + YARD, 0.9144);
    	conversions.put(FROM + FEET, 0.3048);
    	conversions.put(FROM + INCH, 0.0254);
    	conversions.put(FROM + NAUTICAL_MILE, 1852.0);
    	conversions.put(FROM + FURLONG, 201.168);
    	conversions.put(FROM + LIGHT_YEAR, 9460730472580800.0);

    	conversions.put(TO + KILOMETRE, 0.001);
    	conversions.put(TO + MILE, 0.00062137119223733397);
    	conversions.put(TO + METRE, 1.0);
    	conversions.put(TO + CENTIMETRE, 100.0);
    	conversions.put(TO + MILLIMETRE, 1000.0);
    	conversions.put(TO + MICROMETRE, 1000000.0);
    	conversions.put(TO + NANOMETRE, 1000000000.0);
    	conversions.put(TO + YARD, 1.09361329833770779);
    	conversions.put(TO + FEET, 3.28083989501312336);
    	conversions.put(TO + INCH, 39.3700787401574803);
    	conversions.put(TO + NAUTICAL_MILE, 0.000539956803455723542);
    	conversions.put(TO + FURLONG, 0.0049709695379);
    	conversions.put(TO + LIGHT_YEAR, 0.0000000000000001057000834024615463709);

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
        mConversions.add(new Conversion(LENGTH, R.string.length, units));
    }
    
    private void getMassConversions(){
    	//Base unit - Kilograms
    	
    	conversions.put(FROM + KILOGRAM, 1.0);
    	conversions.put(FROM + POUND, 0.45359237);
    	conversions.put(FROM + GRAM, 0.001);
    	conversions.put(FROM + MILLIGRAM, 0.000001);
    	conversions.put(FROM + OUNCE, 0.028349523125);
    	conversions.put(FROM + GRAIN, 0.00006479891);
    	conversions.put(FROM + STONE, 6.35029318);
    	conversions.put(FROM + METRIC_TON, 1000.0);
    	conversions.put(FROM + SHORT_TON, 907.18474);
    	conversions.put(FROM + LONG_TON, 1016.0469088);
    	
    	conversions.put(TO + KILOGRAM, 1.0);
    	conversions.put(TO + POUND, 2.20462262184877581);
    	conversions.put(TO + GRAM, 1000.0);
    	conversions.put(TO + MILLIGRAM, 1000000.0);
    	conversions.put(TO + OUNCE, 35.27396194958041291568);
    	conversions.put(TO + GRAIN, 15432.35835294143065061);
    	conversions.put(TO + STONE, 0.15747304441777);
    	conversions.put(TO + METRIC_TON, 0.001);
    	conversions.put(TO + SHORT_TON, 0.0011023113109243879);
    	conversions.put(TO + LONG_TON, 0.0009842065276110606282276);   	
    }
    
    private void getPowerConversions(){
    	//Base unit - Watt
    	
    	conversions.put(FROM + WATT, 1.0);
    	conversions.put(FROM + KILOWATT, 1000.0);
    	conversions.put(FROM + MEGAWATT, 1000000.0);
    	conversions.put(FROM + HORSEPOWER, 735.49875);
    	conversions.put(FROM + HORSEPOWERUK, 745.69987158227022);
    	conversions.put(FROM + FOOT_LB_FORCE_PER_SECOND, 1.3558179483314004);
    	conversions.put(FROM + CALORIE_PER_SECOND, 4.1868);
    	conversions.put(FROM + BTU_PER_SECOND, 1055.05585262);
    	conversions.put(FROM + KILOVOLT_AMPERE, 1000.0);

    	conversions.put(TO + WATT, 1.0);
    	conversions.put(TO + KILOWATT, 0.001);
    	conversions.put(TO + MEGAWATT, 0.000001);
    	conversions.put(TO + HORSEPOWER, 0.00135962161730390432);
    	conversions.put(TO + HORSEPOWERUK, 0.00134102208959502793);
    	conversions.put(TO + FOOT_LB_FORCE_PER_SECOND, 0.737562149277265364);
    	conversions.put(TO + CALORIE_PER_SECOND, 0.23884589662749594);
    	conversions.put(TO + BTU_PER_SECOND, 0.0009478171203133172);
    	conversions.put(TO + KILOVOLT_AMPERE, 0.001);
    }
    
    private void getPressureConversions(){
    	//Base unit - Pa
    	
    	conversions.put(FROM + MEGAPASCAL, 1000000.0);
    	conversions.put(FROM + KILOPASCAL, 1000.0);
    	conversions.put(FROM + PASCAL, 1.0);
    	conversions.put(FROM + BAR, 100000.0);
    	conversions.put(FROM + PSI, 6894.757293168361);
    	conversions.put(FROM + PSF, 47.880258980335840277777777778);
    	conversions.put(FROM + ATMOSPHERE, 101325.0);
    	conversions.put(FROM + MM_MERCURY, 133.322387415);
    	// conversions.put(FROM + TORR, 133.322387415);
    	conversions.put(FROM + TORR, 133.3223684210526315789);

    	conversions.put(TO + MEGAPASCAL, 0.000001);
    	conversions.put(TO + KILOPASCAL, 0.001);
    	conversions.put(TO + PASCAL, 1.0);
    	conversions.put(TO + BAR, 0.00001);
    	conversions.put(TO + PSI, 0.000145037737730209222);
    	conversions.put(TO + PSF, 0.020885434233150127968);
    	conversions.put(TO + ATMOSPHERE, 0.0000098692326671601283);
    	conversions.put(TO + MM_MERCURY, 0.007500615758456563339513);
    	conversions.put(TO + TORR, 0.00750061682704169751);
    }
    
    private void getSpeedConversions(){
    	//base unit - m/s
    	
    	conversions.put(FROM + KILOMETRES_PER_HOUR, 0.27777777777778);
    	conversions.put(FROM + MILES_PER_HOUR, 0.44704);
    	conversions.put(FROM + METRES_PER_SECOND, 1.0);
    	conversions.put(FROM + FEET_PER_SECOND, 0.3048);
    	conversions.put(FROM + KNOT, 0.51444444444444);
    	
    	conversions.put(TO + KILOMETRES_PER_HOUR, 3.6);
    	conversions.put(TO + MILES_PER_HOUR, 2.2369362920544);
    	conversions.put(TO + METRES_PER_SECOND, 1.0);
    	conversions.put(TO + FEET_PER_SECOND, 3.2808398950131);
    	conversions.put(TO + KNOT, 1.9438444924406);	
    }
    
    private void getTimeConversions(){
    	//Base unit - seconds
    	
    	conversions.put(FROM + YEAR, 31536000.0);
    	conversions.put(FROM + MONTH, 2628000.0);
    	conversions.put(FROM + WEEK, 604800.0);
    	conversions.put(FROM + DAY, 86400.0);
    	conversions.put(FROM + HOUR, 3600.0);
    	conversions.put(FROM + MINUTE, 60.0);
    	conversions.put(FROM + SECOND, 1.0);
    	conversions.put(FROM + MILLISECOND, 0.001);
    	conversions.put(FROM + NANOSECOND, 0.000000001);
    	
    	conversions.put(TO + YEAR, 0.0000000317097919837645865);
    	conversions.put(TO + MONTH, 0.0000003805175);
    	conversions.put(TO + WEEK, 0.00000165343915343915344);
    	conversions.put(TO + DAY, 0.0000115740740740740741);
    	conversions.put(TO + HOUR, 0.000277777777777777778);
    	conversions.put(TO + MINUTE, 0.0166666666666666667);
    	conversions.put(TO + SECOND, 1.0);
    	conversions.put(TO + MILLISECOND, 1000.0);
    	conversions.put(TO + NANOSECOND, 1000000000.0);
    }

    private void getTorqueConversions(){
        // Base unit - Newton-metres
        conversions.put(FROM + N_M, 1.0);
        conversions.put(TO + N_M, 1.0);

        // In-lb and ft-lb are included in Energy
    }

    private void getVolumeConversions(){
        conversions.put(FROM + TEASPOON, 0.0000049289215938);
        conversions.put(FROM + TABLESPOON, 0.0000147867647812);
        conversions.put(FROM + FLUID_OUNCE, 0.0000295735295625);
        conversions.put(FROM + FLUID_OUNCEUK, 0.0000284130625);
        conversions.put(FROM + CUP, 0.0002365882365);
        conversions.put(FROM + PINT, 0.000473176473);
        conversions.put(FROM + PINTUK, 0.00056826125);
        conversions.put(FROM + QUART, 0.000946352946);
        conversions.put(FROM + QUARTUK, 0.0011365225);
        conversions.put(FROM + GALLON, 0.003785411784);
        conversions.put(FROM + GALLONUK, 0.00454609);
        conversions.put(FROM + BARREL, 0.119240471196);
        conversions.put(FROM + BARRELUK, 0.16365924);
        conversions.put(FROM + MILLILITRE, 0.000001);
        conversions.put(FROM + LITRE, 0.001);
        conversions.put(FROM + CUBIC_CENTIMETRE, 0.000001);
        conversions.put(FROM + CUBIC_METRE, 1.0);
        conversions.put(FROM + CUBIC_INCH, 0.000016387064);
        conversions.put(FROM + CUBIC_FOOT, 0.028316846592);
        conversions.put(FROM + CUBIC_YARD, 0.7645548692741148);

        conversions.put(TO + TEASPOON, 202884.136211058);
        conversions.put(TO + TABLESPOON, 67628.045403686);
        conversions.put(TO + FLUID_OUNCE, 33814.0227018429972);
        conversions.put(TO + FLUID_OUNCEUK, 35195.07972785404600437);
        conversions.put(TO + CUP, 4226.7528377304);
        conversions.put(TO + PINT, 2113.37641886518732);
        conversions.put(TO + PINTUK, 1759.753986392702300218);
        conversions.put(TO + QUART, 1056.68820943259366);
        conversions.put(TO + QUARTUK, 879.8769931963511501092);
        conversions.put(TO + GALLON, 264.172052358148415);
        conversions.put(TO + GALLONUK, 219.9692482990877875273);
        conversions.put(TO + BARREL, 8.38641436057614017079);
        conversions.put(TO + BARRELUK, 6.11025689719688298687);
        conversions.put(TO + MILLILITRE, 1000000.0);
        conversions.put(TO + LITRE, 1000.0);
        conversions.put(TO + CUBIC_CENTIMETRE, 1000000.0);
        conversions.put(TO + CUBIC_METRE, 1.0);
        conversions.put(TO + CUBIC_INCH, 61023.744094732284);
        conversions.put(TO + CUBIC_FOOT, 35.3146667214885903);
        conversions.put(TO + CUBIC_YARD, 1.3079506);
    }
}
