package com.example.weatherapp.ui

/**
 Holds all weather-related state and logic
 **/

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.model.translateWeatherCodeToCondition
import com.example.weatherapp.model.WeatherCondition
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
 // save all necessary variables

 private val _currentTemp = MutableLiveData<Double?>()
 val currentTemp: LiveData<Double?> get() = _currentTemp

 private val _rain = MutableLiveData<Double?>()
 val rain: LiveData<Double?> get() = _rain

 private val _windSpeed = MutableLiveData<Double?>()
 val windSpeed: LiveData<Double?> get() = _windSpeed

 private val _humidity = MutableLiveData<Double?>()
 val humidity: LiveData<Double?> get() = _humidity

 private val _tempHigh = MutableLiveData<Double?>()
 val tempHigh: LiveData<Double?> get() = _tempHigh

 private val _tempLow = MutableLiveData<Double?>()
 val tempLow: LiveData<Double?> get() = _tempLow

 private val _weatherCondition = MutableLiveData<WeatherCondition?>()
 val weatherCondition: LiveData<WeatherCondition?> get() = _weatherCondition // data class that stores weather type and its icon based on weather code

 private val _error = MutableLiveData<String?>()
 val error: LiveData<String?> get() = _error

 fun getWeather(lat: Double, lon: Double) {
  // get all necessary variables from API
  viewModelScope.launch {
   try {
    // get weather info from API based on location
    val response = repository.getWeatherByLocation(lat, lon)
    _currentTemp.value = response.current_weather?.temperature
    _rain.value = response.hourly?.precipitation_probability?.firstOrNull()
    _windSpeed.value = response.current_weather?.windspeed
    _humidity.value = response.hourly?.relative_humidity_2m?.firstOrNull()
    _tempHigh.value = response.daily?.temperature_2m_max?.firstOrNull()
    _tempLow.value = response.daily?.temperature_2m_min?.firstOrNull()
    _weatherCondition.value = response.current_weather?.weathercode?.let { // get weather code
     translateWeatherCodeToCondition(it) // translate weather code to weather type and its icon
    }
   } catch (e: Exception) {
    // Handles API error
    _error.value = "Failed to fetch weather. Please try again."
   }
  }
 }
}