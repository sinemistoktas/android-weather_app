package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.data.GeocodingRepository
import com.example.weatherapp.data.UserLocationScreen
import com.example.weatherapp.ui.WeatherScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.WeatherViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.WeatherApi


class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels {
        WeatherViewModelFactory(
            WeatherRepository(WeatherApi.weatherApiService),
            GeocodingRepository(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel.uiState.observe(this) { state ->
            setContent {
                WeatherAppTheme {
                    WeatherScreen(state, viewModel)
                    if (state.locationPermissionGranted == false && state.currentLocation == null) {
                        UserLocationScreen(
                            onLocationReceived = { location ->
                                viewModel.getLocation(location)
                            },
                            onPermissionDenied = {
                                viewModel.setLocationPermissionDenied()
                            }
                        )
                    }
                }
            }
        }
    }
}

class WeatherViewModelFactory(
    private val weatherRepository: WeatherRepository,
    private val geocodingRepository: GeocodingRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(weatherRepository, geocodingRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}