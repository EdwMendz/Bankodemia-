package mx.kodemia.bankodemiaapp.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun checkForInternet(context: Context): Boolean{
    //Registrar la actividad con el servicio connectivity manager
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    // Si la version de Android es M o mayor se usa NetworkCapabilities
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        //Devuelve un objeto de tipo Network correspondiente a la conectividad del dispositivo
        val network = connectivityManager.activeNetwork ?: return false
        //Representation of the capabilities of an active network
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            //Indica si la red usa transporte WiFi o tiene conectividad WiFi
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {

                true
            }
            //Indica si la red tiene conectividad por datos moviles
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {

                true
            }
            //Indica si la red tiene conectividad por ethernet
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {

                true
            }

            else -> false
        }
    }else{
        //Si la version de Android es menor a M
        @Suppress("DEPRECATION") val networkInfo =
            connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
    }

}