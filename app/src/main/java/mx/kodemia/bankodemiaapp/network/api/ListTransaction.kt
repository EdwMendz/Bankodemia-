package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ListTransaction {

    @Headers(
        "Accept: application/json"
    )
    @GET("transactions/me")
    suspend fun listTransaction(): Response<ListaTransaccionesResponse>

}