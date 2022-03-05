package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.request.SignUpResquest
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUp {

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("users")
    suspend fun signUp(
        @Body signUpResquest: SignUpResquest
    ): Response<SignUpResponse>

}