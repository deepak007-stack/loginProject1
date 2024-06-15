package com.example.loginproject.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val uri : String
)
