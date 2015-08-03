package com.physphil.android.unitconverterultimate.models;

/**
 * Database object which contains state (selected From and To units) of current conversion
 * Created by Phizz on 15-08-02.
 */
public class ConversionState
{
    private int conversionId;
    private int fromId;
    private int toId;

    public ConversionState(int conversionId)
    {
        this.conversionId = conversionId;
        this.fromId = 0;
        this.toId = 1;
    }

    public ConversionState(int conversionId, int fromId, int toId)
    {
        this.conversionId = conversionId;
        this.fromId = fromId;
        this.toId = toId;
    }

    public int getConversionId()
    {
        return conversionId;
    }

    public int getFromId()
    {
        return fromId;
    }

    public int getToId()
    {
        return toId;
    }

    public void setFromId(int fromId)
    {
        this.fromId = fromId;
    }

    public void setToId(int toId)
    {
        this.toId = toId;
    }
}
