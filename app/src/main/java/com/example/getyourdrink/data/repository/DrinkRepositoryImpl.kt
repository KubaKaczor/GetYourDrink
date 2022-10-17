package com.example.getyourdrink.data.repository

import android.util.Log
import com.example.getyourdrink.data.model.Drink
import com.example.getyourdrink.data.repository.dataSource.DrinkLocalDataSource
import com.example.getyourdrink.data.repository.dataSource.DrinkRemoteDataSource
import com.example.getyourdrink.domain.repository.DrinkRepository
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

class DrinkRepositoryImpl(
    private val drinkRemoteDataSource: DrinkRemoteDataSource,
    private val drinkLocalDataSource: DrinkLocalDataSource
): DrinkRepository {

    override suspend fun getRandomDrink(): Drink? {
        lateinit var drinksList: List<Drink>
        var drink: Drink? = null
        try {
            val response = drinkRemoteDataSource.getRandomDrink()
            val body = response.body()
            if(body!=null){
                drinksList = body.drinks
                drink = drinksList[0]
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return drink
    }

    override suspend fun getDrinkById(id: String): Drink? {
        lateinit var drinksList: List<Drink>
        var drink: Drink? = null
        try {
            val response = drinkRemoteDataSource.getDrinkById(id)
            val body = response.body()
            if(body!=null){
                drinksList = body.drinks
                drink = drinksList[0]
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return drink
    }

    override suspend fun getDrinksByCategory(category: String): List<Drink> {
        lateinit var drinksList: List<Drink>
        try {
            val response = drinkRemoteDataSource.getDrinksByCategory(category)
            val body = response.body()
            if(body!=null){
                drinksList = body.drinks
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return drinksList
    }

    override suspend fun getSearchedDrinks(query: String): List<Drink> {
        var drinksList = listOf<Drink>()
        try {
            //drinksList = listOf<Drink>()
            val response = drinkRemoteDataSource.getSearchedDrink(query)
            val body = response.body()
            if(body!=null){
                drinksList = body.drinks
            }
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return drinksList
    }

    override suspend fun insert(drink: Drink) {
        drinkLocalDataSource.insert(drink)
    }

    override suspend fun delete(drink: Drink) {
        drinkLocalDataSource.delete(drink)
    }

    override fun getFavouritesDrinks(): Flow<List<Drink>> {
        return drinkLocalDataSource.getFavouritesDrinks()
    }
}