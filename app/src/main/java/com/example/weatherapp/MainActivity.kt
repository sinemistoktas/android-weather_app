package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.weatherapp.ui.WeatherScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.WeatherViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.WeatherApi


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = WeatherViewModel(WeatherRepository(WeatherApi.weatherApiService))
        viewModel.getWeather(41.015137, 28.979530) // Istanbul values for testing, todo: add location logic -> Bensu

        viewModel.uiState.observe(this) { state -> // observing weather state variables
            setContent {
                WeatherAppTheme { // wraps UI with theme
                    WeatherScreen(state) // loads main weather UI
                }
            }
        }
    }
}