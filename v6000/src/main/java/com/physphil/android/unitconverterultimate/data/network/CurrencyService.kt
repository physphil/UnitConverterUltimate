package com.physphil.android.unitconverterultimate.data.network

import com.physphil.android.unitconverterultimate.data.network.model.CurrencyResponse
import retrofit2.http.GET

interface CurrencyService {
    @GET("latest")
    suspend fun getLatestRates(): CurrencyResponse
}