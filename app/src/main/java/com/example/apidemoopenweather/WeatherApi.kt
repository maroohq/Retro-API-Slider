package com.example.apidemoopenweather

import com.example.apidemoopenweather.models.forecast.WeatherForecast
import com.example.apidemoopenweather.models.today.weatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric" // For Celsius
    ): Response<weatherModel>

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric" // For Celsius
    ): Response<WeatherForecast>
}
