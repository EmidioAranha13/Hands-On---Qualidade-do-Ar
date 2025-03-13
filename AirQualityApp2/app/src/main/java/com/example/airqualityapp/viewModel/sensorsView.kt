package com.example.airqualityapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.airqualityapp.MQ9
import com.example.airqualityapp.SDS011
import com.example.airqualityapp.DHT11
import com.example.airqualityapp.utils.calculateIQA
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class SensorsViewModel : ViewModel() {
    // Estados dos sensores (usando StateFlow para reatividade)
    private val _sensorSDS011 = MutableStateFlow(SDS011(0f, 0f))
    val sensorSDS011: StateFlow<SDS011> = _sensorSDS011

    private val _sensorDHT11 = MutableStateFlow(DHT11(25f, 50f))
    val sensorDHT11: StateFlow<DHT11> = _sensorDHT11

    private val _sensorMQ9 = MutableStateFlow(MQ9(0f))
    val sensorMQ9: StateFlow<MQ9> = _sensorMQ9

    private val _sensorIQA = MutableStateFlow(0)
    val sensorIQA: StateFlow<Int> = _sensorIQA

    // Gera os valores de forma distribuida sem saltos muito grandes
    private fun distributedValueGenerator(range: IntRange, limitFreq: Int): Float {
        return if (Random.nextDouble() < 0.7) {
            Random.nextInt(range.first, limitFreq).toFloat()
        } else {
            Random.nextInt(limitFreq, range.last).toFloat()
        }
    }

    // Ajusta o valor anterior para garantir transições suaves
    private fun adjustPreviousValue(currentValue: Float, newValue: Float, hop: Float): Float {
        return when {
            newValue > currentValue -> (currentValue + hop).coerceAtMost(newValue)
            newValue < currentValue -> (currentValue - hop).coerceAtLeast(newValue)
            else -> newValue
        }
    }

    init {
        viewModelScope.launch {
            while (true) {
                val newPm25 = distributedValueGenerator(0..125, 75)
                val newPm10 = distributedValueGenerator(0..250, 150)
                val newCo = distributedValueGenerator(0..15, 13)
                val newTemp = Random.nextFloat().coerceIn(0f, 50f)
                val newHum = Random.nextFloat().coerceIn(20f, 90f)
                val newIQA = calculateIQA(newPm25, newPm10, newCo)

                // Atualiza os valores dos sensores garantindo transições suaves
                _sensorSDS011.value = _sensorSDS011.value.copy(
                    pm25 = adjustPreviousValue(_sensorSDS011.value.pm25, newPm25, 5f),
                    pm10 = adjustPreviousValue(_sensorSDS011.value.pm10, newPm10, 10f)
                )
                _sensorMQ9.value = _sensorMQ9.value.copy(
                    carbonMonoxide = adjustPreviousValue(_sensorMQ9.value.carbonMonoxide, newCo, 1f)
                )
                _sensorDHT11.value = _sensorDHT11.value.copy(
                    temperature = adjustPreviousValue(_sensorDHT11.value.temperature, newTemp, 0.5f),
                    humidity = adjustPreviousValue(_sensorDHT11.value.humidity, newHum, 2.0f)
                )
                _sensorIQA.value = adjustPreviousValue(_sensorIQA.value.toFloat(), newIQA.toFloat(), 5f).toInt()

                delay(10000) // Atualiza a cada 5 segundos
            }
        }
    }
}


