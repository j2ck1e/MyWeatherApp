package com.practicum.myweatherapp.fragments


import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

// проверка на наличие разрешений (локация, камера и т.д.)
fun Fragment.isPermissionGranted(p: String) : Boolean {
    return ContextCompat.checkSelfPermission(activity as AppCompatActivity, p) == PackageManager.PERMISSION_GRANTED // 0 or -1
}