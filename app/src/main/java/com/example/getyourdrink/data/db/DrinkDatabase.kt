package com.example.getyourdrink.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.getyourdrink.data.model.Drink

@Database( entities = [Drink::class],
    version = 1,
    exportSchema = false
)
abstract class DrinkDatabase: RoomDatabase() {

    abstract fun drinkDao(): DrinkDao
}