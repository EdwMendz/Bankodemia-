package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.data.model.response.contacts.AllContacts
import mx.kodemia.bankodemiaapp.data.model.response.contacts.GetSingleContactResponse
import mx.kodemia.bankodemiaapp.network.api.GetContacts
import mx.kodemia.bankodemiaapp.network.api.GetSingleContact
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import retrofit2.Response

class GetSingleContactService(context: Context) {

    private val retrofit = RetrofitInstance.getRetrofit(context).create(GetSingleContact::class.java)

    suspend fun getSingleContact(id: String): Response<GetSingleContactResponse> {

        return withContext(Dispatchers.IO){
            val response = retrofit.getSingleContact(id)
            response
        }
    }

}