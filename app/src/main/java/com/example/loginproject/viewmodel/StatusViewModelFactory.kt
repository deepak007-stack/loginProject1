package com.example.loginproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginproject.repository.StatusRepository

class StatusViewModelFactory(private val repository: StatusRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StatusViewModel(repository) as T
    }
}