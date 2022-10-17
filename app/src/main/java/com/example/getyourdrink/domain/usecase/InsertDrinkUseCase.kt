package com.example.getyourdrink.domain.usecase

import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.domain.repository.DrinkRepository

class InsertDrinkUseCase(private val drinkRepository: DrinkRepository) {
    suspend fun execute(drink: Drink) = drinkRepository.insert(drink)
}