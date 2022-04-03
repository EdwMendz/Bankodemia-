package mx.kodemia.bankodemiaapp.data.model.response.user

import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.User

data class UserList(
    val users: MutableList<EspecificUserInfo>
)
