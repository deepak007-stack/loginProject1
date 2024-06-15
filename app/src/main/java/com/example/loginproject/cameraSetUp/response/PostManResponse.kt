package com.example.loginproject.cameraSetUp.response

import com.squareup.moshi.Json

data class PostManResponse(
    @field:Json(name = "url") val url: String?
)
