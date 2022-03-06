package mx.kodemia.bankodemiaapp.data.model.request

data class SignUpResquest(
    val email: String,
    val name: String,
    val lastName: String,
    val occupation: String,
    val birthDate: String,
    val password: String,
    val phone: String,
    val identityImage: String,
    val identityImageType: String
)
