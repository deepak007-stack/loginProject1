package com.example.loginproject.repository

import com.example.loginproject.model.StatusResponse
import com.example.loginproject.network.APIServices

class StatusRepository(private val apiServices: APIServices) {

    suspend fun getSubStatusData(pid : String,ftype : String,lstatus : String) : StatusResponse {
        return apiServices.getSubStatusData(pid,ftype,lstatus)
    }
}