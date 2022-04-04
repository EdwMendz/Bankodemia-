package mx.kodemia.bankodemiaapp.core

import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object CheckToken {

    fun monitorToken(context: Context,horaActual: String): Boolean{
        val shared = SharedPreferencesInstance.obtenerInstancia(context)
        val horaGuardada = shared.obtenerHoraDeInicioDeSesion()

        Log.e("Actual",horaActual.substring(2, 4).toInt().toString())
        if(horaGuardada.isNullOrEmpty()){
            val algo = 1
        }else{
            Log.e("Guardada",horaGuardada.substring(2, 4).toInt().plus(1).toString())
        }

        if(horaGuardada.isNullOrEmpty()){
            return false
        }else if(horaActual.substring(2, 4).toInt() >= horaGuardada.substring(2, 4).toInt()+1){
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