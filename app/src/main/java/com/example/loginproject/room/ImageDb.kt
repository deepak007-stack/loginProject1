package com.example.loginproject.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
abstract class ImageDb() : RoomDatabase(){
    abstract val dao : ImageDao
}
