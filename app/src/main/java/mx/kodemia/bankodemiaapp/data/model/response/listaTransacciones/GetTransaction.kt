package mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones

data class GetTransaction(
    val success : Boolean,
    val data : Transaccion,
    val destinationUser : User
)
