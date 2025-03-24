package com.example.airqualityapp.screens

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MapScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        AndroidView(
            factory = { context ->
                MapView(context).apply {
                    setTileSource(TileSourceFactory.MAPNIK)
                    setMultiTouchControls(true)

                    val ponto = GeoPoint(-3.119027, -60.021731)
                    val manausLat = -3.100027
                    val manausLon = -59.959999
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
                }
            },
            modifier = Modifier
                .fillMaxSize()
        )
        Button(onClick = {
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
            Text("Oi")
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
