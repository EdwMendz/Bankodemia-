package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.response.contacts.GetSingleContactResponse
import retrofit2.Response
import retrofit2.http.*

interface GetSingleContact {

    @Headers(
        "Accept: application/json"
    )
    @GET("contacts/{id}")
    suspend fun getSingleContact(
        @Path("id") id: String
    ): Response<GetSingleContactResponse>

}