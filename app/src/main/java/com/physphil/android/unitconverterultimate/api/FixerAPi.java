package com.physphil.android.unitconverterultimate.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Singleton to access the Fixer.io API
 * Created by Phizz on 16-07-26.
 */
public class FixerApi
{
    private static final String BASE_URL = "http://api.fixer.io";
    private static FixerApi mInstance;

    private FixerService mService;

    private FixerApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = retrofit.create(FixerService.class);
    }

    public static FixerApi getInstance()
    {
        if (mInstance == null)
        {
            mInstance = new FixerApi();
        }

        return mInstance;
    }

    public FixerService getService()
    {
        return mService;
    }
}
