package com.example.getyourdrink.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.getyourdrink.domain.usecase.*

class DrinkViewModelFactory(
    private val app: Application,
    private val getRandomDrinkUseCase: GetRandomDrinkUseCase,
    private val getDrinkByIdUseCase: GetDrinkByIdUseCase,
    private val getDrinksByCategoryUseCase: GetDrinksByCategoryUseCase,
    private val getSearchedDrinkUseCase: GetSearchedDrinkUseCase,
    private val insertDrinkUseCase: InsertDrinkUseCase,
    private val deleteDrinkUseCase: DeleteDrinkUseCase,
    private val getFavouritesDrinkUseCase: GetFavouritesDrinkUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DrinkViewModel(
            app,
            getRandomDrinkUseCase,
            getDrinkByIdUseCase,
            getDrinksByCategoryUseCase,
            getSearchedDrinkUseCase,
            insertDrinkUseCase,
            deleteDrinkUseCase,
            getFavouritesDrinkUseCase
        ) as T
    }
}