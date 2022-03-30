package mx.kodemia.bankodemiaapp.network.service

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.kodemia.bankodemiaapp.network.apiInstance.RetrofitInstance
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.request.SignUpResquest
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.network.api.LogIn
import retrofit2.Response

class LogInService(context: Context) {

    //Se instancia el servicio de retrofit con la peticion de LogIn
    private val retrofit = RetrofitInstance.getRetrofit(context).create(LogIn::class.java)

    //Se crea la funcion para mandar la peticion con los parametros necesarios para realizarla
    //Con un tipo de retorno del modelo de la respuesta
    suspend fun LogIn(expires_in: String, logInRequest: SignUpResquest): Response<LoginResponse> {
        //Se regresa por corrutina se abre un hilo distinto
        return withContext(Dispatchers.IO){
            val response = retrofit.logIn(expires_in, logInRequest)
            response
        }
    }

}