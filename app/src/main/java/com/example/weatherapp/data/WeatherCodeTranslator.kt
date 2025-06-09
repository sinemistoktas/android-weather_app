package com.example.weatherapp.data

/**
 * Translates numeric weather codes (like 0, 1, 2...) into labels (e.g., “Clear Sky”) and icons
 */


// weather codes table source: https://open-meteo.com/en/docs?current=weather_code#weather_variable_documentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

// data class to save weather type and icon related to it
data class WeatherCondition(
    val label: String,
    val icon: ImageVector
)

// gets weather code from API and translates it to its respective WeatherCondition data class
fun translateWeatherCodeToCondition(code: Int): WeatherCondition {
    return when (code) {
        // Clear sky
        0 -> WeatherCondition("Clear sky", Icons.Outlined.Brightness5)
        // Mainly clear, partly cloudy, and overcast
        1 -> WeatherCondition("Mainly clear", Icons.Outlined.WbSunny)
        2 -> WeatherCondition("Partly Cloudy", Icons.Outlined.Cloud)
        3 -> WeatherCondition("Overcast", Icons.Outlined.FilterDrama)
        // Fog and depositing rime fog
        in 45..48 -> WeatherCondition("Foggy", Icons.Outlined.Dehaze)
        // Drizzle: Light, moderate, and dense intensity
        51 -> WeatherCondition("Light Drizzle", Icons.Outlined.Grain)
        53 -> WeatherCondition("Moderate Drizzle", Icons.Outlined.Grain)
        55 -> WeatherCondition("Dense Drizzle", Icons.Outlined.Grain)
        // Freezing Drizzle: Light and dense intensity
        56 -> WeatherCondition("Light Freezing Drizzle", Icons.Outlined.Grain)
        57 -> WeatherCondition("Dense Freezing Drizzle", Icons.Outlined.Grain)
        // 	Rain: Slight, moderate and heavy intensity
        61 -> WeatherCondition("Slight Rain", Icons.Outlined.Umbrella)
        63 -> WeatherCondition("Moderate Rain", Icons.Outlined.Umbrella)
        65 -> WeatherCondition("Heavy Rain", Icons.Outlined.Umbrella)
        // Freezing Rain: Light and heavy intensity
        66 -> WeatherCondition("Light Freezing Rain", Icons.Outlined.Umbrella)
        67 -> WeatherCondition("Heavy Freezing Rain", Icons.Outlined.Umbrella)
        // Snow fall: Slight, moderate, and heavy intensity
        71 -> WeatherCondition("Slight Snow Fall", Icons.Outlined.AcUnit)
        73 -> WeatherCondition("Moderate Snow Fall", Icons.Outlined.AcUnit)
        75 -> WeatherCondition("Heavy Snow Fall", Icons.Outlined.AcUnit)
        // Snow grains
        77 -> WeatherCondition("Snow Grains", Icons.Outlined.AcUnit)
        // Rain showers: Slight, moderate, and violent
        80 -> WeatherCondition("Slight Rain Showers", Icons.Outlined.Opacity)
        81 -> WeatherCondition("Moderate Rain Showers", Icons.Outlined.Opacity)
        82 -> WeatherCondition("Violent Rain Showers", Icons.Outlined.Opacity)
        // Snow showers slight and heavy
        85 -> WeatherCondition("Slight Snow Showers", Icons.Outlined.AcUnit)
        86 -> WeatherCondition("Heavy Snow Showers", Icons.Outlined.SevereCold)
        // Thunderstorm: Slight or moderate
        95 -> WeatherCondition("Thunderstorm", Icons.Outlined.Bolt)
        // Thunderstorm with slight and heavy hail (this forecast is only available in Central Europe)
        96-> WeatherCondition("Thunderstorm with Slight Hail", Icons.Outlined.Thunderstorm)
        99 -> WeatherCondition("Thunderstorm with Heavy Hail", Icons.Outlined.Thunderstorm)
        // Else
        else -> WeatherCondition("Unknown", Icons.Outlined.QuestionMark)
    }
}