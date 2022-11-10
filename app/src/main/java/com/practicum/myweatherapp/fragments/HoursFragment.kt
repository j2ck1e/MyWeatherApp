package com.practicum.myweatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.myweatherapp.R
import com.practicum.myweatherapp.adapters.WeatherAdapter
import com.practicum.myweatherapp.adapters.WeatherInfo
import com.practicum.myweatherapp.databinding.FragmentHoursBinding


class HoursFragment : Fragment() {

    private lateinit var binding : FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter

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
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter
        var list = listOf(
            WeatherInfo("", "14:00",
                "4°C","", "",
                "Cloudly","",""),
            WeatherInfo("", "15:00",
                "6°C","", "",
                "Cloudly","",""),
            WeatherInfo("", "16:00",
                "7°C","", "",
                "Cloudly","","")  // для теста

        )
        adapter.submitList(list)
    }

    companion object {

        @JvmStatic
        fun newInstance() = HoursFragment()
    }
}