package com.example.places

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.places.openstreetmap.MapView
import com.example.places.ui.theme.PlacesTheme
import org.osmdroid.config.Configuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Note: Because we need some permissions from the user... we should "ask" the user
        //       to provide those to us. They shall be added under here.
        //       Alternative I just ... do not use it and let the user activate it
        //       by themself, as I am/was not able to implement it.
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions()
        }

        // Getting the World Map (else it would be a boring tile map)
        // THIS MUST BE HERE ELSE THE TILEMAP IS NOT LOADED
        Configuration.getInstance().load(
            applicationContext,
            // Note: This is deprecated and should be probably be updated later.
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        )

        enableEdgeToEdge()
        setContent {
            PlacesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    // Resource used for the bar:
                    // https://developer.android.com/develop/ui/compose/components/app-bars
                    bottomBar = { BottomBar() },
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        MapView(clipToOutline = true)
                    }
                }
            }
        }
    }

    fun requestPermissions() {
        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                // Precise location access granted.
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {}
                // Only approximate location access granted.
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {}
                // No location access granted.
                else -> {}
            }
        }
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }
}