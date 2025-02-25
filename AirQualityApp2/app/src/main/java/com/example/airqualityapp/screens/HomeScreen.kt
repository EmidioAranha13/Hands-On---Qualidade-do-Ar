package com.example.airqualityapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.airqualityapp.ui.theme.AirQualityAppTheme
import com.example.airqualityapp.utils.AirQualitySummary
import com.example.airqualityapp.utils.SensorCard
import com.example.airqualityapp.utils.SensorCardDualValues
import com.example.airqualityapp.utils.getBackgroundGradient

data class DHT11(
    val temperature: Float,
    val humidity: Float,
    val priority: Int
)

data class SDS011(
    val pm25: Float,
    val pm10: Float,
    val priority: Int
)

data class MQ9(
    val carbonMonoxide: Float,
    val priority: Int
)

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getBackgroundGradient())
            .padding(4.dp)
    ) {
        // Instâncias dos sensores com valores em Float
        val dht11 = DHT11(temperature = 25.0f, humidity = 60.0f, priority = 1)
        val sds011 = SDS011(pm25 = 35.0f, pm10 = 50.0f, priority = 1)
        val mq9 = MQ9(carbonMonoxide = 9.0f, priority = 3)

        // Lista genérica com sensores e prioridades
        val sensorList = listOf(
            Triple("DHT11", dht11.priority, dht11),
            Triple("SDS011", sds011.priority, sds011),
            Triple("MQ9", mq9.priority, mq9)
        ).sortedBy { it.second } // Ordena pela prioridade

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AirQualitySummary(city = "São Paulo", state = "SP", pm25 = sds011.pm25, pm10 = sds011.pm10)
            sensorList.forEach { (name, _, sensor) ->
                when (sensor) {
                    is DHT11 -> {
                        SensorCardDualValues(
                            sensorName = name,
                            value1Title = "Temperatura",
                            value1 = sensor.temperature,
                            value1Unit = "°C",
                            value2Title = "Humidade",
                            value2 = sensor.humidity,
                            value2Unit = "%"
                        )
                    }

                    is SDS011 -> {
                        SensorCardDualValues(
                            sensorName = name,
                            value1Title = "PM2.5",
                            value1 = sensor.pm25,
                            value1Unit = "µg/m³",
                            value2Title = "PM10",
                            value2 = sensor.pm10,
                            value2Unit = "µg/m³"
                        )
                    }

                    is MQ9 -> {
                        SensorCard("Monóxido de Carbono", "${sensor.carbonMonoxide} ppm", name)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ValueInputScreenPreview() {
    AirQualityAppTheme {
        HomeScreen()
    }
}
