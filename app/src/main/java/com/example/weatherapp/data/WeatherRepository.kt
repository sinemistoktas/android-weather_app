package com.example.weatherapp.data

/**
 * middle layer that fetches data from the API service
 * to keep ViewModel clean and separates business logic from networking
 * uses repository pattern
 */

import com.example.weatherapp.model.WeatherResponse

class WeatherRepository(private val api: WeatherApiService) {
    suspend fun getWeatherByLocation(lat: Double, lon: Double): WeatherResponse {
        return api.getWeatherByLocation(lat, lon)
    }
}
