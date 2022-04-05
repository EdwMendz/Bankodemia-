package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SearchUserResponse
import mx.kodemia.bankodemiaapp.network.api.SearchUsers
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import retrofit2.Response

class SearchUsers (context: Context){
    //Se instancia el servicio de retrofit con la peticion de LogIn
    private val retrofit = RetrofitInstance.getRetrofit(context).create(SearchUsers::class.java)

    //Se crea la funcion para mandar la peticion con los parametros necesarios para realizarla
    //Con un tipo de retorno del modelo de la respuesta
    suspend fun SearchUsers(query: String): Response<SearchUserResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.searchUsers(query)
            response
        }
    }
}