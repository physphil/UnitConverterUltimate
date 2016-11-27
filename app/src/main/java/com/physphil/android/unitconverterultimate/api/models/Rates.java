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

package com.physphil.android.unitconverterultimate.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * List of currency rates from Fixer.io API
 * Created by Phizz on 16-07-26.
 */
public class Rates {

    @SerializedName("AUD") double aud;
    @SerializedName("BGN") double bgn;
    @SerializedName("BRL") double brl;
    @SerializedName("CAD") double cad;
    @SerializedName("CHF") double chf;
    @SerializedName("CNY") double cny;
    @SerializedName("CZK") double czk;
    @SerializedName("DKK") double dkk;
    @SerializedName("GBP") double gbp;
    @SerializedName("HKD") double hkd;
    @SerializedName("HRK") double hrk;
    @SerializedName("HUF") double huf;
    @SerializedName("IDR") double idr;
    @SerializedName("ILS") double ils;
    @SerializedName("INR") double inr;
    @SerializedName("JPY") double jpy;
    @SerializedName("KRW") double krw;
    @SerializedName("MXN") double mxn;
    @SerializedName("MYR") double myr;
    @SerializedName("NOK") double nok;
    @SerializedName("NZD") double nzd;
    @SerializedName("PHP") double php;
    @SerializedName("PLN") double pln;
    @SerializedName("RON") double ron;
    @SerializedName("RUB") double rub;
    @SerializedName("SEK") double sek;
    @SerializedName("SGD") double sgd;
    @SerializedName("THB") double thb;
    @SerializedName("TRY") double lira;
    @SerializedName("USD") double usd;
    @SerializedName("ZAR") double zar;

    public double getAud() {
        return aud;
    }

    public double getBgn() {
        return bgn;
    }

    public double getBrl() {
        return brl;
    }

    public double getCad() {
        return cad;
    }

    public double getChf() {
        return chf;
    }

    public double getCny() {
        return cny;
    }

    public double getCzk() {
        return czk;
    }

    public double getDkk() {
        return dkk;
    }

    public double getGbp() {
        return gbp;
    }

    public double getHkd() {
        return hkd;
    }

    public double getHrk() {
        return hrk;
    }

    public double getHuf() {
        return huf;
    }

    public double getIdr() {
        return idr;
    }

    public double getIls() {
        return ils;
    }

    public double getInr() {
        return inr;
    }

    public double getJpy() {
        return jpy;
    }

    public double getKrw() {
        return krw;
    }

    public double getMxn() {
        return mxn;
    }

    public double getMyr() {
        return myr;
    }

    public double getNok() {
        return nok;
    }

    public double getNzd() {
        return nzd;
    }

    public double getPhp() {
        return php;
    }

    public double getPln() {
        return pln;
    }

    public double getRon() {
        return ron;
    }

    public double getRub() {
        return rub;
    }

    public double getSek() {
        return sek;
    }

    public double getSgd() {
        return sgd;
    }

    public double getThb() {
        return thb;
    }

    public double getLira() {
        return lira;
    }

    public double getUsd() {
        return usd;
    }

    public double getZar() {
        return zar;
    }
}
