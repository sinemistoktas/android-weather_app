package com.example.weatherapp.data

/**
 * Constant values for API configuration and query parameters
 *
 * Sources:
 * Open-Meteo Weather API parameters gotten from: https://open-meteo.com/en/docs#daily_parameter_definition
 * Logic for implementation inspired by this article: https://medium.com/@owmo13/forecasting-weather-with-open-meteo-api-using-jetpack-compose-7e58387f10e1
*/

object Constants {
    const val WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/"
    const val WEATHER_API_ENDPOINT = "forecast"
    const val TIME_ZONE = "auto"
    const val HOURLY_FIELDS = "precipitation_probability,relative_humidity_2m"
    const val DAILY_FIELDS = "temperature_2m_max,temperature_2m_min"
}
