package mx.kodemia.bankodemiaapp.formatos

import android.graphics.Color
import android.widget.TextView

fun darFormatoActivoOPasivo(esEntrada: Boolean, dinero: Double, texto: TextView): String{
    if(esEntrada){
        return "+ ${darFormatoDinero(dinero)}"
    }else{
        texto.setTextColor(Color.RED)
        return "- ${darFormatoDinero(dinero)}"
    }
}