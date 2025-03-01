package com.example.loginproject.network

import com.example.loginproject.model.LoginResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LoginRetrofitReq {

    suspend fun login(mobileNumber: String, imsi: String): LoginResponse {

        val BASE_URl = "https://avcv.svgcso.com/"

        return try {
            var retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val services: APIServices = retrofit.create(APIServices::class.java)
            return services.login(mobileNumber, imsi)
        } catch (e: Exception) {
//            LoginResponse(status = "error", message = e.message ?: "Unknown error")
            LoginResponse("Api is not loading properly",400,false,null,"")
        }
    }
}