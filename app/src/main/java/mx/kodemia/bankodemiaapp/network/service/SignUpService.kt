package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse
import mx.kodemia.bankodemiaapp.data.model.request.SignUpResquest
import mx.kodemia.bankodemiaapp.network.api.SignUp
import retrofit2.Response

class SignUpService (context: Context){

    private val retrofit = RetrofitInstance.getRetrofit(context).create(SignUp::class.java)

    suspend fun SigUp(signUpResquest: SignUpResquest): Response<SignUpResponse>{
        return withContext(Dispatchers.IO){
            val response = retrofit.signUp(signUpResquest)
            response
        }
    }
}