# WeatherApp – COMP 319A Project 3

This is a Jetpack Compose-based Weather app for Android developed as part of Koç University's COMP 319A - Mobile Device Programming course.  
It fetches current weather data using REST API, displays location-based weather information, and provides an intuitive user interface.

---

## 🔧 Features

- ✅ Fetch current weather data from Open-Meteo API using Retrofit
- ✅ Display current temperature, weather condition, and visual indicators
- ✅ Show detailed weather information (wind speed, humidity, precipitation probability)
- ✅ Weather condition icons with comprehensive weather code translations
- ✅ Daily high/low temperature display
- ✅ Modern gradient background with dark/light theme support
- ✅ Error handling with user-friendly messages
- ⏳ **[Coming Soon]** GPS location detection and reverse geocoding
- ⏳ **[Coming Soon]** City dropdown menu for selecting different locations
- ⏳ **[Coming Soon]** Dynamic current date display

---

## 📱 Usage Instructions

### Current Functionality

**Main Screen**: Displays comprehensive weather information
- **Current Location**: Automatically detected via GPS with reverse geocoding
- **City Selection**: Dropdown menu allows choosing from:
  - Current detected location (default)
  - Major world cities: Istanbul, Paris, Berlin, London, Tokyo, New York, etc.
  
- **Weather Display**: Updates automatically when different city is selected
  - Current temperature with weather condition icon
  - Weather condition label (Clear Sky, Partly Cloudy, Rainy, etc.)
  - Daily high and low temperatures
  - Detailed metrics: Rain probability, Wind speed, Humidity

- **Theme Support**:
    - Automatically adapts to system dark/light mode
    - Beautiful gradient backgrounds that change with theme

- **Error Handling**:
    - Displays friendly error messages when API calls fail
    - Shows alternative icons and messages for missing data

---

## 🗃️ Technologies Used

- **Kotlin & Jetpack Compose** - Modern Android UI toolkit
- **Retrofit & Moshi** - REST API client and JSON parsing
- **Open-Meteo Weather API** - Free weather data service (no API key required)
- **MVVM Architecture** - ViewModel + Repository pattern
- **Coroutines & LiveData** - Asynchronous programming and state management
- **Material3 UI Components** - Modern Material Design system

---

## 🚀 Setup & Run

1. Clone the repo from GitHub Classroom
2. Open in Android Studio (Giraffe or newer)
3. Make sure you're using:
    * Target API level 28+ (Android 9)
    * Kotlin `2.0.21+`
    * AGP `8.9.1+`
4. Build & run on emulator or physical device (Android 13+ recommended)
5. **No API key required** - uses free Open-Meteo service

---

## 👥 Authors

* **Bensu Özyurt** - Location service, GPS implementation, and UI design
* **Sinemis Toktaş** - API integration, weather data handling, and UI design

---

## 📄 License

This project is for educational purposes only.

---

## 📚 External Resources Used

This project was developed using a combination of course slides, official Android documentation, and external resources.

### 📘 Course Materials

* **COMP 319A** slides, especially:
  * App Architecture
  * Repository Pattern
  * Connect to the internet
  * Jetpack Compose UI components

### 🌐 Official Android Documentation

* **Gradient Backgrounds in Compose**
  * [https://developer.android.com/develop/ui/compose/graphics/draw/brush](https://developer.android.com/develop/ui/compose/graphics/draw/brush)
  * Used for implementing gradient background colors with theme support

### 🌤️ Weather API Resources

* **Open-Meteo Weather API**
  * [https://open-meteo.com/en/docs#daily_parameter_definition](https://open-meteo.com/en/docs#daily_parameter_definition)
  * Used for weather data fetching and API parameter configuration

### 📖 Implementation Guides

* **Medium Article: Forecasting Weather with Open-Meteo API**
  * [https://medium.com/@owmo13/forecasting-weather-with-open-meteo-api-using-jetpack-compose-7e58387f10e1](https://medium.com/@owmo13/forecasting-weather-with-open-meteo-api-using-jetpack-compose-7e58387f10e1)
  * Used for API parameter configuration and Retrofit implementation patterns

### 🎨 UI Resources

* **Google Fonts & Material Icons**
  * [https://fonts.google.com/?icon.size=24&icon.color=%231f1f1f&icon.platform=android&icon.set=Material+Icons&icon.style=Filled](https://fonts.google.com/?icon.size=24&icon.color=%231f1f1f&icon.platform=android&icon.set=Material+Icons&icon.style=Filled)
  * Used for searching and selecting appropriate weather condition icons

---

## 🧠 AI Assistance

This project used [ChatGPT](https://chat.openai.com) for development guidance and writing this README file. Below are the recorded prompts and the corresponding ChatGPT suggestions & code snippets that we incorporated into our Weather App.

### 1. Icon Implementation Best Practices

**Prompt:**
```
what is the best way to use and find icons
```

**ChatGPT Response:**
> **Use Icons.Filled.X (Material Icons in Code)**
> Best for: Speed, theming, built-in styling
>
> ```kotlin
> import androidx.compose.material.icons.Icons
> import androidx.compose.material.icons.filled.WbSunny
> 
> Icon(Icons.Filled.WbSunny, contentDescription = "Sunny")
> ```
>
> To access more icons:
> * Add this in build.gradle: `implementation "androidx.compose.material:material-icons-extended"`
>
> Where to find icons:
> * Browse at: https://fonts.google.com/icons
> * Search for things like: wb_sunny, wb_cloudy, ac_unit, air, water_drop

*Based on this guidance, we implemented comprehensive weather condition icons in `WeatherCondition.kt` using Material Icons, but after searching icons from the website, we changed our icon type to Outlined.*

---

### 2. Dark and Light Theme Support

**Prompt:**
```
How to support dark and light mode in a Jetpack Compose app?
```

**ChatGPT Response:**
> Great question. Jetpack Compose makes it really easy to support both dark and light themes automatically — and it’s a key part of Material Design, which your course emphasizes.
Here’s how to do it properly in a weather app using course-level architecture:
>
> **Define color palettes in Color.kt:**
> ```kotlin
> val LightColorScheme = lightColorScheme(
>     primary = Blue,
>     background = White,
>     onPrimary = Black
> )
> 
> val DarkColorScheme = darkColorScheme(
>     primary = LightBlue,
>     background = Black,
>     onPrimary = White
> )
> ```

*We changed the colors from given answer and implemented our custom color scheme with gradient backgrounds that adapt to light/dark themes in `Color.kt` and `Theme.kt`.*


### 3. Adding dropdown

**Prompt:**
```
how to add a dropdown in compose
```

**ChatGPT Response:**
> Simple Dropdown Using DropdownMenu in Compose:

>```kotlin
>@Composable
>fun SimpleDropdownMenu() {
>    var expanded by remember { mutableStateOf(false) }
>    val items = listOf("Option 1", "Option 2", "Option 3")
>    var selectedIndex by remember { mutableStateOf(0) }
>
>    Box {
>        Button(onClick = { expanded = true }) {
>            Text(items[selectedIndex])
>        }
>        DropdownMenu(
>            expanded = expanded,
>            onDismissRequest = { expanded = false }
>        ) {
>            items.forEachIndexed { index, item ->
>                DropdownMenuItem(
>                    text = { Text(item) },
>                    onClick = {
>                        selectedIndex = index
>                        expanded = false
>                    }
>                )
>            }
>        }
>    }
>}
> ```
*We used the given code as a general guide and implemented our own*

**Second Prompt:**
```
the dropdown that appears is not the same size as current card 
```
**ChatGPT Response:**
> Fixed dropdown width: Add modifier = Modifier.width(IntrinsicSize.Max) to the DropdownMenu - this makes it match the width of the trigger button