package com.wavetest.ajcurrency.core.service.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wavetest.ajcurrency.domain.vos.TimeSaverVo
import kotlinx.coroutines.flow.Flow

@Dao
interface   TimeSaverDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTime(timeSaverVo: TimeSaverVo)

    @Query("select * from TimeTable where id=1")
    fun getTime() : Flow<TimeSaverVo>
}