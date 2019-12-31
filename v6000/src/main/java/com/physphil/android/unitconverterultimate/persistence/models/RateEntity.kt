package com.physphil.android.unitconverterultimate.persistence.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.physphil.android.unitconverterultimate.persistence.RATES_COLUMN_CODE
import com.physphil.android.unitconverterultimate.persistence.RATES_COLUMN_RATE
import com.physphil.android.unitconverterultimate.persistence.TABLE_CURRENCY_RATES

@Entity(tableName = TABLE_CURRENCY_RATES)
data class RateEntity(
    @PrimaryKey
    @ColumnInfo(name = RATES_COLUMN_CODE)
    val code: String,
    @ColumnInfo(name = RATES_COLUMN_RATE)
    val rate: String
)