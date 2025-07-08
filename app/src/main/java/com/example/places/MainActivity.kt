package com.example.places

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.example.places._openstreetmap.MapView
import com.example.places.ui.theme.PlacesTheme
import org.osmdroid.config.Configuration

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
            // Note: This is deprecated and should be probably be updated later.
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        )

        enableEdgeToEdge()
        setContent {
            PlacesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    // The Floating Action Button, correctly positioned
                    // because of the Scaffold magic.
                    // Resource used for it:
                    // https://briangardner.tech/2020/05/08/compose-floating-action-button.html
                    floatingActionButton = {
                        FloatingActionButton(
                            // The onClick function should either create a new Activity that
                            // lets you create a new Marker, or a Modal that creates a new Marker
                            // alternatively it could be made like in the osm-android-compose
                            // library and how the Marker is handled in that library.
                            onClick = {},
                            shape = CircleShape
                        ) {
                            Icon(Icons.Filled.Add, "FAB.")
                        }
                    }
                ) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        MapView(clipToOutline = true)
                    }
                }
            }
        }
    }
}
