package mx.kodemia.bankodemiaapp.formatos

import android.content.Context
import mx.kodemia.bankodemiaapp.R

fun cambiarMesANumero(Mes: String, context: Context): String{
    return when (Mes) {
        context.getString(R.string.january) -> "01"
        context.getString(R.string.february) -> "02"
        context.getString(R.string.march) -> "03"
        context.getString(R.string.april) -> "04"
        context.getString(R.string.may) -> "05"
        context.getString(R.string.june) -> "06"
        context.getString(R.string.july) -> "07"
        context.getString(R.string.august) -> "08"
        context.getString(R.string.september) -> "09"
        context.getString(R.string.october) -> "10"
        context.getString(R.string.november) -> "11"
        context.getString(R.string.december) -> "12"
        else -> "01"
    }
}