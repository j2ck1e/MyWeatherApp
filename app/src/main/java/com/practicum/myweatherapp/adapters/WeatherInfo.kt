package com.practicum.myweatherapp.adapters

import java.util.concurrent.locks.Condition

data class WeatherInfo(
    val city : String,
    val time : String,
    val condition: String,
    val currentTemp : String,
    val maxTemp : String,
    val minTemp : String,
    val imageUrl : String,
    val hours: String


)