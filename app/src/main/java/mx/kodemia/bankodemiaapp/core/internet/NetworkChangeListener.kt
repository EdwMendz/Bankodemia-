package mx.kodemia.bankodemiaapp.core.internet

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance

class NetworkChangeListener: BroadcastReceiver() {

    private val checkInternet = CheckInternet
    private val alert = Alerts

    override fun onReceive(context: Context, intent: Intent?) {

        val shared = SharedPreferencesInstance.obtenerInstancia(context)

            if(!shared.obtenerEstadoRed() && checkInternet.isNetworkAvailable(context)) // Si se tiene acceso a internet
                alert.showToast(context.getString(R.string.si_internet), context)

            if(!checkInternet.isNetworkAvailable(context)) // Si no se tiene acceso a internet
                alert.showToast(context.getString(R.string.no_internet), context)

    }

}