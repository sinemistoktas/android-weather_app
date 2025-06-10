package com.example.weatherapp.ui

// sources:
// gradient background color: https://developer.android.com/develop/ui/compose/graphics/draw/brush

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import com.example.weatherapp.ui.theme.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.text.style.TextAlign


@Composable
fun WeatherScreen(state: WeatherUiState) {

    val primaryTextColor = MaterialTheme.colorScheme.onSurface
    val cardBackground = MaterialTheme.colorScheme.surfaceVariant
    val cardContentColor = MaterialTheme.colorScheme.onSurfaceVariant

    // changes background based on theme
    val isDark = isSystemInDarkTheme()
    val gradientBrush = Brush.verticalGradient(
        colors = if (isDark) {
            listOf(DarkGradientTop, DarkGradientBottom)
        } else {
            listOf(LightGradientTop, LightGradientBottom)
        }
    )

    Scaffold { innerPadding ->
        Box( // main background container
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBrush) // gradient background color
                .padding(innerPadding) // respects system bars & camera
                .padding(36.dp),
            contentAlignment = Alignment.TopCenter
            )
        {
            // top rows: city and date
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
                Text(
                    text = state.currentDate ?: "?",
                    style = MaterialTheme.typography.titleMedium,
                    color = primaryTextColor
                )

                // Weather card
                Card(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = cardBackground.copy(alpha = 0.8f),
                        contentColor = cardContentColor
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
                    ) {
                        // error handling, when weather info fetch fails
                        if (state.error == true) {
                            Icon(
                                imageVector = Icons.Outlined.SentimentDissatisfied, // sad error face
                                contentDescription = "Failed to get weather info",
                                modifier = Modifier.size(96.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )

                            Text(
                                text = "Oopsie Woopsie! Failed to fetch weather info. Please try again.",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium
                            )

                        } else {

                            // Weather icon
                            if (state.condition != null) {
                                Icon(
                                    imageVector = state.condition.icon,
                                    contentDescription = "Current Weather",
                                    modifier = Modifier.size(96.dp),
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                                // Weather label
                                Text(
                                    text = state.condition.label,
                                    style = MaterialTheme.typography.titleLarge
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Outlined.Casino, // luck icon instead of failed weather icon
                                    contentDescription = "failed to fetch weather type",
                                    modifier = Modifier.size(96.dp),
                                    tint = MaterialTheme.colorScheme.onSurface
                                )

                                Text(
                                    text = "Couldn't get the weather type... Good luck out there!", // instead of failed weather label
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }

                            // Current weather temperature
                            Text(
                                text = "${state.currentTemp ?: "?"}${state.tempUnit ?: "°"}",
                                style = MaterialTheme.typography.displaySmall
                            )
                            // high and low temperatures
                            Text(
                                text = "H: ${state.tempHigh ?: "?"}° L:${state.tempLow ?: "?"}°",
                                style = MaterialTheme.typography.bodyLarge
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // bottom section for details
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                // rain
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Umbrella,
                                        contentDescription = "Rain",
                                        modifier = Modifier.size(32.dp),
                                        tint = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "${state.rain ?: "?"}%",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = "Rain"
                                    )
                                }

                                // wind speed
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Air,
                                        contentDescription = "Wind Speed",
                                        modifier = Modifier.size(32.dp),
                                        tint = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "${state.windSpeed ?: "?"} ${state.windUnit ?: "?"}",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = "Wind Speed"
                                    )
                                }

                                // humidity
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.WaterDrop,
                                        contentDescription = "Humidity",
                                        modifier = Modifier.size(32.dp),
                                        tint = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "${state.humidity ?: "?"}%",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                    Text(
                                        text = "Humidity"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}





