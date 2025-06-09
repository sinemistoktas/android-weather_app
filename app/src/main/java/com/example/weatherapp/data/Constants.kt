package com.example.weatherapp.data

/**
 * Constant values for API configuration and query parameters
 *
 * Sources:
 * Open-Meteo Weather API parameters gotten from: https://open-meteo.com/en/docs#daily_parameter_definition
 * Logic for implementation inspired by this article: https://medium.com/@owmo13/forecasting-weather-with-open-meteo-api-using-jetpack-compose-7e58387f10e1
*/

object Constants {
    // base URL for all weather requests
    const val WEATHER_API_BASE_URL = "https://api.open-meteo.com/v1/"
    // the endpoint we hit on that API (added after base URL)
    const val WEATHER_API_ENDPOINT = "forecast"
    // tells API to match user's time zone automatically
    const val TIME_ZONE = "auto"
    // hourly weather details we want (rain chance + humidity)
    const val HOURLY_FIELDS = "precipitation_probability,relative_humidity_2m"
    // daily forecast values we care about (high + low temp)
    const val DAILY_FIELDS = "temperature_2m_max,temperature_2m_min"
}
