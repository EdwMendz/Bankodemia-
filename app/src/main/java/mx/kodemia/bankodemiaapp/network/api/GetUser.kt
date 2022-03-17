package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.response.signUp.GetUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GetUser {
    @Headers(
        "Accept: application/json"
    )
    @GET("users/{id}")
    suspend fun getUser(): Response<GetUsersResponse>
}