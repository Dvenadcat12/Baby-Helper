package com.example.babyhelper.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.babyhelper.data.model.Child

@Database(entities = [Child::class], version = 1)
abstract class ChildDatabase : RoomDatabase() {

    abstract fun childDao(): ChildDao

    companion object {
        @Volatile
        private var INSTANCE: ChildDatabase? = null

        fun getDatabase(context: Context): ChildDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ChildDatabase::class.java,
                    "child_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
