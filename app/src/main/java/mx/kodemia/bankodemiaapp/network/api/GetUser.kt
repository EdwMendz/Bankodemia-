package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.response.signUp.GetUsersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GetUser {
    @Headers(
        "Accept: application/json"
    )
    @GET("users/{id}")
    suspend fun getUser(
        @Query("id") id: String,
    ): Response<GetUsersResponse>
}