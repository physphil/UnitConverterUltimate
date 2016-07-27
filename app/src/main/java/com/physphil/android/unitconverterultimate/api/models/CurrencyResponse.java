package com.physphil.android.unitconverterultimate.api.models;

/**
 * Response object sent from Fixer.io API
 * Created by Phizz on 16-07-26.
 */
public class CurrencyResponse
{
    private String base;
    private String date;
    private Rates rates;

    public String getBase()
    {
        return base;
    }

    public String getDate()
    {
        return date;
    }

    public Rates getRates()
    {
        return rates;
    }
}
