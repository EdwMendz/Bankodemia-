package mx.kodemia.bankodemiaapp.formatos

fun darFormatoHoraMinutos(horaApi: String): String{
    val formatoFinal: String
    var hora = horaApi.substring(11,13).toInt()
    if(hora <= 4){
        hora = 23-(5-(hora+1))
    }else{
        hora-=5
    }
    if(hora == 0){
        hora = 1
    }
    return if(hora > 12){
        val reformato = hora - 12
        formatoFinal = "${reformato}${horaApi.substring(13,16)} p.m."
        formatoFinal
    }else{
        if(hora > 9){
            formatoFinal = "${horaApi.substring(11,16)} a.m."
            formatoFinal
        }else{
            formatoFinal = "${horaApi.substring(12,16)} a.m."
            formatoFinal
        }
    }
}