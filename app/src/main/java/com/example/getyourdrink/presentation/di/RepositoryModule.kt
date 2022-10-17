package com.example.getyourdrink.presentation.di

import com.example.getyourdrink.data.repository.dataSource.DrinkRemoteDataSource
import com.example.getyourdrink.data.repository.DrinkRepositoryImpl
import com.example.getyourdrink.data.repository.dataSource.DrinkLocalDataSource
import com.example.getyourdrink.domain.repository.DrinkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryModule(
        drinkRemoteDataSource: DrinkRemoteDataSource,
        drinkLocalDataSource: DrinkLocalDataSource
    ): DrinkRepository{
        return DrinkRepositoryImpl(drinkRemoteDataSource, drinkLocalDataSource)
    }
}