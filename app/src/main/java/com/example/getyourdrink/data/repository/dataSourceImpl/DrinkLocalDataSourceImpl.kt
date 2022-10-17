package com.example.getyourdrink.data.repository.dataSourceImpl

import com.example.getyourdrink.data.db.DrinkDao
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.data.repository.dataSource.DrinkLocalDataSource
import kotlinx.coroutines.flow.Flow

class DrinkLocalDataSourceImpl(private val drinkDao: DrinkDao): DrinkLocalDataSource {
    override suspend fun insert(drink: Drink) {
        drinkDao.insert(drink)
    }

    override suspend fun delete(drink: Drink) {
        drinkDao.delete(drink)
    }

    override fun getFavouritesDrinks(): Flow<List<Drink>> {
        return drinkDao.getFavouritesDrinks()
    }
}