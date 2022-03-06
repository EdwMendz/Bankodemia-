package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import mx.kodemia.bankodemiaapp.data.model.request.MakeTransactionRequest
import mx.kodemia.bankodemiaapp.data.model.response.makeTransaction.MakeTransactionResponse
import mx.kodemia.bankodemiaapp.network.api.MakeTransaction
import retrofit2.Response

class MakeTransactionService(context: Context) {

    private val retrofit = RetrofitInstance.getRetrofit(context).create(MakeTransaction::class.java)

    suspend fun MakeTransaction(makeTransactionRequest: MakeTransactionRequest): Response<MakeTransactionResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.makeTransaction(makeTransactionRequest)
            response
        }
    }

}