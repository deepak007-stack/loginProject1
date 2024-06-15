package com.example.loginproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginproject.model.StatusResponse
import com.example.loginproject.repository.StatusRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatusViewModel(private val repository: StatusRepository) :ViewModel() {

    private val _userDataFlow = MutableStateFlow<StatusResponse?>(null)
    val userDataFlow = _userDataFlow.asStateFlow()

    fun getUser(pid: String, ftype: String,lstatus : String) {
        viewModelScope.launch {
            try {
//                _userDataFlow.value = Response.Loading()
                val response = repository.getSubStatusData(pid, ftype,lstatus)
//                _userDataFlow.value = Response.Success(response)
//                delay(2000)
                _userDataFlow.value = response
            } catch (e: Exception) {

            }
        }
    }
}