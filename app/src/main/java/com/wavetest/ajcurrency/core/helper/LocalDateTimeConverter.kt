package com.wavetest.ajcurrency.core.helper

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.util.Date

class LocalDateTimeConverter {

    @TypeConverter
     fun dateToTimeStamp(date: Date?) : Long? {
        return date?.time
     }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun timeStampToDate(value: Long?) : Date?{
        return value?.let { Date(it) }
    }
}