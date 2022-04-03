package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.data.model.request.SaveContactRequest
import mx.kodemia.bankodemiaapp.data.model.response.contacts.SaveContactResponse
import mx.kodemia.bankodemiaapp.network.api.SaveContact
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import retrofit2.Response

class SaveContactService (context: Context) {

    private val retrofit = RetrofitInstance.getRetrofit(context).create(SaveContact::class.java)

    suspend fun saveContact(saveContactRequest: SaveContactRequest): Response<SaveContactResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.saveContact(saveContactRequest)
            response
        }
    }
}