package com.example.weatherapp.model

/**
 * Data class that models the JSON response we get from the API
 * Used multiple nested classes to match the JSON response from API
 * Open-Meteo API Source: https://open-meteo.com/en/docs#api-form
 */

// Top-level response from the weather API
data class WeatherResponse(
    val current_weather: CurrentWeather?, // current temperature, wind, code
    val current_weather_units: CurrentWeatherUnits?, // weather units
    val hourly: HourlyData?, // hourly values like humidity, rain %
    val daily: DailyData?  // daily high/low temps
)

// cuurent weather -> what's happening right now
data class CurrentWeather(
    val temperature: Double?, // current temperature
    val windspeed: Double?, // current wind speed
    val weathercode: Int?, // code for current weather type
    val time: String? // timestamp
)

data class CurrentWeatherUnits(
    val temperature: String,  // tempUnit
    val windspeed: String     // windUnit
)

// Hourly breakdown in list form
// We use the first value from the hourly list to show the current weather condition
data class HourlyData(
    val precipitation_probability: List<Double>?, // chance of rain per hour
    val relative_humidity_2m: List<Double>? // humidity %
)

// Daily breakdown
data class DailyData(
    val temperature_2m_max: List<Double>?, // high temp for the day
    val temperature_2m_min: List<Double>? // low temp for the day
)

