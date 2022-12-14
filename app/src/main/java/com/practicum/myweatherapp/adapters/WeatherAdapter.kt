package com.practicum.myweatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.practicum.myweatherapp.R
import com.practicum.myweatherapp.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class WeatherAdapter(val listener: Listener?) : ListAdapter<WeatherInfo, WeatherAdapter.Holder>(Comparator()) {

    class Holder(view: View, val listener: Listener?) : RecyclerView.ViewHolder(view){ // класс отвечает за отрисовку list_item
        val binding = ListItemBinding.bind(view)  // собираем все элементы из разметки list_item в один объект
        var itemTemp: WeatherInfo? = null
        init {
            itemView.setOnClickListener {
                itemTemp?.let { it1 -> listener?.onClick(it1) }
            }
        }

        fun bind(item: WeatherInfo) = with(binding){
            itemTemp = item
            tvListDate.text = item.time
            tvListCondition.text = item.condition
            tvListTemp.text = item.currentTemp.ifEmpty {item.maxTemp + "/" + item.minTemp}
            Picasso.get().load("https:" + item.imageUrl).into(imList)
        }
    }

    class Comparator : DiffUtil.ItemCallback<WeatherInfo>(){  // обновление данных с сервера
        override fun areItemsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherInfo, newItem: WeatherInfo): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder { // создаем Холдер
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) { // отрисовка элементов
        holder.bind(getItem(position))
    }

    interface Listener{
        fun onClick(item : WeatherInfo)
    }
}