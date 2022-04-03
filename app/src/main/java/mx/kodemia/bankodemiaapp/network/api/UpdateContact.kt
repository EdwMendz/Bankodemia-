package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.request.UpdateContactRequest
import mx.kodemia.bankodemiaapp.data.model.response.contacts.ActionsContactResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.Path

interface UpdateContact {

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @PATCH("contacts/{id}")
    suspend fun updateContact(
        @Path("id") id: String,
        @Body updateContactRequest: UpdateContactRequest
    ): Response<ActionsContactResponse>

}