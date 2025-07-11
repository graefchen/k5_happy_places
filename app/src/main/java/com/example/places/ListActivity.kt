package com.example.places

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.places.ui.theme.PlacesTheme

class ListActivity : ComponentActivity() {
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        context = applicationContext

        enableEdgeToEdge()
        setContent {
            PlacesTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    // Resource used for the bar:
                    // https://developer.android.com/develop/ui/compose/components/app-bars
                    bottomBar = { BottomBar() },
                ) { innerPadding ->
                    MarkerList(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxWidth()
                            .verticalScroll(
                                rememberScrollState()
                            )
                    )
                }
            }
        }
    }

    @Composable
    fun MarkerList(modifier: Modifier) {
        var dao = AppDatabase.getDatabase(context).mapMarkerDao()
        var markers by remember { mutableStateOf(dao.getAll()) }

        Column(
            modifier = modifier
        ) {
            markers.forEach { marker ->
                MarkerRow(marker, onDelete = { markers = dao.getAll() })
            }
        }
    }

    @Composable
    fun MarkerRow(marker: MapMarker, onDelete: () -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = marker.title)
                // Text(text = marker.description)
            }
            Row {
                FilledIconButton(
                    onClick = {
                        Toast.makeText(context, "Todo: Implement Edit feature", Toast.LENGTH_LONG)
                            .show()
                    },
                    enabled = false
                ) {
                    Icon(Icons.Outlined.Edit, "Edit")
                }
                FilledIconButton(
                    onClick = {
                        AppDatabase.getDatabase(context).mapMarkerDao().deleteById(marker.id)
                        onDelete()
                    },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Icon(Icons.Outlined.Delete, "Delete")
                }
            }
        }
    }
}

