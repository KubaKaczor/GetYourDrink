package com.example.getyourdrink.data.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("drinks")
    val drinks: List<Drink>
)