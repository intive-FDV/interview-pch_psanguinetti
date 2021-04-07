package com.challenge.myfirstmillion.di

import android.content.Context
import androidx.room.Room
import com.challenge.myfirstmillion.BuildConfig
import com.challenge.myfirstmillion.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        //TODO add schema
        return Room.databaseBuilder(context, AppDatabase::class.java, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}