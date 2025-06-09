package com.example.weatherapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
@Composable
@Preview
fun WeatherScreen2() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Name of the city
            // TODO: This is going to be dropdown in the future
            Text(
                text = "Istanbul",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 35.sp
            )
            // TODO: Need to Take current date from the device
            Text(
                text = "Mon June 9, 2025",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxHeight(0.4f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    //TODO: Image (Icon) Should be added in here

                    Text(
                        text = "Mostly Cloudy",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.White,
                        fontSize = 20.sp
                    )
                    // TODO: Temperature is going to come from the  REST API
                    Text(
                        text = "25°",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(vertical = 4.dp),
                        color = Color.White
                    )
                    // TODO: Location is going to come from the location provider
                    Text(
                        text = "H:27°  L:18°",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.White
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                        
                    ) {
                        // TODO: Icon needs to be added!
                        Text(
                            text = "Rain",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = Color.White
                        )
                        Text(
                            text = "Wind Speed",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = Color.White
                        )
                        Text(
                            text = "Humidity",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(bottom = 8.dp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}



