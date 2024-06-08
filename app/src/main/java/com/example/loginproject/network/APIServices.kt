package com.example.loginproject.network

import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

//    val base_url = "https://avcv.svgcso.com/avcv.asmx/appgetloginold?CusMobile&IMSI"

    @GET("avcv.asmx/appgetloginold")
    suspend fun login(
        @Query("CusMobile") mobileNumber : String,
        @Query("IMSI") imsi : String,
    ) : LoginResponse

}