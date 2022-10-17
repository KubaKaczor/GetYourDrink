package com.example.getyourdrink.domain.usecase

import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.domain.repository.DrinkRepository

class GetDrinkByIdUseCase(private val drinkRepository: DrinkRepository) {
    suspend fun execute(id: String): Drink? = drinkRepository.getDrinkById(id)
}