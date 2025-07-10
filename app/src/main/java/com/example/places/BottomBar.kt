package com.example.places

import android.content.Intent
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun BottomBar() {
    val context = LocalContext.current

    BottomAppBar(
        actions = {
            // Open the Main Activity
            IconButton(onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
            }) {
                // This should have been a Map as an Icon, but getting it into Android
                // is way harder than expected so it is just the Home Icon
                Icon(Icons.Outlined.Home, "Home")
            }
            // Note: When clicking this Button we should probably open a new
            //       Activity in which we can Manage all the Markers we have added.
            IconButton(onClick = {
                context.startActivity(Intent(context, ListActivity::class.java))
            }) {
                Icon(Icons.AutoMirrored.Filled.List, "List")
            }
        },
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
                onClick = {
                    Toast.makeText(context, "Todo: Implement \"Add Marker\" feature", Toast.LENGTH_LONG)
                        .show()
                },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(Icons.Filled.Add, "FAB.")
            }
        }
    )
}
