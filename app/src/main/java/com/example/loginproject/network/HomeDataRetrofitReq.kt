package com.example.loginproject.network

import com.example.loginproject.model.HomeScreenResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HomeScreenReq {

    suspend fun loadDataForList(
        mobileNumber: String,
        caseId: String? = null,
        process: String
    ): HomeScreenResponse {

        val BASE_URl = "https://avcv.svgcso.com/"

        return try {
            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val services: APIServices = retrofit.create(APIServices::class.java)
            return services.getData(mobileNumber, caseId ?: "",process)
        }
        catch (e : Exception){
            HomeScreenResponse(
                ExceptionMessage = e.message ?: "",
                HttpStatusCode = 0, // Set appropriate value for HttpStatusCode
                IsSuccess = false,
                data = emptyList(), // Set empty list or default data
                message = ""
            )
        }
    }
}