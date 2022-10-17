package com.example.getyourdrink.data.repository.dataSourceImpl

import com.example.getyourdrink.data.api.AlcoholService
import com.example.getyourdrink.data.model.ApiResponse
import com.example.getyourdrink.data.repository.dataSource.DrinkRemoteDataSource
import retrofit2.Response

class DrinkRemoteDataSourceImpl(private val alcoholService: AlcoholService): DrinkRemoteDataSource {

    override suspend fun getRandomDrink(): Response<ApiResponse> = alcoholService.getRandomDrink()
    override suspend fun getDrinkById(id: String): Response<ApiResponse> = alcoholService.getDrinkById(id)

    override suspend fun getDrinksByCategory(category: String): Response<ApiResponse> = alcoholService.getDrinksByCategory(category)

    override suspend fun getSearchedDrink(query: String): Response<ApiResponse> = alcoholService.getDrinksByName(query)
}