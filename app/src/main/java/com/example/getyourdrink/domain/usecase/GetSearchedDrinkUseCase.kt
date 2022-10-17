package com.example.getyourdrink.domain.usecase

import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.domain.repository.DrinkRepository

class GetSearchedDrinkUseCase(private val drinkRepository: DrinkRepository) {
    suspend fun execute(query: String) : List<Drink> = drinkRepository.getSearchedDrinks(query)
}