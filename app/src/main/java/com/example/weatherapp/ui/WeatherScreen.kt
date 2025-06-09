package com.example.weatherapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.icons.filled.*

@Composable
@Preview(showBackground = true)
fun WeatherScreen() {
    val primaryTextColor = MaterialTheme.colorScheme.onSurface
    val cardBackground = MaterialTheme.colorScheme.surfaceVariant
    val cardContentColor = MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Name of the city
            // TODO: This is going to be dropdown in the future
            // TODO: Location is going to come from the location provider
            Text(
                text = "Istanbul",
                style = MaterialTheme.typography.headlineLarge,
                color = primaryTextColor
            )

            // Date
            // TODO: Need to Take current date from the device
            Text(
                text = "Mon June 9, 2025",
                style = MaterialTheme.typography.titleMedium,
                color = primaryTextColor
            )

            // Weather card
            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = cardBackground,
                    contentColor = cardContentColor
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    //TODO: Image (Icon) Should be added in here

                    Text(
                        text = "Mostly Cloudy",
                        style = MaterialTheme.typography.titleLarge
                    )

                    // TODO: Temperature is going to come from the  REST API
                    Text(
                        text = "25°",
                        style = MaterialTheme.typography.displaySmall
                    )
                    Text(
                        text = "H:27°  L:18°",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        // TODO: Icon needs to be added!
                        Text(
                            text = "Rain"
                            )

                        // TODO: Icon needs to be added!
                        Text(
                            text = "Wind Speed"
                        )

                        // TODO: Icon needs to be added!
                        Text(
                            text = "Humidity"
                        )
                    }
                }
            }
        }
    }
}





