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
                    if (state.currentLocation == null) {
                        UserLocationScreen { location ->
                            viewModel.getLocation(location)
                        }
                    } else {
                        WeatherScreen(state, viewModel)
                    }
                }
            }
        }
    }
}