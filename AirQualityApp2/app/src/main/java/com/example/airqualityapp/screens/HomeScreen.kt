package com.example.airqualityapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.airqualityapp.MQ9
import com.example.airqualityapp.SDS011
import com.example.airqualityapp.DHT11
import com.example.airqualityapp.Sensors
import com.example.airqualityapp.ui.theme.AirQualityAppTheme
import com.example.airqualityapp.utils.cards.AirQualitySummary
import com.example.airqualityapp.utils.cards.SensorCard
import com.example.airqualityapp.utils.cards.SensorCardDualValues
import com.example.airqualityapp.utils.getBackgroundGradient
import com.example.airqualityapp.utils.validateDataSensors
import com.example.airqualityapp.viewModel.SensorsViewModel
import java.time.LocalTime

@Composable
fun HomeScreen(
    sensors: Sensors,
    sensorsViewModel: SensorsViewModel = viewModel(),
    currentTime: LocalTime = LocalTime.now()
) {
    /*
    O Código está rodando no modo viewModel. Para fazê-lo funcionar com sensores reais
    comente os itens com o comentário "ViewModel Aqui" e substitua a variável sensorsTest
    no código por sensors que vem dos paramentros da função HomeScreen.
     */
    val sensorSDS011 by sensorsViewModel.sensorSDS011.collectAsState() //ViewModel Aqui
    val sensorDHT11 by sensorsViewModel.sensorDHT11.collectAsState()   //ViewModel Aqui
    val sensorMQ9 by sensorsViewModel.sensorMQ9.collectAsState()       //ViewModel Aqui
    val sensorIQA by sensorsViewModel.sensorIQA.collectAsState()       //ViewModel Aqui

    //ViewModel Aqui
    val sensorsTest = sensors.copy(
        sds011 = sensorSDS011,
        dht11 = sensorDHT11,
        mq9 = sensorMQ9,
        iqa = sensorIQA
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getBackgroundGradient(currentTime))
            .padding(0.dp)
    ) {
        // Instâncias dos sensores com valores em Float

        val validatedData = validateDataSensors(sensorsTest).sortedBy { it.priority }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                AirQualitySummary(
                    city = "Manaus",
                    state = "AM",
                    iqa = sensorsTest.iqa,
                    )
            }

            items(validatedData) {sensorData ->
                when (val sensor = sensorData.sensor) {
                    is DHT11 -> {
                        SensorCardDualValues(
                            sensorName = "DHT11",
                            value1Title = "Temperatura",
                            value1 = sensor.temperature,
                            value1Unit = "°C",
                            value2Title = "Umidade",
                            value2 = sensor.humidity,
                            value2Unit = "%",
                            priority = sensorData.priority,
                            extraInfo = sensorData.extraInfo
                        )
                    }

                    is SDS011 -> {
                        SensorCardDualValues(
                            sensorName = "SDS011",
                            value1Title = "PM2.5",
                            value1 = sensor.pm25,
                            value1Unit = "µg/m³",
                            value2Title = "PM10",
                            value2 = sensor.pm10,
                            value2Unit = "µg/m³",
                            priority = sensorData.priority,
                            extraInfo = sensorData.extraInfo
                        )
                    }

                    is MQ9 -> {
                        SensorCard(
                            "Monóxido de Carbono",
                            "${sensor.carbonMonoxide} ppm",
                            "MQ9",
                            priority = sensorData.priority,
                            extraInfo = sensorData.extraInfo)
                    }
                }
            }
            item {
                Box(modifier = Modifier.padding(top = 20.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ValueInputScreenPreview1() {
    AirQualityAppTheme {
        HomeScreen(sensors = Sensors(DHT11(), SDS011(20f, 35f), MQ9(), 40), currentTime =  LocalTime.of(8, 0))
    }
}

@Preview(showBackground = true)
@Composable
fun ValueInputScreenPreview2() {
    AirQualityAppTheme {
        HomeScreen(Sensors(DHT11(), SDS011(40f, 35f), MQ9(), 75), currentTime = LocalTime.of(16, 0))
    }
}

@Preview(showBackground = true)
@Composable
fun ValueInputScreenPreview3() {
    AirQualityAppTheme {
        HomeScreen(Sensors(DHT11(), SDS011(75f, 40f), MQ9(), 120), currentTime = LocalTime.of(19, 0))
    }
}

@Preview(showBackground = true)
@Composable
fun ValueInputScreenPreview4() {
    AirQualityAppTheme {
        HomeScreen(Sensors(DHT11(), SDS011(120f, 100f), MQ9(), 200), currentTime = LocalTime.of(0, 0))
    }
}

@Preview(showBackground = true)
@Composable
fun ValueInputScreenPreview5() {
    AirQualityAppTheme {
        HomeScreen(Sensors(DHT11(25f, 46f), SDS011(200f, 150f), MQ9(), 251), currentTime = LocalTime.of(7, 0))
    }
}
