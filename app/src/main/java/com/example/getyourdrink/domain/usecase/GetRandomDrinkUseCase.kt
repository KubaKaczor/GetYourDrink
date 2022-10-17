package com.example.getyourdrink.domain.usecase

import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.domain.repository.DrinkRepository
import javax.inject.Inject

class GetRandomDrinkUseCase(private val drinkRepository: DrinkRepository) {
    suspend fun execute() : Drink? = drinkRepository.getRandomDrink()
}