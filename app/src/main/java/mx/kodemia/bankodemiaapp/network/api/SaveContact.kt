package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.request.SaveContactRequest
import mx.kodemia.bankodemiaapp.data.model.response.contacts.SaveContactResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SaveContact {
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("contacts")
    suspend fun saveContact(
        @Body saveContactRequest: SaveContactRequest
    ): Response<SaveContactResponse>
}