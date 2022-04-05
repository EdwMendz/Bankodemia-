package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.network.api.ListTransaction
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import mx.kodemia.bankodemiaapp.data.model.response.listatransacciones.ListaTransaccionesResponse
import retrofit2.Response

class ListTransactionService(context: Context) {

    private val retrofit = RetrofitInstance.getRetrofit(context).create(ListTransaction::class.java)

    suspend fun listTransaction(): Response<ListaTransaccionesResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.listTransaction()
            response
        }
    }

}