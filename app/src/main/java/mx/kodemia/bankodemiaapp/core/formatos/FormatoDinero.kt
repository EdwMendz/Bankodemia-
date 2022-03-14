package mx.kodemia.bankodemiaapp.core.formatos

import java.text.NumberFormat
import java.util.*

fun darFormatoDinero(dinero: Double): String{
    val formatoNumero = NumberFormat.getCurrencyInstance()
    return formatoNumero.format(dinero)
}