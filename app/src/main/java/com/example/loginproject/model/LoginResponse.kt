package com.example.loginproject.model

data class LoginResponse(
    val ExceptionMessage: Any,
    val HttpStatusCode: Int,
    val IsSuccess: Boolean,
    val data: List<LoginData>?,
    val message: String
)