package com.physphil.android.unitconverterultimate.models;

/**
 * Temperature Unit for conversion
 * Created by Phizz on 15-07-31.
 */
public final class TemperatureUnit extends Unit
{
    /**
     * Create a new Temperature unit
     * @param id unit id
     * @param labelResource resource of string label
     */
    public TemperatureUnit(int id, int labelResource)
    {
        // Don't require conversion info, handled in code
        super(id, labelResource, 0.0, 0.0);
    }
}
