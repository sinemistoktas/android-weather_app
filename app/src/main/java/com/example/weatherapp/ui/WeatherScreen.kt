package com.example.weatherapp.ui

// sources:
// gradient background color: https://developer.android.com/develop/ui/compose/graphics/draw/brush

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


@Composable
fun WeatherScreen(state: WeatherUiState, viewModel: WeatherViewModel) {
    val primaryTextColor = MaterialTheme.colorScheme.onSurface
    val cardBackgroundColor = MaterialTheme.colorScheme.surfaceVariant
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
                .padding(48.dp),
            contentAlignment = Alignment.TopCenter
        )
        {
            // top rows: city and date
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // city dropdown menu
                CityDropdown(
                    currentCity = state.cityName ?: "Select City",
                    onCitySelected = { selectedCity ->
                        viewModel.getWeatherByCity(selectedCity)
                    },
                    primaryTextColor = primaryTextColor,
                    cardBackgroundColor = cardBackgroundColor,
                    cardContentColor = cardContentColor
                )

                // Date
                Text(
                    text = state.currentDate ?: "Loading date...",
                    style = MaterialTheme.typography.titleMedium,
                    color = primaryTextColor
                )

                // Weather card
                Card(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = cardBackgroundColor.copy(alpha = 0.8f),
                        contentColor = cardContentColor
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
                    ) {
                        // Show loading indicator when fetching data
                        if (state.isLoading == true) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Autorenew,
                                    contentDescription = "Loading data",
                                    modifier = Modifier.size(96.dp),
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "Fetching weather data...",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                        // Show message when no city is selected
                        else if (state.cityName == "Select City") {
                            Icon(
                                imageVector = Icons.Outlined.LocationOff,
                                contentDescription = "Location Permission Denied",
                                modifier = Modifier.size(96.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Please select a city from the dropdown to see the weather",
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        // error handling, when weather info fetch fails
                        else if (state.error == true) {
                            Icon(
                                imageVector = Icons.Outlined.SentimentDissatisfied, // sad error face
                                contentDescription = "Failed to get weather info",
                                modifier = Modifier.size(96.dp),
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Oopsie Woopsie!  Failed to fetch weather info.  Try again.",
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
                                        text = "${state.windSpeed ?: "?"} ${state.windUnit ?: ""}",
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

@Composable
fun CityDropdown(
    currentCity: String,
    onCitySelected: (String) -> Unit,
    primaryTextColor : Color,
    cardBackgroundColor : Color,
    cardContentColor : Color
) {
    var expanded by remember { mutableStateOf(false) }
    val cities = listOf("Current City", "Paris", "Berlin", "London", "Tokyo", "New York", "İstanbul", "Barcelona", "Amsterdam")

    Box(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        // Styled dropdown button
        Card(
            modifier = Modifier
                .border(
                    width = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(12.dp)
                ),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = cardBackgroundColor.copy(alpha = 0.8f),
                contentColor = cardContentColor
            ),
        ) {
            TextButton(
                onClick = { expanded = true },
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = currentCity,
                        style = MaterialTheme.typography.headlineMedium,
                        color = primaryTextColor
                    )
                    Icon(
                        imageVector = Icons.Outlined.ArrowDropDown,
                        contentDescription = "Dropdown arrow",
                        tint = primaryTextColor,
                        modifier = Modifier
                            .size(32.dp)
                            .rotate(if (expanded) 180f else 0f)
                    )
                }
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(IntrinsicSize.Max)  // Match the width of the trigger button
        ) {
            cities.forEach { city ->
                DropdownMenuItem(
                    text = {
                        Text(
                            city,
                            style = MaterialTheme.typography.titleLarge
                        ) },
                    onClick = {
                        expanded = false
                        onCitySelected(city)
                    }
                )
            }
        }
    }
}