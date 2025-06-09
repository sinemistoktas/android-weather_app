package com.example.weatherapp.location

import android.app.Service
import android.app.Notification
import android.content.Intent
import android.os.IBinder

class LocationService : Service(){
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Start location tracking here
        val notification: Notification = LocationNotificationHelper.createNotification(this)
        startForeground(1, notification)

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}