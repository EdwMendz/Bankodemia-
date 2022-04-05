package mx.kodemia.bankodemiaapp.data.model.response.listatransacciones

data class ListaTransaccionesResponse(
    val success: Boolean,
    val data: Transacciones,
)
