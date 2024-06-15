package com.example.loginproject.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StatusRetroReq {

    private const val BASE_URl = "https://avcv.svgcso.com/"
//  https://avcv.svgcso.com/avcv.asmx/getleadSubReasion

    private var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val services : APIServices = retrofit.create(APIServices::class.java)
}