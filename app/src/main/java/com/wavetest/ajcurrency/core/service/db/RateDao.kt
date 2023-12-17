package com.wavetest.ajcurrency.core.service.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wavetest.ajcurrency.domain.vos.RateVo
import kotlinx.coroutines.flow.Flow

@Dao
interface RateDao {

    @Query("SELECT * from RateTable")
    fun  getAllRate() : Flow<List<RateVo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insetSingleRate( users: RateVo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertRate(users: List<RateVo>)

    @Delete
    fun deleteAll(user: RateVo)
}