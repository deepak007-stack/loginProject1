package com.example.loginproject.room

import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface ImageDao {

    suspend fun insert(item : ImageEntity)

    @Query("SELECT * FROM users")
    fun getAll(): Flow<List<ImageEntity>>
}