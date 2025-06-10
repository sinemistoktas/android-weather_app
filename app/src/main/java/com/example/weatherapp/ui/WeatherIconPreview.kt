package com.example.weatherapp.ui

// A visual test screen to preview icons for all weather codes
// only used to preview the weather icons before using it in app
// todo: delete after development stage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.model.translateWeatherCodeToCondition
import com.example.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun WeatherConditionTestList() {
    // list of weather codes to test, got the codes from open-meteo API website
    val sampleCodes = listOf(
        0, 1, 2, 3,
        45, 48,
        51, 53, 55, 56, 57,
        61, 63, 65, 66, 67,
        71, 73, 75, 77,
        80, 81, 82,
        85, 86,
        95, 96, 99
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()) // scrollable column
    ) {
        sampleCodes.forEach { code ->
            val condition = translateWeatherCodeToCondition(code) // get label & icon for the code
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    imageVector = condition.icon,
                    contentDescription = condition.label,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "${code}: ${condition.label}")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWeatherConditionList() {
    WeatherAppTheme {
        WeatherConditionTestList()
    }
}