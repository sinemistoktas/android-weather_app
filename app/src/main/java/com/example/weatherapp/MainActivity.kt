package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.weatherapp.data.GeocodingRepository
import com.example.weatherapp.data.UserLocationScreen
import com.example.weatherapp.ui.WeatherScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.WeatherViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.WeatherApi


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = WeatherViewModel(WeatherRepository(WeatherApi.weatherApiService), GeocodingRepository(applicationContext))

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