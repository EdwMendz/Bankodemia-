package mx.kodemia.bankodemiaapp.data.model.request

import mx.kodemia.bankodemiaapp.data.model.response.makeTransaction.Type

data class MakeTransactionRequest(
    val amount: Double,
    val type: String,
    val destinationUser: String?,
    val concept: String
)
