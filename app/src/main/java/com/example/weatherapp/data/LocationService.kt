@file:Suppress("DEPRECATION")

package com.example.weatherapp.data

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


/**
 * Retrieves the device's last known location to supply latitude and longitude coordinates for weather data requests.
 **/


data class UserLocation(
    val latitude: Double,
    val longitude: Double
)

@Composable
fun ErrorDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "There is a security issue. Please be careful!!!",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun LocationPermission(
    onRequestPermissions: () -> Unit,
    onDismiss: () -> Unit
) {
    PermissionDialog(
        dialogTitle = "Location Permission",
        dialogText = "We need your location to show you accurate weather information based on your current location. \n\n You can also select a city to view its weather from the dropdown menu.",
        icon = Icons.Default.LocationOn,
        onDismissRequest = {
            onDismiss()
        },
        onConfirmation = onRequestPermissions
    )
}

@Composable
fun PermissionDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Location Permission Dialog")
        },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}



@Composable
fun UserLocationScreen(onLocationReceived: (UserLocation) -> Unit, onPermissionDenied: () -> Unit) {
    val context = LocalContext.current
    val locationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val scope = rememberCoroutineScope()

    var showPermissionDialog by remember { mutableStateOf(true) }
    var showSecurityErrorDialog by remember { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val fineLocationGranted = permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
        val coarseLocationGranted = permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true

        if (fineLocationGranted || coarseLocationGranted) {
            scope.launch {
                try {
                    val priority = if (fineLocationGranted) {
                        Priority.PRIORITY_HIGH_ACCURACY
                    } else {
                        Priority.PRIORITY_BALANCED_POWER_ACCURACY
                    }

                    val location = locationClient
                        .getCurrentLocation(priority, CancellationTokenSource().token)
                        .await()

                    location?.let {
                        Log.d("LocationService", "Location obtained - Lat: ${it.latitude}, Lon: ${it.longitude}")
                        onLocationReceived(UserLocation(it.latitude, it.longitude))
                    }
                } catch (e: SecurityException) {
                    Log.e("LocationService", "Security Exception error when getting location", e)
                    showSecurityErrorDialog = true
                }
            }
        } else {
            onPermissionDenied()
        }
    }

    if (showPermissionDialog) {
        LocationPermission(
            onRequestPermissions = {
                showPermissionDialog = false
                permissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            },
            onDismiss = {
                showPermissionDialog = false
                onPermissionDenied()
            }
        )
    }

    if (showSecurityErrorDialog) {
        ErrorDialog {
            showSecurityErrorDialog = false
        }
    }
}
