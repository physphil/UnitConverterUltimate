package com.physphil.android.unitconverterultimate.data

import android.util.Log
import com.physphil.android.unitconverterultimate.data.network.CurrencyApi
import com.physphil.android.unitconverterultimate.data.network.model.toRateEntities
import com.physphil.android.unitconverterultimate.models.Currency
import com.physphil.android.unitconverterultimate.persistence.CurrencyDao
import java.math.BigDecimal

class CurrencyRepository(
    private val api: CurrencyApi,
    private val dao: CurrencyDao
) {

    suspend fun getRates(): Unit {
        // TODO: Placeholder for repo to indicate to UI that conversion is ready to go
        // Return DB results. If empty, hit API, persist and return DB results.
        val response = api.getLatestRates()
        Log.d("phil", "response = $response")
        val entities = response.toRateEntities()
        dao.insertRates(entities)
        Log.d("phil", "insert complete!")
    }

    fun convert(value: BigDecimal, initial: Currency, final: Currency): BigDecimal {
        TODO()
    }
}