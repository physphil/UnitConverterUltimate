package com.physphil.android.unitconverterultimate.models;

import java.util.List;

/**
 * Object representing a conversion type, ie Area, Power, Time, etc.
 * Created by Phizz on 15-07-25.
 */
public class Conversion
{
    private int id;
    private int labelResource;
    private List<Unit> units;

    /**
     * Create a Conversion object
     * @param id id of the conversion
     * @param labelResource string resource id for the conversion
     * @param units list of units contained in conversion
     */
    public Conversion(int id, int labelResource, List<Unit> units)
    {
        this.id = id;
        this.labelResource = labelResource;
        this.units = units;
    }

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

    public int getNumberUnits()
    {
        return units.size();
    }
}
