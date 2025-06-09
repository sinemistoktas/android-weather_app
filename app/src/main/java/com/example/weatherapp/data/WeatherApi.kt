package com.example.weatherapp.data

/**
 * Provides a Retrofit-based WeatherApiService instance using Moshi for JSON parsing.
 * follows structure from course slide 11
 *
 * Base URL and other constants are defined in Constants.kt
 */


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(Constants.WEATHER_API_BASE_URL)
    .build()

object WeatherApi {
    val weatherApiService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}
