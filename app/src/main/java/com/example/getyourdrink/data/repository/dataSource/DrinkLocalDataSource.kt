package com.example.getyourdrink.data.repository.dataSource

import com.example.getyourdrink.data.model.Drink
import kotlinx.coroutines.flow.Flow

interface DrinkLocalDataSource {
    suspend fun insert(drink: Drink)
    suspend fun delete(drink: Drink)
    fun getFavouritesDrinks(): Flow<List<Drink>>
}