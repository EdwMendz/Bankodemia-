package mx.kodemia.bankodemiaapp.data.model.response.error

data class ErrorResponse(
    val statusCode: Int,
    val message: MutableList<String> = mutableListOf(),
    val error: String
)
