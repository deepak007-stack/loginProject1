package com.example.loginproject.network

import com.example.loginproject.model.DetailsScreenResponse
import com.example.loginproject.model.HomeScreenResponse
import com.example.loginproject.model.LoginResponse
import com.example.loginproject.model.StatusResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {

    //    val base_url = "https://avcv.svgcso.com/avcv.asmx/appgetloginold?CusMobile&IMSI"
    @GET("avcv.asmx/appgetloginold")
    suspend fun login(
        @Query("CusMobile") mobileNumber: String,
        @Query("IMSI") imsi: String,
    ): LoginResponse

    //    val base_url = "https://avcv.svgcso.com/avcv.asmx/appgetleadshow"
    @GET("avcv.asmx/appgetleadshow")
    suspend fun getData(
        @Query("CusMobile") mobileNo: String,
        @Query("CaseID") caseId: String,
        @Query("Process") process: String
    ): HomeScreenResponse

    //https://avcv.svgcso.com/avcv.asmx/getLeadFieldDetails
    @GET("avcv.asmx/getLeadFieldDetails")
    suspend fun getDetailsData(
        @Query("PID") pid: String,
        @Query("FType") ftype: String,
    ) : DetailsScreenResponse


//    https://avcv.svgcso.com/avcv.asmx/getleadSubReasion
    @GET("avcv.asmx/getleadSubReasion")
    suspend fun getSubStatusData(
        @Query("PID") pid: String,
        @Query("FType") ftype: String,
        @Query("lStatus") istatus : String
    ) : StatusResponse


}