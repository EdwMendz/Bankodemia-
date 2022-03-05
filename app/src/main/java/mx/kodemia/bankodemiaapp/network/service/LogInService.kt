package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.data.apiInstance.RetrofitInstance
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.network.api.LogIn
import retrofit2.Response

class LogInService(context: Context) {

    private val retrofit = RetrofitInstance.getRetrofit(context).create(LogIn::class.java)

    suspend fun LogIn(expires_in: String, logInRequest: LogInRequest): Response<LoginResponse> {
        return withContext(Dispatchers.IO){
            val response = retrofit.logIn(expires_in, logInRequest)
            response
        }
    }

}