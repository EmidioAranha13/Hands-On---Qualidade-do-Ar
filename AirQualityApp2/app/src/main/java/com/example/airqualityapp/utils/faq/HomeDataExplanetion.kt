package com.example.airqualityapp.utils.faq

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.airqualityapp.R
import com.example.airqualityapp.ui.theme.AirQualityAppTheme
import com.example.airqualityapp.utils.IQA_Range
import com.example.airqualityapp.utils.cards.ResumeCard
import com.example.airqualityapp.utils.cards.airQualityStatus
import com.example.airqualityapp.utils.faqAnswers

//import com.example.airqualityapp.ui.theme.AirQualityAppTheme

@Composable
fun getIntIntoString(string: String): Int {
    return Regex("\\d+").findAll(string).map {
        it.value.toInt()
    }.toList()[0]
}

@Composable
fun HomeDataExplanetion() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = faqAnswers[4],
            style = MaterialTheme.typography.bodyLarge,
            //fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f)
            //.offset(x = (-1).dp)
                .padding(bottom = 16.dp),
            painter = painterResource(R.drawable.home_1), // Substitua pelo nome da sua imagem
            contentDescription = "home page 1",
        )
        Text(
            text = faqAnswers[5],
            style = MaterialTheme.typography.bodyLarge,
            //fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            //.offset(x = (-1).dp)
            //.padding(end = 16.dp),
            painter = painterResource(R.drawable.home_2), // Substitua pelo nome da sua imagem
            contentDescription = "home page 1",
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(0.8f)
            //.offset(x = (-1).dp)
                .padding(bottom = 16.dp),
            painter = painterResource(R.drawable.home_3), // Substitua pelo nome da sua imagem
            contentDescription = "home page 1",
        )
        Text(
            text = faqAnswers[6],
            style = MaterialTheme.typography.bodyLarge,
            //fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (triple in IQA_Range) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Qualidade: ",
                            style = MaterialTheme.typography.bodyLarge,
                            //fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Justify,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                        Box(
                            modifier = Modifier
                                .background(
                                    airQualityStatus(
                                        getIntIntoString(triple.second)
                                    ).color, shape = RoundedCornerShape(16.dp))
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = triple.first,
                                color = Color.White,
                                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    }
                    ResumeCard(
                        airQualityStatus(
                            getIntIntoString(triple.second)
                        ),
                        true
                    )
                }
            }
        }
        Text(
            text = faqAnswers[7],
            style = MaterialTheme.typography.bodyLarge,
            //fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(1f)
                //.offset(x = (-1).dp)
                .padding(bottom = 16.dp),
            painter = painterResource(R.drawable.home_4), // Substitua pelo nome da sua imagem
            contentDescription = "home page 1",
        )
        Text(
            text = faqAnswers[8],
            style = MaterialTheme.typography.bodyLarge,
            //fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth(1f)
                //.offset(x = (-1).dp)
                .padding(bottom = 16.dp),
            painter = painterResource(R.drawable.home_5), // Substitua pelo nome da sua imagem
            contentDescription = "home page 1",
        )
        Text(
            text = faqAnswers[9],
            style = MaterialTheme.typography.bodyLarge,
            //fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Justify,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                //.offset(x = (-1).dp)
                .padding(bottom = 16.dp),
            painter = painterResource(R.drawable.home_0), // Substitua pelo nome da sua imagem
            contentDescription = "home page 1",
        )

    }
}

@Preview(showBackground = true)
@Composable
fun HomeDataExplanetionPreview() {
    AirQualityAppTheme {
        Box {
            HomeDataExplanetion()
        }
    }
}