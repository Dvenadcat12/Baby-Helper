package com.example.babyhelper.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepDao {
    @Query("SELECT * FROM sleep_entries ORDER BY startTime DESC")
    fun getAll(): Flow<List<SleepEntry>>

    @Insert
    suspend fun insert(entry: SleepEntry): Long

    @Update
    suspend fun update(entry: SleepEntry)

    @Delete
    suspend fun delete(entry: SleepEntry)

    @Query("SELECT * FROM sleep_entries WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): SleepEntry?
}