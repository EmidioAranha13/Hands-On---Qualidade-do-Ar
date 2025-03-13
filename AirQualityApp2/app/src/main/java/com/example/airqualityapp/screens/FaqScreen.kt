package com.example.airqualityapp.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.airqualityapp.ui.theme.AirQualityAppTheme
import com.example.airqualityapp.utils.cards.InfoCard
import com.example.airqualityapp.utils.getBackgroundGradient

val questions = listOf(
    "Sobre o AirQuality" to 1,
    "Informações da tela principal" to 2,
    "Entendendo os dados do sensor DHT11" to 3,
    "Entendendo os dados do sensor SDS011" to 4,
    "Entendendo os dados do sensor MQ9" to 5,
    "Sobre o mapa" to 6,
    "Notificações do aplicativo" to 7)

@Composable
fun FaqScreen () {
    val listState = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getBackgroundGradient())
            .padding(0.dp)
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth() // Preenche a largura disponível
                        .padding(top = 36.dp)
                ){
                Text(
                    text = "Dúvidas Frequentes",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 20.dp)
                )}
            }

            itemsIndexed(questions) {index, (question, answer) ->
                InfoCard(question = question, answer = answer, index = index + 1, listState = listState)
            }
            item {
                Box(modifier = Modifier.padding(top = 20.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FaqScreenPreview() {
    AirQualityAppTheme {
        FaqScreen()
    }
}