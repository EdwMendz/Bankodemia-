package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.response.contacts.ActionsContactResponse
import retrofit2.Response
import retrofit2.http.*

interface DeleteContact {

    @Headers(
        "Accept: application/json"
    )
    @DELETE("contacts/{id}")
    suspend fun deleteContact(
        @Path("id") id: String
    ): Response<ActionsContactResponse>

}