package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.weatherapp.data.UserLocationScreen
import com.example.weatherapp.ui.WeatherScreen
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.ui.WeatherViewModel
import com.example.weatherapp.data.WeatherRepository
import com.example.weatherapp.data.WeatherApi
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.weatherapp.data.UserLocation


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel = WeatherViewModel(WeatherRepository(WeatherApi.weatherApiService))
         // Istanbul values for testing, todo: add location logic -> Bensu
        setContent {
            WeatherAppTheme {
                var userLocation by remember { mutableStateOf<UserLocation?>(null) }


                val state by viewModel.uiState.observeAsState() // ✅ this keeps Compose reactive

                LaunchedEffect(userLocation) {
                    userLocation?.let {
                        //viewModel.getWeather(41.015137, 28.979530)

                        viewModel.getWeather(it.latitude, it.longitude)
                    }
                }

                if (userLocation == null) {
                    println("User location is null")
                    UserLocationScreen { location ->
                        userLocation = location
                    }
                } else {
                    state?.let {
                        WeatherScreen(it, userLocation= userLocation!!)
                    } ?: Box(
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }

                }
            }
        }

        }
    }