package mx.kodemia.bankodemiaapp.network.api

import mx.kodemia.bankodemiaapp.data.model.request.MakeTransactionRequest
import mx.kodemia.bankodemiaapp.data.model.response.makeTransaction.MakeTransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface MakeTransaction {

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("transactions")
    suspend fun makeTransaction(
        @Body makeTransactionRequest: MakeTransactionRequest
    ): Response<MakeTransactionResponse>
}