package com.example.weatherapp.ui

import com.example.weatherapp.data.UserLocation
import com.example.weatherapp.model.WeatherCondition

// data class to store weather state variables for ease of observability
data class WeatherUiState (
    val currentTemp: Double? = null,
    val tempUnit: String? = null,
    val condition: WeatherCondition? = null,
    val windSpeed: Double? = null,
    val windUnit: String? = null,
    val humidity: Double? = null,
    val rain: Double? = null,
    val tempHigh: Double? = null,
    val tempLow: Double? = null,
    val error: Boolean? = false,
    val currentDate: String? = null,
    val currentLocation: UserLocation? = null,
    val currentCity: String? = null,
    val timezone: String? = null
)