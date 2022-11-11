package com.practicum.myweatherapp.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayoutMediator
import com.practicum.myweatherapp.MainViewModel
import com.practicum.myweatherapp.R
import com.practicum.myweatherapp.adapters.VpAdapter
import com.practicum.myweatherapp.adapters.WeatherInfo
import com.practicum.myweatherapp.databinding.FragmentMainBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject
import kotlin.math.abs

const val API_KEY = "8e4815cd5b2244ffa7f190506220411"

class MainFragment : Fragment() {
    private val fragmentList = listOf(HoursFragment.newInstance(), DaysFragment.newInstance())
    private lateinit var pLauncher: ActivityResultLauncher<String> // лаунчер разрешений
    private lateinit var binding: FragmentMainBinding
    private val tabList = listOf(R.string.tab_list_hours, R.string.tab_list_days)
    private val model : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
        requestWeatherData("Cairo")
        updateCurrentCard()
    }

    private fun init() = with(binding) {
        val adapter = VpAdapter(activity as FragmentActivity, fragmentList)
        vPager.adapter = adapter
        TabLayoutMediator(tabLayout, vPager) { //  переключение между часами и днями
                tab, position ->
            tab.text = getString(tabList[position])
        }.attach() // включение переключателя
    }

    private fun updateCurrentCard() = with(binding) {
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            val tempMaxMin = "${it.maxTemp}°C/${it.minTemp}°C"
            tvDate.text = it.time
            tvCity.text = it.city
            tvCurrentTemp.text = "${it.currentTemp}°C"
            tvCondition.text = it.condition
            tvMaxMin.text = tempMaxMin
            Picasso.get().load("https:" + it.imageUrl).into(imWeather)
        }
    }

    private fun permissionListener() {  // запрос на разрешения
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission() { // проверка разрешения на исп локации
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun requestWeatherData(city: String) {
        val url = "https://api.weatherapi.com/v1/forecast.json?key=" +
                API_KEY +
                "&q=" +
                city +
                "&days=" +
                "3" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(Request.Method.GET, url,
            {
                    result -> parseWeatherData(result)
            },
            {
                error -> Log.d("MyLog", "Error: $error")
            })
        queue.add(request)
    }

    private fun parseWeatherData(result : String) {
        val mainObject = JSONObject(result)
        val list = parseDays(mainObject)
        parseCurrentData(mainObject, list[0])


    }

    private fun parseDays(mainObject: JSONObject) : List<WeatherInfo> {
        val list = ArrayList<WeatherInfo>()
        val name = mainObject.getJSONObject("location").getString("name")
        var daysArray = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
        for(i in 0 until daysArray.length()) {
            val day = daysArray[i] as JSONObject
            val item = WeatherInfo(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition").getString("text"),
                "",
                day.getJSONObject("day").getString("maxtemp_c"),
                day.getJSONObject("day").getString("mintemp_c"),
                day.getJSONObject("day").getJSONObject("condition").getString("icon"),
                day.getJSONArray("hour").toString()
            )
            list.add(item)
        }
        return list
    }

    private fun parseCurrentData(mainObject : JSONObject, weatherItem : WeatherInfo) { // основная карточка
        val item = WeatherInfo(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current").getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c"),
            weatherItem.maxTemp,
            weatherItem.minTemp,
            mainObject.getJSONObject("current").getJSONObject("condition").getString("icon"),
            weatherItem.hours)
            model.liveDataCurrent.value = item
        Log.d("MyLog", "City: ${item.city}," +
                " date: ${item.time}," +
                " temp: ${item.currentTemp}," +
                " condition: ${item.condition}," +
                " link: ${item.imageUrl}," +
                " max: ${item.maxTemp}," +
                " min: ${item.minTemp}," +
                " hour: ${item.hours}")
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()

    }
}