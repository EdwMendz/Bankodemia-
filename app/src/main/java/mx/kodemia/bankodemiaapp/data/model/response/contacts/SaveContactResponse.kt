package mx.kodemia.bankodemiaapp.data.model.response.contacts

import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.User

data class SaveContactResponse(
    val success : Boolean,
    val data : UserContact,
)
