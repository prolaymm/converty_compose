package com.wavetest.ajcurrency.core.service.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wavetest.ajcurrency.domain.vos.CurrencyVo
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {


    @Query("SELECT * from CurrencyTable")
    fun  getAllCurrency() : Flow<List<CurrencyVo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insetSingleCurrency( users: CurrencyVo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertCurrency(users: List<CurrencyVo>)

    @Delete
    fun deleteAll(user: CurrencyVo)
}