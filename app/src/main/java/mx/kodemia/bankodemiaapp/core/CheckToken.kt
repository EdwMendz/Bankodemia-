package mx.kodemia.bankodemiaapp.core

import android.content.Context
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object CheckToken {

    fun monitorToken(context: Context, horaActual: String):Boolean {

        val shared = SharedPreferencesInstance.obtenerInstancia(context)
        val horaGuardada = shared.obtenerHoraDeInicioDeSesion()

        //Datos de hora actual
        val anioActuales = horaActual.substring(0,4).toInt()
        val mesActuales = horaActual.substring(4,6).toInt()
        val diaActuales = horaActual.substring(6,8).toInt()
        val horaActuales =  horaActual.substring(8,10).toInt()
        val minutoActuales = horaActual.substring(10,12).toInt()

        //Datos de hora guardada cuando se realizo el LogIn
        val anioGuardado = horaGuardada?.substring(0,4)
        val mesGuardados = horaGuardada?.substring(4,6)
        val diasGuardados = horaGuardada?.substring(6,8)
        val horasGuardadas = horaGuardada?.substring(8,10)
        val minutosGuardados = horaGuardada?.substring(10,12)

        if(horaGuardada.isNullOrEmpty()){
            return false
        }else{
            if(anioActuales == anioGuardado?.toInt()){ //Si se encuentra en el mismo anio
                if(mesActuales == mesGuardados?.toInt()){ //Si se encuentra en el mismo mes
                    if(diaActuales == diasGuardados?.toInt()){ //Si se encuentra en el mismo dia
                        if(minutosGuardados?.toInt()!! >= 50){ //Si los minutos ya estan por dar la entrada a una nueva hora
                            if(minutoActuales >= (minutosGuardados.toInt()-50) && horaActuales != horasGuardadas?.toInt()){
                                shared.limpiarPreferencias()
                                return false
                            }
                        }else{
                            if(horaActuales != horasGuardadas?.toInt()){
                                shared.limpiarPreferencias()
                                return false
                            }else{
                                if(minutoActuales >= minutosGuardados.toInt().plus(10) ){
                                    shared.limpiarPreferencias()
                                    return false
                                }
                            }
                        }
                    }else if(horasGuardadas?.toInt() == 23 && minutosGuardados?.toInt()!! >= 50 && minutoActuales <= (minutosGuardados.toInt()-50)){
                        Log.e("Token", "Token activo")
                        return true
                    } else{ //Si no se encuentra en el mismo dia
                        shared.limpiarPreferencias()
                        return false
                    }
                }else{ //Si no se encuentra en el mismo mes
                    shared.limpiarPreferencias()
                    return false
                }
            }else{ //Si no se encuentra en el mismo anio
                shared.limpiarPreferencias()
                return false
            }
            Log.e("Token", "Token activo")
            return true
        }
    }

    fun obtenerHoraActual(): String {
        val horaActual = System.currentTimeMillis()
        return SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault()).format(Date(horaActual))
    }

}