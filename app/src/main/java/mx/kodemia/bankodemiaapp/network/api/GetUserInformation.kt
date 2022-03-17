package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.response.user.GetUserFullResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GetUserInformation {

    @Headers(
        "Accept: application/json"
    )
    @GET("users/me/profile")
    suspend fun getUserFullProfile(): Response<GetUserFullResponse>

}