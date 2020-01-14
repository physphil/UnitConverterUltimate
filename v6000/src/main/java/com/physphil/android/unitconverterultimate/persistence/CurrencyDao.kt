package com.physphil.android.unitconverterultimate.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.physphil.android.unitconverterultimate.persistence.models.RateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRates(rates: List<RateEntity>)

    @Query("SELECT * FROM $TABLE_CURRENCY_RATES")
    fun getRates(): Flow<List<RateEntity>>

    @Query("SELECT COUNT(*) FROM $TABLE_CURRENCY_RATES")
    suspend fun getRatesCount(): Int
}