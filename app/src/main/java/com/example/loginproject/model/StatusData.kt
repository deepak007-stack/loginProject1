package com.example.loginproject.model

data class StatusData(
    var SubStatus: String
){
    fun clearSubStatus(): StatusData {
        return this.copy(SubStatus = "") // Assuming you want to clear SubStatus to an empty string
    }
}