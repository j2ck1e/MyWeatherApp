package com.practicum.myweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.practicum.myweatherapp.databinding.ActivityMainBinding
import com.practicum.myweatherapp.fragments.FirstFragment
import com.practicum.myweatherapp.fragments.MainFragment
import org.json.JSONObject

//const val API_KEY = "8e4815cd5b2244ffa7f190506220411"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.placeHolder, FirstFragment.newInstance()).commit() // накладываем фрагмент поверх MainActivity
    }

   /* private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonGet.setOnClickListener {
            getWeather("London")
        }
    }
    private fun getWeather(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json?" +
                "key=$API_KEY&q=$name&aqi=no"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(Request.Method.GET, url,
            {response ->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                Log.d("MyLog", "Volley response: ${temp.getString("temp_c")}")},
            { Log.d("MyLog", "Volley error: $it")})
        queue.add(stringRequest)
    }*/
}