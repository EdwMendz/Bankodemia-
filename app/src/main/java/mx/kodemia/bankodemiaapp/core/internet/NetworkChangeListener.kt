package mx.kodemia.bankodemiaapp.core.internet

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance

class NetworkChangeListener : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

        val shared = SharedPreferencesInstance.obtenerInstancia(context)

        if (!shared.obtenerEstadoRed() && CheckInternet.isNetworkAvailable(context)) // Si se tiene acceso a internet
            Alerts.showToast(context.getString(R.string.si_internet), context)

        if (!CheckInternet.isNetworkAvailable(context)) // Si no se tiene acceso a internet
            Alerts.showToast(context.getString(R.string.no_internet), context)

    }

}