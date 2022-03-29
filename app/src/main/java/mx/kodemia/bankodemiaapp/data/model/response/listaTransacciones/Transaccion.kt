package mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones


data class Transaccion(
    val amount: Double,
    val type: String,
    val concept: String,
    val created_at: String,
    val issuer: User,
    val destinationUser: User,
    val isIncome: Boolean,
    val _id: String
)
