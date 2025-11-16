package com.example.babyhelper.data.repository

import com.example.babyhelper.data.db.SleepDao
import com.example.babyhelper.data.db.SleepEntry
import kotlinx.coroutines.flow.Flow

class SleepRepository(private val dao: SleepDao) {
    fun getAll(): Flow<List<SleepEntry>> = dao.getAll()
    suspend fun insert(entry: SleepEntry) = dao.insert(entry)
    suspend fun update(entry: SleepEntry) = dao.update(entry)
    suspend fun delete(entry: SleepEntry) = dao.delete(entry)
    suspend fun getById(id: Long) = dao.getById(id)
}