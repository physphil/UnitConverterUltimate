package com.physphil.android.unitconverterultimate.data.network

import com.physphil.android.unitconverterultimate.data.network.model.CurrencyResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.exchangeratesapi.io/"

class CurrencyApi {

    private val service: CurrencyService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CurrencyService::class.java)

    suspend fun getLatestRates(): CurrencyResponse = service.getLatestRates()
}