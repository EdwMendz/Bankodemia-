package mx.kodemia.bankodemiaapp.data.model.response.signUp

import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.User

data class SignUpResponse(
    val success: Boolean,
    val data: User
)
