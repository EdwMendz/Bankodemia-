package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.data.model.response.contacts.ActionsContactResponse
import mx.kodemia.bankodemiaapp.network.api.DeleteContact
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import retrofit2.Response
import java.lang.Exception

class DeleteContactService(context: Context) {

    private val retrofit = RetrofitInstance.getRetrofit(context).create(DeleteContact::class.java)

    suspend fun deleteContact(id: String): Response<ActionsContactResponse> {

        return withContext(Dispatchers.IO) {
            val response = retrofit.deleteContact(id)
            response
        }

    }

}