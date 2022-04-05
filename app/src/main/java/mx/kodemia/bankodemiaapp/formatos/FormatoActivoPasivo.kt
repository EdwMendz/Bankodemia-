package mx.kodemia.bankodemiaapp.formatos

fun darFormatoActivoOPasivo(esEntrada: Boolean, dinero: Double): String{
    return if(esEntrada){
        "+ ${darFormatoDinero(dinero)}"
    }else{
        "- ${darFormatoDinero(dinero)}"
    }
}