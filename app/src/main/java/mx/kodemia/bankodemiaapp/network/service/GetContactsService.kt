package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.data.model.response.contacts.AllContacts
import mx.kodemia.bankodemiaapp.network.api.GetContacts
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import retrofit2.Response

class GetContactsService(context: Context) {

    private val retrofit = RetrofitInstance.getRetrofit(context).create(GetContacts::class.java)

    suspend fun getContacts(): Response<AllContacts> {
        return withContext(Dispatchers.IO){
            val response = retrofit.getContacts()
            response
        }
    }

}