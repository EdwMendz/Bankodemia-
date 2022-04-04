package mx.kodemia.bankodemiaapp.formatos

import android.graphics.Color
import android.widget.TextView

fun darFormatoActivoOPasivo(esEntrada: Boolean, dinero: Double): String{
    if(esEntrada){
        return "+ ${darFormatoDinero(dinero)}"
    }else{
        return "- ${darFormatoDinero(dinero)}"
    }
}