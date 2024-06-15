package com.example.loginproject.room.di

import android.content.Context
import androidx.room.Room
import com.example.loginproject.room.ImageDao
import com.example.loginproject.room.ImageDb
import com.example.loginproject.room.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDao(db: ImageDb): ImageDao {
        return db.dao
    }


    @Provides
    @Singleton
    fun providesImageDb(@ApplicationContext context: Context): ImageDb {
        return Room.databaseBuilder(context, ImageDb::class.java, "image_db").build()
    }


    @Provides
    @Singleton
    fun providesRepository(dao: ImageDao): ImageRepository {
        return ImageRepository(dao = dao)
    }

}