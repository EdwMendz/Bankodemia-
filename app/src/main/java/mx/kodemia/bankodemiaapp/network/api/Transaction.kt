package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransacciones
import retrofit2.http.GET
import retrofit2.http.Headers

interface Transaction {

    val shared : SharedPreferencesInstance

    @Headers("Authorization: Bearer")
    @GET("transactions/me")
    suspend fun obtenerTransacciones(

    ): ListaTransacciones
}