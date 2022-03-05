package mx.kodemia.bankodemiaapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.request.SignUpResquest
import mx.kodemia.bankodemiaapp.data.model.response.error.ErrorMessageResponse
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse
import mx.kodemia.bankodemiaapp.network.service.LogInService
import mx.kodemia.bankodemiaapp.network.service.SignUpService

class PruebasActivityViewModel: ViewModel() {

    //Service
    lateinit var service: SignUpService
    lateinit var serviceLogin: LogInService

    //LiveDatas
    val signUpResponse = MutableLiveData<SignUpResponse>()
    val logInResponse = MutableLiveData<LoginResponse>()

    fun onCreate(context: Context){
        service = SignUpService(context)
        serviceLogin = LogInService(context)
    }

    //Funcion
    fun signUp(signUpResquest: SignUpResquest){
        viewModelScope.launch {
            val response = service.SigUp(signUpResquest)
            if (response.isSuccessful){
                signUpResponse.postValue(response.body())
            }else if(response.code() == 400){
                Log.e("SIGNUPERROR",response.message())
            }else if(response.code() == 412){
                Log.e("SIGNUPERROR","User already exists")
            }
        }
    }

    fun logIn(expires_in: String, LoginRequest: LogInRequest){
        viewModelScope.launch {
            val response = serviceLogin.LogIn(expires_in,LoginRequest)
            if(response.isSuccessful){
                logInResponse.postValue(response.body())
            }else if(response.code() == 401){
                Log.e("LOGINERROR","Unauthorized")
            }
        }
    }

}