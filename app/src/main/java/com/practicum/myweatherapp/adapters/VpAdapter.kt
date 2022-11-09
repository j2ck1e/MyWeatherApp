package com.practicum.myweatherapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class VpAdapter(fragActivity: FragmentActivity, private val list: List<Fragment>) : FragmentStateAdapter(fragActivity) {
    override fun getItemCount(): Int {
        return list.size // количество переключяаемых фрагментов
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}