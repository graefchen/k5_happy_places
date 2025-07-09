package com.example.places

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.places.ui.theme.PlacesTheme

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                        // Here comes in all the MapMarkers
                    }
                }
            }
        }
    }
}