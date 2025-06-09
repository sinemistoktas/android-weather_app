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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Brush
import com.example.weatherapp.ui.theme.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*


@Composable
@Preview(showBackground = true)
fun WeatherScreen() {
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
                        containerColor = cardBackground.copy(alpha = 0.8f),
                        contentColor = cardContentColor
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
                    ) {
                        //TODO: icon and text will be based on value we got from API, use WeatherCondition class
                        // Weather icon
                        Icon(
                            imageVector = Icons.Outlined.Cloud,
                            contentDescription = "Current Weather",
                            modifier = Modifier.size(96.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                        // Weather label
                        Text(
                            text = "Mostly Cloudy",
                            style = MaterialTheme.typography.titleLarge
                        )

                        // TODO: Temperature is going to come from the  REST API
                        // Current weather temperature
                        Text(
                            text = "25°",
                            style = MaterialTheme.typography.displaySmall
                        )
                        // high and low temperatures
                        // TODO: H, L Temperatures are going to come from the  REST API
                        Text(
                            text = "H:27°  L:18°",
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
                                Text( // TODO: get value from API
                                    text = "22%",
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
                                Text( // TODO: get value from API
                                    text = "14.4 km/h",
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
                                Text( // TODO: get value from API
                                    text = "18%",
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





