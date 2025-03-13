package com.example.airqualityapp.utils.faq

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.airqualityapp.R
import com.example.airqualityapp.utils.faqAnswers

@Composable
fun SensorDHT11Explanetion() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = faqAnswers[10],
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            textAlign = TextAlign.Justify,
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                //.offset(x = (-1).dp)
                .padding(bottom = 16.dp),
            painter = painterResource(R.drawable.home_6), // Substitua pelo nome da sua imagem
            contentDescription = "home page 1",
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = faqAnswers[11],
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            textAlign = TextAlign.Justify,
        )
    }
}