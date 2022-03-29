package mx.kodemia.bankodemiaapp.formatos

fun darFormatoHoraMinutos(horaApi: String): String{
    var formatoFinal = ""
    val hora = horaApi.substring(11,13).toInt()
    return if(hora > 12){
        val reformato = horaApi.substring(11,13).toInt() - 12
        formatoFinal = "${reformato} ${horaApi.substring(13,16)} p.m."
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