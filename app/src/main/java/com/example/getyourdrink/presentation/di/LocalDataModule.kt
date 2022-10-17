package com.example.getyourdrink.presentation.di

import com.example.getyourdrink.data.db.DrinkDao
import com.example.getyourdrink.data.repository.dataSource.DrinkLocalDataSource
import com.example.getyourdrink.data.repository.dataSourceImpl.DrinkLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideDrinkLocalDataSource(drinkDao: DrinkDao): DrinkLocalDataSource{
        return DrinkLocalDataSourceImpl(drinkDao)
    }
}