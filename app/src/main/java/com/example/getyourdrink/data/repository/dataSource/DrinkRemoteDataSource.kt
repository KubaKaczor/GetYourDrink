package com.example.getyourdrink.data.repository.dataSource

import com.example.getyourdrink.data.model.ApiResponse
import retrofit2.Response

interface DrinkRemoteDataSource {
    suspend fun getRandomDrink(): Response<ApiResponse>
    suspend fun getDrinkById(id: String): Response<ApiResponse>
    suspend fun getDrinksByCategory(category: String): Response<ApiResponse>
    suspend fun getSearchedDrink(query: String): Response<ApiResponse>
}