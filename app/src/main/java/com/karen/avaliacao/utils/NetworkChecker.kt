package com.karen.avaliacao.utils

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

class NetworkChecker(private val connectivityManager: ConnectivityManager?) {

    fun performActionIfConnect(action:() -> Unit){
        if(hasInternet())
            action()
    }

    fun performActionIfNotConnect(action:() -> Unit){
        if(!hasInternet())
            action()
    }

    private fun hasInternet(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network: Network = connectivityManager?.activeNetwork ?: return false
            val capabilities: NetworkCapabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return false

            return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)

        } else {
            val activeNetworkInfo: NetworkInfo? = connectivityManager?.activeNetworkInfo
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI
                        || activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
            }
            false
        }
    }

}