package com.example.getyourdrink.data.api

import androidx.viewbinding.BuildConfig
import com.example.getyourdrink.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AlcoholService {

    @GET("random.php")
    suspend fun getRandomDrink(): Response<ApiResponse>

    @GET("lookup.php")
    suspend fun getDrinkById(
        @Query("i")id: String
    ): Response<ApiResponse>

    @GET("filter.php")
    suspend fun getDrinksByCategory(
        @Query("c")category: String
    ): Response<ApiResponse>

    @GET("filter.php")
    suspend fun getDrinkByIngredient(
        @Query("i")searchQuery: String
    ): Response<ApiResponse>

    @GET("search.php")
    suspend fun getDrinksByName(
        @Query("s")searchQuery: String
    ): Response<ApiResponse>
}