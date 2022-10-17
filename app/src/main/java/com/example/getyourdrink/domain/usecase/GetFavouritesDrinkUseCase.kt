package com.example.getyourdrink.domain.usecase

import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.domain.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesDrinkUseCase(private val drinkRepository: DrinkRepository) {
    fun execute(): Flow<List<Drink>> = drinkRepository.getFavouritesDrinks()
}