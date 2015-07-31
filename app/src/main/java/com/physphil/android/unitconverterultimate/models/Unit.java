package com.physphil.android.unitconverterultimate.models;

/**
 * A unit that can be converted to/from
 * Created by Phizz on 15-07-25.
 */
public class Unit
{
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
    public Unit(int id, int labelResource, double conversionToBase, double conversionFromBase)
    {
        this.id = id;
        this.labelResource = labelResource;
        this.conversionToBase = conversionToBase;
        this.conversionFromBase = conversionFromBase;
    }

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

    /**
     * Checks if the unit is the base unit for the conversion
     * @return if the unit is the base unit
     */
    public boolean isBaseUnit()
    {
        return conversionToBase == 1.0;
    }
}
