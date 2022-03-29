package mx.kodemia.bankodemiaapp.formatos

import java.text.SimpleDateFormat
import java.util.*

fun darFormatoFechaActual(): String{
    val enteroFecha = System.currentTimeMillis()
    return SimpleDateFormat("d MMMM yyyy", Locale.getDefault()).format(Date(enteroFecha)).uppercase()
}