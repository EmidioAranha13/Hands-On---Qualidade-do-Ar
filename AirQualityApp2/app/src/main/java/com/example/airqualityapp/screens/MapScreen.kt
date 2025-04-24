package com.example.airqualityapp.screens

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.airqualityapp.ui.theme.AirQualityAppTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import java.util.ArrayList
import androidx.core.content.ContextCompat
import com.example.airqualityapp.R
import kotlin.random.Random
import androidx.core.graphics.scale
import androidx.core.graphics.drawable.toDrawable
import com.example.airqualityapp.ui.theme.BlueishBlack
import com.example.airqualityapp.ui.theme.IndigoBlue
import com.example.airqualityapp.ui.theme.MidnightBlue
import com.example.airqualityapp.ui.theme.SkyBlue
import com.example.airqualityapp.ui.theme.VibrantPurple
import org.osmdroid.views.CustomZoomButtonsController
import java.time.LocalTime

fun setPoint (
    resources: android.content.res.Resources,
    context: android.content.Context,
    value: Int
): BitmapDrawable {
    val newDrawable: Drawable
    when (value) {
        in 0..40 -> {
            newDrawable = ContextCompat.getDrawable(context, R.drawable.bom_iqa)!!
        }
        in 41..80 -> {
            newDrawable = ContextCompat.getDrawable(context, R.drawable.neutro_iqa)!!
        }
        in 81..120 -> {
            newDrawable = ContextCompat.getDrawable(context, R.drawable.ruim_iqa)!!
        }
        in 121..200 -> {
            newDrawable = ContextCompat.getDrawable(context, R.drawable.worst_iqa)!!
        }
        else -> {
            newDrawable = ContextCompat.getDrawable(context, R.drawable.critical_iqa)!!
        }
    }
    val bitmap = (newDrawable as BitmapDrawable).bitmap
    val resizedBitmap = bitmap.scale(48, 48, false)
    return resizedBitmap.toDrawable(resources)
}

fun getColorTime(): Color {
    val time = LocalTime.now()
    return when (time) {
        in LocalTime.of(5, 30) .. LocalTime.of(6, 59) -> IndigoBlue
        in LocalTime.of(7, 0) .. LocalTime.of(14, 59) -> SkyBlue
        in LocalTime.of(15, 0) .. LocalTime.of(17, 29) -> VibrantPurple
        in LocalTime.of(17, 30) .. LocalTime.of(18, 59) -> MidnightBlue
        else -> BlueishBlack
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    val mapViewState = remember { mutableStateOf<MapView?>(null) } // Guarda a referência do MapView

    @Composable
    fun ZoomControls(
        mapView: MapView,
        modifier: Modifier = Modifier
    ) {
        Column(
            modifier = modifier.padding(16.dp)
                .background(Color.White.copy(alpha = 0.8f), shape = RoundedCornerShape(12.dp))
                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(12.dp)).width(50.dp)
                //.padding(bottom = 6.dp),
            ,verticalArrangement = Arrangement.spacedBy(2.dp)
            ,horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = { mapView.controller.zoomIn() },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    modifier = Modifier.size(35.dp),
                    painter = painterResource(R.drawable.zoom_in),
                    contentDescription = "Zoom In")
            }
            HorizontalDivider(
                modifier = Modifier.padding(8.dp).fillMaxWidth(),
                thickness = 1.dp,
                color = Color.Gray
            )
            IconButton(
                onClick = { mapView.controller.zoomOut() },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    modifier = Modifier.size(35.dp),
                    painter = painterResource(R.drawable.zoom_out),
                    contentDescription = "Zoom Out")
            }
        }
    }

    Box(modifier = modifier) {
        AndroidView(
            factory = { context ->
                MapView(context).apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)
                    zoomController.setVisibility(CustomZoomButtonsController.Visibility.NEVER)

                    val ponto = GeoPoint(-3.10719, -60.0261)
                    val manausLat = -3.100027
                    val manausLon = -60.0261
                    val coords = ArrayList<GeoPoint>()
                    val random = Random
                    val range = 0.05

                    for (i in 0..10) {
                        val randomLat: Double = manausLat + (random.nextDouble() * 2 - 1) * range
                        val randomLon: Double = manausLon + (random.nextDouble() * 2 - 1) * range
                        coords.add(GeoPoint(randomLat, randomLon))
                    }

                    controller.setZoom(12.5)
                    controller.setCenter(ponto)

                    for (i in coords) {
                        val marker = Marker(this)
                        val newValue = Random.nextInt(0, 301)
                        marker.position = i
                        marker.title = "IQA: $newValue"
                        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                        //icons
                        marker.icon = setPoint(resources, context, newValue)
                        this.overlays.add(marker)
                    }
                    mapViewState.value = this
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        mapViewState.value?.let { mapView ->
            ZoomControls(
                mapView = mapView,
                modifier = Modifier.align(Alignment.BottomEnd) // ou qualquer canto que quiser
            )
        }

        Button(
            modifier = Modifier.padding(16.dp).align(Alignment.BottomStart),
            colors = ButtonDefaults.buttonColors(
                containerColor = getColorTime(),
                contentColor = Color.White
            ),
            onClick = {
            val database = Firebase.firestore

            val sensor = hashMapOf(
                "pm_25" to 1.6,
                "pm_10" to 0.25,
                "isValid" to true
            )

            database.collection("sensor").document("dia1").set(sensor).addOnSuccessListener {
                Log.d("Firebase","Documento adicionado com sucesso")
            }.addOnFailureListener { e ->
                Log.w("Firebase", "Erro ao escrever documento", e)
            }
        }) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Adicionar Ponto",
            )
            Text(text = "Adicionar Ponto")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MapPreview() {
    AirQualityAppTheme {
        MapScreen()
    }
}
