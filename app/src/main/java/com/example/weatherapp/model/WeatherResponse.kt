package com.example.weatherapp.model

/**
 * Data class that models the JSON response we get from the API
 * Used multiple nested classes to match the JSON response from API
 * Open-Meteo API Source: https://open-meteo.com/en/docs#api-form
 */

data class WeatherResponse(
    val current_weather: CurrentWeather?,
    val hourly: HourlyData?,
    val daily: DailyData?
)

data class CurrentWeather(
    val temperature: Double?,
    val windspeed: Double?,
    val weathercode: Int?,
    val time: String?
)

data class HourlyData(
    val precipitation_probability: List<Double>?,
    val relative_humidity_2m: List<Double>?
)

data class DailyData(
    val temperature_2m_max: List<Double>?,
    val temperature_2m_min: List<Double>?
)

