package com.example.places.map

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.places.R
import org.osmdroid.util.BoundingBox
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@Composable
fun rememberMapViewWithLifecycle(clipToOutline: Boolean): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = R.id.map
        }
    }

    // Note: Extract the lines until "lifecycleObserver" into another function/file
    var mLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), mapView)
    mLocationOverlay.enableMyLocation()
    mapView.overlays.add(mLocationOverlay)

    mapView.controller.setZoom(20.0)
    // while this idea might be cool ... it is somehow flawed
    // as "mLocationOverlay.myLocation" does NOT return the
    // correct location ... somehow
    mapView.controller.setCenter(GeoPoint(50.98957579738558, 7.155953029479965))

    // from: https://stackoverflow.com/a/63943996
    mapView.setScrollableAreaLimitDouble(BoundingBox(85.0, 180.0, -85.0, -180.0))
    mapView.maxZoomLevel = 22.0
    mapView.minZoomLevel = 4.0
    mapView.setHorizontalMapRepetitionEnabled(false)
    mapView.setVerticalMapRepetitionEnabled(false)
    mapView.setScrollableAreaLimitLatitude(
        MapView.getTileSystem().maxLatitude,
        MapView.getTileSystem().minLatitude, 0
    )

    // Makes MapView follow the lifecycle of this composable
    val lifecycleObserver = rememberMapLifecycleObserver(mapView)
    val lifecycle = androidx.lifecycle.compose.LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    // The following line is probably the most important
    // line in this whole project!
    // The reason is that this line allows the mapView
    // to respect other Composable Objects and not simply
    // fill the while screen and be extremely annoying
    mapView.setClipToOutline(clipToOutline)

    return mapView
}

@Composable
fun rememberMapLifecycleObserver(mapView: MapView): LifecycleEventObserver =
    remember(mapView) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> mapView.onResume()
                Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                else -> {}
            }
        }
    }