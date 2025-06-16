package com.example.weatherapp.data

import android.content.Context
import android.location.Geocoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class GeocodingRepository(private val context: Context) {

    suspend fun getCityName(lat: Double, lon: Double): String? = withContext(Dispatchers.IO) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, lon, 1)
            addresses?.firstOrNull()?.locality
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getCoordinates(cityName: String): Pair<Double, Double>? = withContext(Dispatchers.IO) {
        try {
            val geocoder = Geocoder(context, Locale.getDefault())
            val locations = geocoder.getFromLocationName(cityName, 1)
            locations?.firstOrNull()?.let {
                it.latitude to it.longitude
            }
        } catch (e: Exception) {
            null
        }
    }
}