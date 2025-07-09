package com.example.places.openstreetmap

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.views.MapView

/**
 * A composable OpenStreetMap View.
 *
 * It is to be noted, that this map does not store the current position and zoom when using it.
 * Note to self: Should I still add it or is that a little bit of overkill for this Android Project?
 * @author Arnau Mora, Simon Jünger
 * @since 2021-12-30, 2025-07-07
 * @param modifier Modifiers to apply to the map.
 * @param onLoad This will get called once the map has been loaded.
 * @param clipToOutline This will let the View clip to its outline and not take the whole screen.
 */
@Composable
fun MapView(
    modifier: Modifier = Modifier,
    onLoad: ((map: MapView) -> Unit)? = null,
    clipToOutline: Boolean = false
) {
    // Note: Maybe we can high-jack the way Muhammad Utsman did the Markers in their library...
    //       if we want to do it in our own way
    val mapViewState = rememberMapViewWithLifecycle(clipToOutline)

    AndroidView(
        factory = { mapViewState },
        modifier = modifier
    ) { mapView -> onLoad?.invoke(mapView) }
}