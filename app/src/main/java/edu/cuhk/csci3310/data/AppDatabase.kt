package edu.cuhk.csci3310.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [Group::class, Habit::class, Record::class, HabitGroupCrossRef::class],
    version = 3,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3)
    ]
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val habitDao: HabitDao
    abstract val groupDao: GroupDao

    companion object {
        private var instance: AppDatabase? = null

        // notifications cannot have dependency injection
        // we build the database here instead
        fun getAppDatabase(context: Context): AppDatabase? {
            if (instance == null) {
                instance = databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "habit_tracker_db"
                ).build()
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }
    }

}
