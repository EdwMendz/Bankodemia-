package mx.kodemia.bankodemiaapp.data.model.response.maketransaction

import mx.kodemia.bankodemiaapp.data.model.response.listatransacciones.Transaccion

data class Data(
    val transaction: Transaccion,
    val finalBalance: Double
)
