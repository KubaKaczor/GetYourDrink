package com.example.getyourdrink.presentation.di

import com.example.getyourdrink.BuildConfig
import com.example.getyourdrink.BuildConfig.BASE_URL
import com.example.getyourdrink.data.api.AlcoholService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideAlcoholService(retrofit: Retrofit): AlcoholService{
        return retrofit.create(AlcoholService::class.java)
    }
}