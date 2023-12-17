package com.wavetest.ajcurrency.core.service.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.wavetest.ajcurrency.core.helper.LocalDateTimeConverter
import com.wavetest.ajcurrency.domain.vos.CurrencyVo
import com.wavetest.ajcurrency.domain.vos.RateVo
import com.wavetest.ajcurrency.domain.vos.TimeSaverVo

@Database(version = 6, entities = [CurrencyVo::class,RateVo::class,TimeSaverVo::class], exportSchema = true)
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract  fun getCurrencyDao() : CurrencyDao
    abstract  fun getRateDao() : RateDao
    abstract  fun getTimeSaverDao() : TimeSaverDao
}