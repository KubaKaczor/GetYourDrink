package com.example.getyourdrink.presentation.di

import com.example.getyourdrink.data.api.AlcoholService
import com.example.getyourdrink.data.repository.dataSource.DrinkRemoteDataSource
import com.example.getyourdrink.data.repository.dataSourceImpl.DrinkRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(alcoholService: AlcoholService): DrinkRemoteDataSource {
        return DrinkRemoteDataSourceImpl(alcoholService)
    }
}