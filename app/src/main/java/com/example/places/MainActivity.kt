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
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.places.openstreetmap.MapView
import com.example.places.ui.theme.PlacesTheme
import org.osmdroid.config.Configuration

var mapMarkerLists: List<MapMarker> = listOf<MapMarker>()

class MainActivity : ComponentActivity() {
    lateinit var mapMarkerDao: MapMarkerDao

    // TODO: Add TopBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(applicationContext)
        mapMarkerDao = db.mapMarkerDao()
        // Note: This simply adds a marker to the Map
        // Can be deleted Later.
        mapMarkerDao.insertAll(
            MapMarker(
                title = "Köln",
                description = "Die Deutsche Stadt, die für ihr Parfüm bekannt ist.",
                lat = 50.936389,
                long = 6.952778
            ),
            MapMarker(
                title = "Universität zu Köln",
                description = "Die Universität an der wir grade eingeschrieben sind.",
                lat = 50.928309,
                long = 6.929363
            ),
            MapMarker(
                title = "Nullpunkt",
                description = "Das ist null, erwartest du mehr?",
                lat = 0.0,
                long = 0.0
            )
        )
        mapMarkerLists = mapMarkerDao.getAll()

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

    override fun onDestroy() {
        super.onDestroy()

        // Deleting every thing what is in the DB .. at least for now while we are testing
        for (marker in mapMarkerDao.getAll()) {
            mapMarkerDao.delete(marker)
            mapMarkerDao.deleteById(marker.id)
        }
    }
}