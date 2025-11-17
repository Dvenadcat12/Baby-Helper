package com.example.babyhelper.data.db

import androidx.room.*
import com.example.babyhelper.data.model.Child

@Dao
interface ChildDao {

    @Query("SELECT * FROM child_table")
    suspend fun getAllChildren(): List<Child>

    @Insert
    suspend fun insert(child: Child)

    @Update
    suspend fun update(child: Child)

    @Delete
    suspend fun delete(child: Child)
}
