package com.example.airqualityapp.utils.faq

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.airqualityapp.R

@Composable
fun NotificationsExplanetion() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "As notificações alertam sobre mudanças significativas na qualidade do ar ou outros eventos importantes.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
    }
}