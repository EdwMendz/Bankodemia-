package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.response.signUp.ListUserResponse
import mx.kodemia.bankodemiaapp.data.model.response.user.ListUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ListUsers {
    @Headers(
        "Accept: application/json"
    )
    @GET("users")
    suspend fun listUsers(): Response<ListUsersResponse>
}