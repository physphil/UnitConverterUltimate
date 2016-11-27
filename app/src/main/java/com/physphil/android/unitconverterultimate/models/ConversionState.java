/*
 * Copyright 2016 Phil Shadlyn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physphil.android.unitconverterultimate.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Database object which contains state (selected From and To units) of current conversion
 * Created by Phizz on 15-08-02.
 */
public final class ConversionState implements Parcelable {

    private int conversionId;
    private int fromId;
    private int toId;

    public ConversionState(@Conversion.id int conversionId) {
        this.conversionId = conversionId;
        this.fromId = -1;
        this.toId = -1;
    }

    public ConversionState(@Conversion.id int conversionId, @Unit.id int fromId, @Unit.id int toId) {
        this.conversionId = conversionId;
        this.fromId = fromId;
        this.toId = toId;
    }

    @Conversion.id
    public int getConversionId() {
        return conversionId;
    }

    @Unit.id
    public int getFromId() {
        return fromId;
    }

    @Unit.id
    public int getToId() {
        return toId;
    }

    public void setFromId(@Unit.id int fromId) {
        this.fromId = fromId;
    }

    public void setToId(@Unit.id int toId) {
        this.toId = toId;
    }

    protected ConversionState(Parcel in) {
        conversionId = in.readInt();
        fromId = in.readInt();
        toId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(conversionId);
        dest.writeInt(fromId);
        dest.writeInt(toId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ConversionState> CREATOR = new Parcelable.Creator<ConversionState>() {
        @Override
        public ConversionState createFromParcel(Parcel in) {
            return new ConversionState(in);
        }

        @Override
        public ConversionState[] newArray(int size) {
            return new ConversionState[size];
        }
    };
}
