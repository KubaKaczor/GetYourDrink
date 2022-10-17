package com.example.getyourdrink.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.getyourdrink.data.db.DrinkDao
import com.example.getyourdrink.data.db.DrinkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDrinkDatabase(app: Application): DrinkDatabase{
        return Room.databaseBuilder(app, DrinkDatabase::class.java, "drink_app_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDrinkDao(drinkDatabase: DrinkDatabase): DrinkDao{
        return drinkDatabase.drinkDao()
    }
}