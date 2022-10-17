package com.example.getyourdrink.presentation.di

import com.example.getyourdrink.domain.repository.DrinkRepository
import com.example.getyourdrink.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetRandomDrinkUseCase(repository: DrinkRepository): GetRandomDrinkUseCase{
        return GetRandomDrinkUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedDrinkUseCase(repository: DrinkRepository): GetSearchedDrinkUseCase{
        return GetSearchedDrinkUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetDrinksByCategoryUseCase(repository: DrinkRepository): GetDrinksByCategoryUseCase{
        return GetDrinksByCategoryUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetDrinkByIdUseCase(repository: DrinkRepository): GetDrinkByIdUseCase{
        return GetDrinkByIdUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideInsertDrinkUseCase(repository: DrinkRepository): InsertDrinkUseCase{
        return InsertDrinkUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideDeleteDrinkUseCase(repository: DrinkRepository): DeleteDrinkUseCase{
        return DeleteDrinkUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetFavouritesDrinksUseCase(repository: DrinkRepository): GetFavouritesDrinkUseCase{
        return GetFavouritesDrinkUseCase(repository)
    }
}