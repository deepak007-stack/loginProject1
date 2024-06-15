package com.example.loginproject.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginproject.model.DetailsScreenResponse
import com.example.loginproject.repository.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: LoginRepository) : ViewModel() {

    private val _userDataFlow = MutableStateFlow<DetailsScreenResponse?>(null)
    val userDataFlow = _userDataFlow.asStateFlow()

    fun getUser(pid: String, ftype: String) {
        viewModelScope.launch {
            try {
//                _userDataFlow.value = Response.Loading()
                val response = repository.getData(pid, ftype)
//                _userDataFlow.value = Response.Success(response)
//                delay(2000)
                _userDataFlow.value = response
            } catch (e: Exception) {

            }
        }
    }

}