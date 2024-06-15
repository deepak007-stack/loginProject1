package com.example.loginproject.model

data class StatusResponse(
    val ExceptionMessage: Any,
    val HttpStatusCode: Int,
    val IsSuccess: Boolean,
    val `data`: List<StatusData>,
    val message: String
)