package edu.cuhk.csci3310.data

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [Group::class, Habit::class, Record::class, HabitGroupCrossRef::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val habitDao: HabitDao
    abstract val groupDao: GroupDao

    companion object {
        private var instance: AppDatabase? = null

        fun getAppDatabase(context: Context): AppDatabase? {
            if (instance == null) {
                instance = databaseBuilder(context.applicationContext, AppDatabase::class.java, "habit_tracker_db").build()
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

}
