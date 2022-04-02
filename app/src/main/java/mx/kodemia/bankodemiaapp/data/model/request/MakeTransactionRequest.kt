package mx.kodemia.bankodemiaapp.data.model.request

data class MakeTransactionRequest(
    val amount: Double,
    val type: String,
    val destinationUser: String?,
    val concept: String
)
