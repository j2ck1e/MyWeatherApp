package com.practicum.myweatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.myweatherapp.MainViewModel
import com.practicum.myweatherapp.R
import com.practicum.myweatherapp.adapters.WeatherAdapter
import com.practicum.myweatherapp.adapters.WeatherInfo
import com.practicum.myweatherapp.databinding.FragmentHoursBinding
import org.json.JSONArray
import org.json.JSONObject


class HoursFragment : Fragment() {

    private lateinit var binding : FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    private val model : MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHoursBinding.inflate(inflater, container, false)// Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            adapter.submitList(getHoursModel(it))
        }
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter(null)
        rcView.adapter = adapter

    }

    private fun getHoursModel(wItem : WeatherInfo) : List<WeatherInfo> {
        val hoursArray = JSONArray(wItem.hours)
        val hoursList = ArrayList<WeatherInfo>()
        for (i in 0 until hoursArray.length()) {
            val item = WeatherInfo(
                "",
                (hoursArray[i] as JSONObject).getString("time"),
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("text"),
                (hoursArray[i] as JSONObject).getString("temp_c").toFloat().toInt().toString() + "°C",
                "",
                "",
                (hoursArray[i] as JSONObject).getJSONObject("condition").getString("icon"),
                ""
            )
            hoursList.add(item)

        }
        return hoursList
    }

    companion object {

        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}