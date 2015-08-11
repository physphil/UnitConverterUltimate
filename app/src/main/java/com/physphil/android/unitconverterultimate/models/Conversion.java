package com.physphil.android.unitconverterultimate.models;

import android.support.annotation.IntDef;

import java.util.List;

/**
 * Object representing a conversion type, ie Area, Power, Time, etc.
 * Created by Phizz on 15-07-25.
 */
public final class Conversion
{
    // Conversion types. Use integer over enum as value is stored in database and shared prefs
    // Use @IntDef for type safety
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

    private int id;
    private int labelResource;
    private List<Unit> units;

    @IntDef({AREA, COOKING, STORAGE, ENERGY, FUEL, LENGTH, MASS, POWER, PRESSURE, SPEED,
    TEMPERATURE, TIME, TORQUE, VOLUME})
    public @interface id{}

    /**
     * Create a Conversion object
     * @param id id of the conversion
     * @param labelResource string resource id for the conversion
     * @param units list of units contained in conversion
     */
    public Conversion(@id int id, int labelResource, List<Unit> units)
    {
        this.id = id;
        this.labelResource = labelResource;
        this.units = units;
    }

    @id
    public int getId()
    {
        return id;
    }

    public int getLabelResource()
    {
        return labelResource;
    }

    public List<Unit> getUnits()
    {
        return units;
    }

    public Unit getUnitById(int id)
    {
        return units.get(id);
    }
}
