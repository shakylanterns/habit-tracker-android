package edu.cuhk.csci3310.data

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

@TypeConverters
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
