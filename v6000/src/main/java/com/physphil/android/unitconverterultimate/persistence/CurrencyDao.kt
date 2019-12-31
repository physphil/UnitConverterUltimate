package com.physphil.android.unitconverterultimate.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.physphil.android.unitconverterultimate.persistence.models.RateEntity

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRates(rates: List<RateEntity>)

    @Query("SELECT * FROM $TABLE_CURRENCY_RATES")
    suspend fun getRates(): List<RateEntity>
}