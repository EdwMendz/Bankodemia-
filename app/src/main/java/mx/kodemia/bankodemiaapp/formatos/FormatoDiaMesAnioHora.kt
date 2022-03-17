package mx.kodemia.bankodemiaapp.formatos

import android.content.Context

fun darFormatoDiaMesAnioHora(fecha: String, context: Context): String{

    val dia = fecha.substring(8,10).toInt()
    val mes = cambiarNumeroAMes(fecha.substring(5,7).toInt(), context)
    val anio = fecha.substring(0,4)
    var hora = fecha.substring(11,13)
    val minutos = fecha.substring(13,16)

    if(hora.toInt() > 12){
        val reformato = fecha.substring(11,13).toInt() - 12
        hora = "$reformato$minutos p.m."
    }else{
        if(hora.toInt() > 9){
            hora = fecha.substring(11,16) + " a.m."
        }else{
            hora = fecha.substring(12,16) + " a.m."
        }
    }

    return "$dia $mes $anio, $hora"
}