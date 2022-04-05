package mx.kodemia.bankodemiaapp.data.model.response.signUp

import mx.kodemia.bankodemiaapp.data.model.response.listatransacciones.User

data class SignUpResponse(
    val success: Boolean,
    val data: User
)
