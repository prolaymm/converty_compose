package com.wavetest.ajcurrency.domain.vos

import androidx.room.ColumnInfo
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wavetest.ajcurrency.utils.DatabaseConstants

@Entity(DatabaseConstants.RATE_TABLE)
class RateVo(
    @PrimaryKey
    @ColumnInfo("currencyCode")
    val currencyCode : String,
    @ColumnInfo("price")
    val price : Double
    )

