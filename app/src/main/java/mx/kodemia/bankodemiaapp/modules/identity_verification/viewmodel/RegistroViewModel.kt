package mx.kodemia.bankodemiaapp.modules.identity_verification.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.SignUpResquest
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse
import mx.kodemia.bankodemiaapp.network.service.SignUpService
import java.io.IOException

class RegistroViewModel: ViewModel() {

    //Service
    private lateinit var serviceSignUp: SignUpService

    //LiveDatas
    val signUpResponse = MutableLiveData<SignUpResponse>()
    val error = MutableLiveData<String>()
    val cargando = MutableLiveData<Boolean>()

    fun onCreate(context: Context){
        serviceSignUp = SignUpService(context)
    }

    //Funcion
    fun signUp(signUpResquest: SignUpResquest){
        viewModelScope.launch {
            cargando.postValue(true)
            val response = serviceSignUp.sigUp(signUpResquest)
            try {
                when {
                    response.isSuccessful -> {
                        signUpResponse.postValue(response.body())
                    }
                    response.code() == 400 -> {
                        error.postValue("Se ha enviado informacion incorrecta, favor de revisar sus datos e intentarlo nuevamente")
                    }
                    response.code() == 412 -> {
                        error.postValue("Este usuario ya existe")
                    }
                    else -> {
                        error.postValue("Ha ocurrido un error")
                    }
                }
                cargando.postValue(false)
            }catch (io: IOException){
                error.postValue("Error de excepcion")
                cargando.postValue(false)
            }
        }
    }

}