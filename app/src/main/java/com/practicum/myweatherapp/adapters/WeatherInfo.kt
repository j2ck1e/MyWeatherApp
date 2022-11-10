package com.practicum.myweatherapp.adapters

import java.util.concurrent.locks.Condition

data class WeatherInfo(
    val city : String,
    val time : String,
    val currentTemp : String,
    val maxTemp : String,
    val minTemp : String,
    val condition: String,
    val hours: String,
    val imageUrl : String

)