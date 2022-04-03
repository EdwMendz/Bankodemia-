package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.response.contacts.AllContacts
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GetContacts {

    @Headers(
        "Accept: application/json"
    )
    @GET("contacts")
    suspend fun getContacts(): Response<AllContacts>

}