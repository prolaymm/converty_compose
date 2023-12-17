package com.wavetest.ajcurrency.core.service.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wavetest.ajcurrency.utils.DatabaseConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule  {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context : Context) : AppDatabase {
        return  Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCurrencyDao(appDatabase: AppDatabase) : CurrencyDao {
        return  appDatabase.getCurrencyDao()
    }

    @Provides
    @Singleton
    fun provideRateDao(appDatabase: AppDatabase) : RateDao {
        return  appDatabase.getRateDao()
    }

    @Provides
    @Singleton
    fun provideTimeSaverDao(appDatabase: AppDatabase) : TimeSaverDao {
        return  appDatabase.getTimeSaverDao()
    }
}