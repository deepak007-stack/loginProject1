package com.example.loginproject

import android.app.Application
import com.example.loginproject.network.DetailsRetrofitReq
import com.example.loginproject.repository.LoginRepository
import com.example.loginproject.repository.StatusRepository
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LoginApplication : Application() {

    lateinit var loginRepository : LoginRepository
    lateinit var statusRepository : StatusRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize(){
        val apiServices = DetailsRetrofitReq.services
        loginRepository = LoginRepository(apiServices)
        statusRepository = StatusRepository(apiServices)
    }
}
