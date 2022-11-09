package com.practicum.myweatherapp.fragments

import android.Manifest
import android.app.DownloadManager.Request
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.practicum.myweatherapp.R
import com.practicum.myweatherapp.adapters.VpAdapter
import com.practicum.myweatherapp.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private val fragmentList = listOf(HoursFragment.newInstance(), DaysFragment.newInstance())
    private lateinit var pLauncher: ActivityResultLauncher<String> // лаунчер разрешений
    private lateinit var binding: FragmentMainBinding
    private val tabList = listOf("Hours", "Days")

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
    }

    private fun init() = with(binding){
        val adapter = VpAdapter(activity as FragmentActivity, fragmentList)
        vPager.adapter = adapter
        TabLayoutMediator(tabLayout ,vPager) { //  переключение между часами и днями
            tab, position -> tab.text = tabList[position]
        }.attach() // включение переключателя
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

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()

    }
}