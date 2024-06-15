package com.example.loginproject.repository

import com.example.loginproject.model.DetailsScreenResponse
import com.example.loginproject.network.APIServices

class LoginRepository(private val apiServices: APIServices) {

    suspend fun getData(pid : String,ftype : String) : DetailsScreenResponse{
       return apiServices.getDetailsData(pid,ftype)
    }
}