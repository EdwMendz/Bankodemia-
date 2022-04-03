package mx.kodemia.bankodemiaapp.data.model.response.user

import mx.kodemia.bankodemiaapp.data.model.response.contacts.UserInfo

data class UserInformation(
    val balance: Double,
    val user: UserInfo
)
