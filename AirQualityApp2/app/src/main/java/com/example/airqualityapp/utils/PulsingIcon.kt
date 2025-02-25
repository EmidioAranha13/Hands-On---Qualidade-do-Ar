package com.example.airqualityapp.utils

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.airqualityapp.ui.theme.AirQualityAppTheme

@Composable
fun PulsingIcon(
    imageVector: ImageVector,
    contentDescription: String,
    alertLevel: Int
) {
    val elevation by animateDpAsState(
        targetValue = if (alertLevel > 0) 8.dp else 0.dp, // Alterar o valor conforme o nível de alerta
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        tint = when (alertLevel) {
            1 -> Color.Green
            2 -> Color.Yellow
            3 -> Color.Red
            else -> Color.Gray
        },
        modifier = Modifier
            .size(48.dp)
            .shadow(elevation, shape = CircleShape)
            .padding(4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    AirQualityAppTheme {
        PulsingIcon(
            imageVector = Icons.Default.Info,
            contentDescription = "Alerta",
            alertLevel = 4
        )
    }
}
