package com.physphil.android.unitconverterultimate.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Database object which contains state (selected From and To units) of current conversion
 * Created by Phizz on 15-08-02.
 */
public class ConversionState implements Parcelable
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

    protected ConversionState(Parcel in)
    {
        conversionId = in.readInt();
        fromId = in.readInt();
        toId = in.readInt();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(conversionId);
        dest.writeInt(fromId);
        dest.writeInt(toId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ConversionState> CREATOR = new Parcelable.Creator<ConversionState>()
    {
        @Override
        public ConversionState createFromParcel(Parcel in)
        {
            return new ConversionState(in);
        }

        @Override
        public ConversionState[] newArray(int size)
        {
            return new ConversionState[size];
        }
    };
}
