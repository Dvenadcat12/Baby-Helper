package com.example.babyhelper.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "sleep_entries")
data class SleepEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val startTime: Long,   // epoch millis
    val endTime: Long?,    // null — если ещё спит
    val note: String? = null,
    val mood: String? = null
) {
    val durationMillis: Long?
        get() = endTime?.let { it - startTime }
}