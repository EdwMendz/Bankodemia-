package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.data.model.request.UpdateContactRequest
import mx.kodemia.bankodemiaapp.data.model.response.contacts.ActionsContactResponse
import mx.kodemia.bankodemiaapp.network.api.UpdateContact
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import retrofit2.Response

class UpdateContactService(context: Context) {

    private val retrofit = RetrofitInstance.getRetrofit(context).create(UpdateContact::class.java)

    suspend fun updateContact(id: String, updateContactRequest: UpdateContactRequest): Response<ActionsContactResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.updateContact(id,updateContactRequest)
            response
        }
    }

}