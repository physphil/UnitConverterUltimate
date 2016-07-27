package com.physphil.android.unitconverterultimate.api;

import com.physphil.android.unitconverterultimate.api.models.CurrencyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Retrofit service to consume Fixer.io API
 * Created by Phizz on 16-07-26.
 */
public interface FixerService
{
    @GET("latest")
    Call<CurrencyResponse> getLatestRates(@Query("base") String base);
}
