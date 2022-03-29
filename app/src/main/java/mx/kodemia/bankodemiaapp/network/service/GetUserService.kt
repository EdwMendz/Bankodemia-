package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.data.model.response.signUp.GetUsersResponse
import mx.kodemia.bankodemiaapp.data.model.response.signUp.ListUserResponse
import mx.kodemia.bankodemiaapp.network.api.GetUser
import mx.kodemia.bankodemiaapp.network.api.ListUsers
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import retrofit2.Response

class GetUserService (context: Context){
    private val retrofit = RetrofitInstance.getRetrofit(context).create(GetUser::class.java)

    suspend fun GetUser(): Response<GetUsersResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.getUser()
            response
        }
    }
}