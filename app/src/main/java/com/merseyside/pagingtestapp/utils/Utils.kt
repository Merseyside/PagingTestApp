package com.merseyside.pagingtestapp.utils

import android.content.Context
import android.net.ConnectivityManager
import com.merseyside.pagingtestapp.PropertyApplication

class Utils {

    companion object {

        fun decimetresToMetres(decimetres : Int) : Int {
            return decimetres / 100
        }

        fun isOnline(context: Context = PropertyApplication.getInstance()): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
        }
    }
}