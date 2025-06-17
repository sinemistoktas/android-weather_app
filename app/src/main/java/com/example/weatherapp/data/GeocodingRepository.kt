package com.example.weatherapp.data

import android.content.Context
import android.location.Geocoder
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Locale

class GeocodingRepository(private val context: Context) {

    companion object {
        private const val TAG = "GeocodingRepository"
    }

    suspend fun getCityName(lat: Double, lon: Double): String? = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "getCityName called with lat: $lat, lon: $lon")

            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses = geocoder.getFromLocation(lat, lon, 1)
            val address = addresses?.firstOrNull()

            if (address != null) {
                Log.d(TAG, "=== ADDRESS DETAILS ===")
                Log.d(TAG, "locality: '${address.locality}'")
                Log.d(TAG, "adminArea: '${address.adminArea}'")
            

                // Try multiple fields to find the city name
                val cityName = address.adminArea?.takeIf { it.isNotEmpty() }
                    ?: address.locality?.takeIf { it.isNotEmpty() }
                    

                Log.d(TAG, "Selected city name: '$cityName'")
                return@withContext cityName
            } else {
                Log.w(TAG, "No address found for coordinates")
                return@withContext null
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error in getCityName: ${e.message}", e)
            null
        }
    }

    suspend fun getCoordinates(cityName: String): Pair<Double, Double>? = withContext(Dispatchers.IO) {
        try {
            Log.d(TAG, "getCoordinates called with cityName: $cityName")

            val geocoder = Geocoder(context, Locale.getDefault())
            val locations = geocoder.getFromLocationName(cityName, 1)
            val result = locations?.firstOrNull()?.let {
                Log.d(TAG, "Found coordinates: lat=${it.latitude}, lon=${it.longitude}")
                it.latitude to it.longitude
            }

            if (result == null) {
                Log.w(TAG, "No coordinates found for city: $cityName")
            }

            return@withContext result
        } catch (e: Exception) {
            Log.e(TAG, "Error in getCoordinates: ${e.message}", e)
            null
        }
    }
}