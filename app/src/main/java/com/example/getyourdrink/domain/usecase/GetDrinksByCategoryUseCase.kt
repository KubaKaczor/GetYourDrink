package com.example.getyourdrink.domain.usecase

import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.domain.repository.DrinkRepository

class GetDrinksByCategoryUseCase(private val drinkRepository: DrinkRepository) {
    suspend fun execute(category: String): List<Drink> = drinkRepository.getDrinksByCategory(category)
}