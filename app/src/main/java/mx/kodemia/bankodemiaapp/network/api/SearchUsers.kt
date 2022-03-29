package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SearchUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchUsers {
    @Headers(
        "Accept: application/json"
    )
    @GET("users/search")
    suspend fun searchUsers(
        @Query("query") query: String,
    ): Response<SearchUserResponse>
}