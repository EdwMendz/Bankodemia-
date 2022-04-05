package mx.kodemia.bankodemiaapp.data.model.response.signUp

import mx.kodemia.bankodemiaapp.data.model.response.listatransacciones.User

data class UserMutable(
    val user : MutableList<User>
)
