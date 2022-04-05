package mx.kodemia.bankodemiaapp.formatos

import android.content.Context

fun darFormatoDiaMesAnioHora(fecha: String, context: Context): String{

    val dia = fecha.substring(8,10).toInt()
    val mes = cambiarNumeroAMes(fecha.substring(5,7).toInt(), context)
    val anio = fecha.substring(0,4)
    var hora = fecha.substring(11,13)
    val minutos = fecha.substring(13,16)

    var horaEnEntero = hora.toInt()

    if(horaEnEntero <= 5){
        horaEnEntero = 23-(5-(horaEnEntero+1))
    }else{
        horaEnEntero-=5
    }
    if(horaEnEntero == 0){
        horaEnEntero = 1
    }

    hora = if(horaEnEntero > 12){
        val reformato = horaEnEntero - 12
        "$reformato$minutos p.m."
    }else{
        if(horaEnEntero > 9){
            horaEnEntero.toString() + fecha.substring(13,16) + " a.m."
        }else{
            horaEnEntero.toString() + fecha.substring(13,16) + " a.m."
        }
    }

    return "$dia $mes $anio, $hora"
}