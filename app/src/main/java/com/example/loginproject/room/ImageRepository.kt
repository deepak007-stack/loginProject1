package com.example.loginproject.room

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepository @Inject constructor(private val dao : ImageDao) {
    suspend fun insert(item: ImageEntity){
        dao.insert(item)
    }


    fun getAll(): Flow<List<ImageEntity>> {
        return dao.getAll()
    }
}