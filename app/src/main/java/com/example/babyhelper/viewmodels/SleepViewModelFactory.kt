package com.example.babyhelper.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.babyhelper.data.repository.SleepRepository

class SleepViewModelFactory(private val repo: SleepRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SleepViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
