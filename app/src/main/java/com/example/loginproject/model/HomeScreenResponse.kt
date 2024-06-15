package com.example.loginproject.model

data class HomeScreenResponse(
    val ExceptionMessage: Any,
    val HttpStatusCode: Int,
    val IsSuccess: Boolean,
    val `data`: List<HomeData>,
    val message: String
)