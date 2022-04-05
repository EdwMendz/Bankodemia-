package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.data.model.response.user.ListUsersResponse
import mx.kodemia.bankodemiaapp.network.api.ListUsers
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import retrofit2.Response

class ListUsersService(context: Context) {
    //Se instancia el servicio de retrofit con la peticion de LogIn
    private val retrofit = RetrofitInstance.getRetrofit(context).create(ListUsers::class.java)

    //Se crea la funcion para mandar la peticion con los parametros necesarios para realizarla
    //Con un tipo de retorno del modelo de la respuesta
    suspend fun ListUser(): Response<ListUsersResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.listUsers()
            response
        }
    }
}