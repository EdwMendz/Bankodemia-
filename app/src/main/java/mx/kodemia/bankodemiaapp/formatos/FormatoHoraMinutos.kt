package mx.kodemia.bankodemiaapp.formatos

fun darFormatoHoraMinutos(horaApi: String): String{
    val formatoFinal: String
    var hora = horaApi.substring(11,13).toInt()
    if(hora <= 5){
        hora = 23-(5-(hora+1))
    }else{
        hora-=5
    }
    if(hora == 0){
        hora = 1
    }

    formatoFinal = if(hora > 12){
        val reformato = hora - 12
        "$reformato${horaApi.substring(13,16)} p.m."
    }else{
        if(hora > 9){
            hora.toString() + horaApi.substring(13,16) + " a.m."
        }else{
            hora.toString() + horaApi.substring(13,16) + " a.m."
        }
    }

    return formatoFinal

}