package com.physphil.android.unitconverterultimate.data

import com.physphil.android.unitconverterultimate.data.network.CurrencyApi
import com.physphil.android.unitconverterultimate.data.network.model.toRateEntities
import com.physphil.android.unitconverterultimate.persistence.CurrencyDao
import com.physphil.android.unitconverterultimate.persistence.models.RateEntity
import kotlinx.coroutines.flow.Flow

class CurrencyRepository(
    private val api: CurrencyApi,
    private val dao: CurrencyDao
) {

    suspend fun getRates(): Flow<List<RateEntity>> {
        val hasRates = dao.getRatesCount() > 0
        if (!hasRates) {
            val response = api.getLatestRates()
            dao.insertRates(response.toRateEntities())
        }
        return dao.getRates()
    }
}