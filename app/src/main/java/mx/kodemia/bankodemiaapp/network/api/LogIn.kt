package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface LogIn {

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("auth/login")
    suspend fun logIn(
        @Query("expires_in") expires_in: String,
        @Body logInRequest: LogInRequest
    ): Response<LoginResponse>

}