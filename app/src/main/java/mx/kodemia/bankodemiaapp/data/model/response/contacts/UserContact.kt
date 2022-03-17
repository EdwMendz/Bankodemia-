package mx.kodemia.bankodemiaapp.data.model.response.contacts

import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.User

data class UserContact(
    val shortName : String,
    val owner : User,
    val user : User
)
