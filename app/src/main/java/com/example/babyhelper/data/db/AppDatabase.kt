package com.example.babyhelper.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.babyhelper.data.db.SleepDao
import com.example.babyhelper.data.db.SleepEntry

@Database(entities = [SleepEntry::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sleepDao(): SleepDao
    // Остальные Dao добавим позже

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "babyhelper_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}