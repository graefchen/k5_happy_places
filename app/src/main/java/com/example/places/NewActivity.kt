package com.example.places

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import com.example.places.ui.theme.PlacesTheme


class NewActivity : ComponentActivity() {
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
                    NewMarker(modifier = Modifier.padding(innerPadding), onFinished = {
                        finish()
                    })
                }
            }
        }
    }
}

@Composable
fun NewMarker(modifier: Modifier, onFinished: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var longitude by remember { mutableDoubleStateOf(0.0) }
    var latitude by remember { mutableDoubleStateOf(0.0) }

    val dao = AppDatabase.getDatabase(LocalContext.current).mapMarkerDao()

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Titel of Marker") }
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description of Marker") }
        )
        OutlinedTextField(
            value = latitude.toString(),
            onValueChange = { latitude = it.toDouble() },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword
            ),
            label = { Text("Latitude Position of new Marker") }
        )
        OutlinedTextField(
            value = longitude.toString(),
            onValueChange = { longitude = it.toDouble() },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword
            ),
            label = { Text("Longitude Position of new Marker") }
        )
        Button(onClick = {
            dao.insert(
                MapMarker(
                    title = title,
                    description = description,
                    long = longitude,
                    lat = latitude
                )
            )
            onFinished()
        }) {
            Text("Add")
        }
    }
}
