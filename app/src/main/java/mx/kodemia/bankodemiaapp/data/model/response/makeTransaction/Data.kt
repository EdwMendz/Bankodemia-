package mx.kodemia.bankodemiaapp.data.model.response.makeTransaction

import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.User

data class Data(
    val transaction: Transaccion,
    val finalBalance: Double
)
