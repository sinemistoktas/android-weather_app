package com.example.weatherapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen() {
    var city by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var weatherResult by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text= "Weather App"
                    )
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Enter city") }
            )

            Button(
                onClick = {
                    isLoading = true
                    errorMessage = null
                    weatherResult = null

                    // Simulate fake loading
                    // todo: Replace this later with ViewModel logic
                    isLoading = false
                    weatherResult = "22°C, Clear Sky"
                },
                enabled = city.isNotBlank() && !isLoading
            ) {
                Text("Get Weather")
            }

            if (isLoading) {
                CircularProgressIndicator()
            }

            weatherResult?.let {
                Text("Weather: $it")
            }

            errorMessage?.let {
                Text("Error: $it", color = MaterialTheme.colorScheme.error)
            }
        }
    }
}
