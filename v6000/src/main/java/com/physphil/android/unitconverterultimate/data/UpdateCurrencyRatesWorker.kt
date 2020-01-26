package com.physphil.android.unitconverterultimate.data

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.physphil.android.unitconverterultimate.data.network.CurrencyApi
import com.physphil.android.unitconverterultimate.persistence.AppDatabase

class UpdateCurrencyRatesWorker(private val context: Context, params: WorkerParameters) :
    CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        val repo = CurrencyRepository(
            api = CurrencyApi(),
            dao = AppDatabase.getInstance(context).currencyDao()
        )
        repo.updateRates()
        return Result.success()
    }
}