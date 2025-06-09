package com.example.weatherapp.location


import com.google.android.gms.location.FusedLocationProviderClient
import android.app.Notification
import android.app.Service
import android.app.Service.START_STICKY
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.ServiceCompat.startForeground

class LocationManager {
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    override fun onCreate() {
        super.onCreate()
        // Initialize location updates, if needed
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification: Notification = LocationNotificationHelper.createNotification(this)
        startForeground(1, notification)

        // TODO: Start location updates here

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}