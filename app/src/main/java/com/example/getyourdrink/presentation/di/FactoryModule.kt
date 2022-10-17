package com.example.getyourdrink.presentation.di

import android.app.Application
import com.example.getyourdrink.domain.usecase.*
import com.example.getyourdrink.presentation.viewmodel.DrinkViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideFactory(
        app: Application,
        getRandomDrinkUseCase: GetRandomDrinkUseCase,
        getDrinkByIdUseCase: GetDrinkByIdUseCase,
        getDrinksByCategoryUseCase: GetDrinksByCategoryUseCase,
        getSearchedDrinkUseCase: GetSearchedDrinkUseCase,
        insertDrinkUseCase: InsertDrinkUseCase,
        deleteDrinkUseCase: DeleteDrinkUseCase,
        getFavouritesDrinkUseCase: GetFavouritesDrinkUseCase
    ): DrinkViewModelFactory{
        return DrinkViewModelFactory(
            app,
            getRandomDrinkUseCase,
            getDrinkByIdUseCase,
            getDrinksByCategoryUseCase,
            getSearchedDrinkUseCase,
            insertDrinkUseCase,
            deleteDrinkUseCase,
            getFavouritesDrinkUseCase
            )
    }
}