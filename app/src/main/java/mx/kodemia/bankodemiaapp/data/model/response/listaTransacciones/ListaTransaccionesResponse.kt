package mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones

data class ListaTransaccionesResponse(
    val success: Boolean,
    val data: Transacciones,
)
