package com.practicum.myweatherapp.adapters

import android.view.View
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.practicum.myweatherapp.databinding.ListItemBinding


class WeatherAdapter : ListAdapter<WeatherInfo, WeatherAdapter.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) { // класс отвечает за отрисовку list_item
        val binding = ListItemBinding.bind(view) // собираем все элементы из разметки list_item в один класс
        fun bind(item: WeatherInfo) = with(binding) {
            tvListDate.text = item.time
            tvListCondition.text = item.condition
            tvListTemp.text = item.currentTemp
        }
    }
}