package com.example.getyourdrink.domain.repository

import com.example.getyourdrink.data.model.Drink
import kotlinx.coroutines.flow.Flow

interface DrinkRepository {
    suspend fun getRandomDrink(): Drink?
    suspend fun getDrinkById(id: String): Drink?
    suspend fun getDrinksByCategory(category: String): List<Drink>
    suspend fun getSearchedDrinks(query: String): List<Drink>

    //local db
    suspend fun insert(drink: Drink)
    suspend fun delete(drink: Drink)
    fun getFavouritesDrinks(): Flow<List<Drink>>

}