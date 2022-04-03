package mx.kodemia.bankodemiaapp.core

import android.app.Activity
import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object CheckToken {

    fun monitorToken(context: Context,horaActual: String): Boolean{
        val shared = SharedPreferencesInstance.obtenerInstancia(context)
        val horaGuardada = shared.obtenerHoraDeInicioDeSesion()

        if(horaGuardada.isNullOrEmpty()){
            return false
        }else if(horaActual.substring(1, 3).toInt() >= horaGuardada.substring(1, 3).toInt().plus(5)){
            Log.e("Token","Token caducado")
            return false
        }else{
            return true
        }
    }

    fun obtenerHoraActual(): String {
        val horaActual = System.currentTimeMillis()
        return SimpleDateFormat("HHmm", Locale.getDefault()).format(Date(horaActual))
    }
}