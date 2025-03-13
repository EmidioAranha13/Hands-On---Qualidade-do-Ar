package com.example.airqualityapp.utils.faq

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.airqualityapp.R
import com.example.airqualityapp.utils.faqAnswers

@Composable
fun AboutApp () {
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = faqAnswers[0],
            style = MaterialTheme.typography.bodyLarge,
            //fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(1f)
                .offset(x = (-1).dp)
                .padding(vertical = 20.dp),
            painter = painterResource(R.drawable.initiate_1), // Substitua pelo nome da sua imagem
            contentDescription = "onboarding",
        )
        Text(
            text = faqAnswers[1],
            style = MaterialTheme.typography.bodyLarge,
            //fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth().padding(16.dp).padding(top = 10.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(1f)
                .offset(x = (-1).dp)
                .padding(vertical = 20.dp),
            painter = painterResource(R.drawable.initiate_2), // Substitua pelo nome da sua imagem
            contentDescription = "onboarding1",
        )
        Text(
            text = faqAnswers[2],
            style = MaterialTheme.typography.bodyLarge,
            //fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth().padding(16.dp).padding(top = 10.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(1f)
                .offset(x = (-1).dp)
                .padding(vertical = 20.dp),
            painter = painterResource(R.drawable.initiate_3), // Substitua pelo nome da sua imagem
            contentDescription = "onboarding2",
        )
        Text(
            text = faqAnswers[3],
            style = MaterialTheme.typography.bodyLarge,
            //fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth().padding(16.dp).padding(top = 10.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(1f)
                .offset(x = (-1).dp)
                .padding(vertical = 20.dp),
            painter = painterResource(R.drawable.initiate_4), // Substitua pelo nome da sua imagem
            contentDescription = "onboarding3",
        )
    }
}