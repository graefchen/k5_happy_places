package com.example.places

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.places._openstreetmap.MapView
import com.example.places.ui.theme.PlacesTheme
import org.osmdroid.config.Configuration
import org.osmdroid.views.overlay.Marker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Note: Because we need some permissions from the user... we should "ask" the user
        //       to provide those to us. They shall be added under here.
        //       Alternative I just ... do not use it and let the user activate it
        //       by themself, as I am/was not able to implement it.

        // Getting the World Map (else it would be a boring tile map)
        // THIS MUST BE HERE ELSE THE TILEMAP IS NOT LOADED
        Configuration.getInstance().load(
            applicationContext,
            // Note: This deprecated and should be probably be updated later.
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        )

        enableEdgeToEdge()
        setContent {
            PlacesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text(text = "Hello, World!")
                        MapView(clipToOutline = true)
                    }
                }
            }
        }
    }
}
