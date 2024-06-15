package com.example.loginproject.model

data class DetailsScreenResponse(
    val ExceptionMessage: Any,
    val HttpStatusCode: Int,
    val IsSuccess: Boolean,
    val `data`: List<DetailsData>,
    val message: String
)