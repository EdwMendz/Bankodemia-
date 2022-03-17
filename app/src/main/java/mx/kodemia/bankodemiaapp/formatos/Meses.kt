package mx.kodemia.bankodemiaapp.formatos

import android.content.Context
import mx.kodemia.bankodemiaapp.R

fun cambiarNumeroAMes(numeroMes: Int, context: Context): String{
    return when (numeroMes) {
        1 -> context.getString(R.string.enero)
        2 -> context.getString(R.string.febrero)
        3 -> context.getString(R.string.marzo)
        4 -> context.getString(R.string.abril)
        5 -> context.getString(R.string.mayo)
        6 -> context.getString(R.string.junio)
        7 -> context.getString(R.string.julio)
        8 -> context.getString(R.string.agosto)
        9 -> context.getString(R.string.septiembre)
        10 -> context.getString(R.string.octubre)
        11 -> context.getString(R.string.noviembre)
        12 -> context.getString(R.string.diciembre)
        else -> "mes_$numeroMes"
    }
}