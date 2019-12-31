package com.physphil.android.unitconverterultimate.persistence

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amitshekhar.DebugDB
import com.physphil.android.unitconverterultimate.persistence.models.RateEntity

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "unit_converter_database"

const val TABLE_CURRENCY_RATES = "currency_rates"
const val RATES_COLUMN_CODE = "code"
const val RATES_COLUMN_RATE = "rate"

@Database(entities = [RateEntity::class], version = DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build().also {
                Log.d("phil", "Database address: ${DebugDB.getAddressLog()}")
            }
    }
}