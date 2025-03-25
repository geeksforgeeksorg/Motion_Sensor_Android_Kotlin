package org.geeksforgeeks.motionsensor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val accelerometerSensor: MeasurableSensor
) : ViewModel() {

    private val _sensorData = MutableLiveData<Pair<Float, Float>>() // Pair for X and Y axis
    val sensorData: LiveData<Pair<Float, Float>> get() = _sensorData

    init {
        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values ->
            val sides = values[0]  // X-axis
            val upDown = values[1] // Y-axis
            _sensorData.postValue(Pair(sides, upDown))
        }
    }
}