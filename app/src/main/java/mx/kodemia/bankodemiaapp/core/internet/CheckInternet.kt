package mx.kodemia.bankodemiaapp.core.internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance

object CheckInternet {

    fun isNetworkAvailable(context: Context): Boolean {
        val shared = SharedPreferencesInstance.obtenerInstancia(context)

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    shared.guardarEstadoRed(true)
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    shared.guardarEstadoRed(true)
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    shared.guardarEstadoRed(true)
                    return true
                }
            }
        }
        shared.guardarEstadoRed(false)
        return false
    }

}