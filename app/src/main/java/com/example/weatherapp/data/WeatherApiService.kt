package com.example.weatherapp.data

/**
 * Declares the Retrofit interface to talk to the weather API
 * Open-Meteo Weather API source: https://open-meteo.com/en/docs#daily_parameter_definition
 *
 * Logic for @GET and @Query usage inspired by this article:
 * Source: https://medium.com/@owmo13/forecasting-weather-with-open-meteo-api-using-jetpack-compose-7e58387f10e1
 */


import com.example.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET(Constants.WEATHER_API_ENDPOINT)
    suspend fun getWeatherByLocation(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true,
        @Query("hourly") hourly: String = "precipitation_probability,relative_humidity_2m",
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse

}
