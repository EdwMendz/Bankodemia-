package mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones

data class User(
    val email: String,
    val name: String,
    val lastName: String,
    val occupation: String,
    val birthDate: String,
    val password: String,
    val phone: String,
    val isPhoneVerified: Boolean,
    val identityImage: String,
    val identityImageType: String,
    val _id: String
)
