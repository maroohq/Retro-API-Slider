package com.example.apidemoopenweather.models.today

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)