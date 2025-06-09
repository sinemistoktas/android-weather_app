package com.example.weatherapp.location

import android.os.Bundle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationManager {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        // ...

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }
}