package com.example.getyourdrink.data.db

import androidx.room.*
import com.example.getyourdrink.data.model.Drink
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(drink: Drink)

    @Delete
    suspend fun delete(drink: Drink)

    @Query("SELECT * FROM drinks")
    fun getFavouritesDrinks(): Flow<List<Drink>>
}