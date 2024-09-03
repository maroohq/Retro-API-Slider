package com.example.apidemoopenweather.models.forecast

data class WeatherForecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<Hour3Forecast>,
    val message: Int
)