package com.example.weatherapp.ui

/**
 Holds all weather-related state and logic
 **/

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.model.translateWeatherCodeToCondition
import com.example.weatherapp.model.WeatherCondition
import kotlinx.coroutines.launch

class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
 // Saves all necessary weather info variables

 // current temperature
 private val _currentTemp = MutableLiveData<Double?>()
 val currentTemp: LiveData<Double?> get() = _currentTemp

 // temperature unit
 private val _tempUnit = MutableLiveData<String?>()
 val tempUnit: LiveData<String?> get() = _tempUnit

 // rain probability %
 private val _rain = MutableLiveData<Double?>()
 val rain: LiveData<Double?> get() = _rain

 // wind speed
 private val _windSpeed = MutableLiveData<Double?>()
 val windSpeed: LiveData<Double?> get() = _windSpeed

 // wind speed unit
 private val _windUnit = MutableLiveData<String?>()
 val windUnit: LiveData<String?> get() = _windUnit

 // humidity %
 private val _humidity = MutableLiveData<Double?>()
 val humidity: LiveData<Double?> get() = _humidity

 // max temp today
 private val _tempHigh = MutableLiveData<Double?>()
 val tempHigh: LiveData<Double?> get() = _tempHigh

 // min temp today
 private val _tempLow = MutableLiveData<Double?>()
 val tempLow: LiveData<Double?> get() = _tempLow

 // translated weather condition (icon + label)
 private val _weatherCondition = MutableLiveData<WeatherCondition?>()
 val weatherCondition: LiveData<WeatherCondition?> get() = _weatherCondition // data class that stores weather type and its icon based on weather code

 // error message, if API fails
 private val _error = MutableLiveData<Boolean?>()
 val error: LiveData<Boolean?> get() = _error

 // to store all state variables in one class for ease of observability
 private val _uiState = MutableLiveData<WeatherUiState>()
 val uiState: LiveData<WeatherUiState> = _uiState


 fun getWeather(lat: Double, lon: Double) {
  // get all necessary variables from API
  viewModelScope.launch {
   try {
    // fetch weather info from API based on location
    val response = repository.getWeatherByLocation(lat, lon)

    // assign values to state vars
    _currentTemp.value = response.current_weather?.temperature
    _tempUnit.value = response.current_weather_units?.temperature
    _rain.value = response.hourly?.precipitation_probability?.firstOrNull()
    _windSpeed.value = response.current_weather?.windspeed
    _windUnit.value = response.current_weather_units?.windspeed
    _humidity.value = response.hourly?.relative_humidity_2m?.firstOrNull()
    _tempHigh.value = response.daily?.temperature_2m_max?.firstOrNull()
    _tempLow.value = response.daily?.temperature_2m_min?.firstOrNull()

    // convert weather code to icon & label
    _weatherCondition.value = response.current_weather?.weathercode?.let { // get weather code
     translateWeatherCodeToCondition(it) // translate weather code to weather type and its icon
    }
    _error.value = false // no error
    updateUiState() // updates ui state variables

   } catch (e: Exception) {
    // show error if API call failed
    Log.e("WeatherRepository", "Error fetching weather data", e)
    _error.value = true
    updateUiState()
   }
  }
 }


 private fun updateUiState() {
  _uiState.value = WeatherUiState(
   currentTemp = currentTemp.value,
   tempUnit = tempUnit.value,
   condition = weatherCondition.value,
   windSpeed = windSpeed.value,
   windUnit = windUnit.value,
   humidity = humidity.value,
   rain = rain.value,
   tempHigh = tempHigh.value,
   tempLow = tempLow.value,
   error = error.value
  )
 }

}