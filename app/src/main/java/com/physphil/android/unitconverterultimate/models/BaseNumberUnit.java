package com.physphil.android.unitconverterultimate.models;

public class BaseNumberUnit extends Unit{

    /**
     * Create a unit object
     *
     * @param id                 id of the unit
     * @param labelResource      string resource id of the label
     */
    public BaseNumberUnit(int id, int labelResource) {

        //Need custom function to convert
        super(id, labelResource, 0.0, 0.0);

    }
}
