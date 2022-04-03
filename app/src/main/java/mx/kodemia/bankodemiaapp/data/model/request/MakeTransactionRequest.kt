package mx.kodemia.bankodemiaapp.data.model.request

data class MakeTransactionRequest(
    val amount: Int,
    val type: String,
    val destinationUser: String?,
    val concept: String
)
