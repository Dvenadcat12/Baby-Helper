package com.example.babyhelper.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.babyhelper.data.repository.SleepRepository
import com.example.babyhelper.data.db.SleepEntry
import kotlinx.coroutines.launch

class SleepViewModel(private val repo: SleepRepository) : ViewModel() {
    val sleepList = repo.getAll().asLiveData()

    fun addSleep(start: Long, end: Long?, note: String? = null, mood: String? = null) {
        viewModelScope.launch {
            val entry = SleepEntry(startTime = start, endTime = end, note = note, mood = mood)
            repo.insert(entry)
        }
    }

    fun updateEntry(entry: SleepEntry) {
        viewModelScope.launch { repo.update(entry) }
    }

    fun deleteEntry(entry: SleepEntry) {
        viewModelScope.launch { repo.delete(entry) }
    }
}