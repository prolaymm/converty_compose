package com.wavetest.ajcurrency.domain.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wavetest.ajcurrency.utils.DatabaseConstants
import java.time.LocalTime
import java.util.Date

@Entity(DatabaseConstants.TIME_SAVER_TABLE)
data class TimeSaverVo(

    @PrimaryKey
    val id : Int= 1,

    @ColumnInfo("time")
    val time : Date? = null)