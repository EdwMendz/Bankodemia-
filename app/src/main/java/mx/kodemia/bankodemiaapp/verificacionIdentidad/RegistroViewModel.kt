package mx.kodemia.bankodemiaapp.verificacionIdentidad

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.SignUpResquest
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse
import mx.kodemia.bankodemiaapp.network.service.ListTransactionService
import mx.kodemia.bankodemiaapp.network.service.LogInService
import mx.kodemia.bankodemiaapp.network.service.MakeTransactionService
import mx.kodemia.bankodemiaapp.network.service.SignUpService
import java.io.IOException

class RegistroViewModel: ViewModel() {

    //Service
    lateinit var serviceSignUp: SignUpService

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
            val response = serviceSignUp.SigUp(signUpResquest)
            try {
                if (response.isSuccessful){
                    signUpResponse.postValue(response.body())
                }else if(response.code() == 400){
                    error.postValue(response.message())
                }else if(response.code() == 412){
                    error.postValue("Este usuario ya existe")
                }else{
                    error.postValue("Ha ocurrido un error")
                }
                cargando.postValue(false)
            }catch (io: IOException){
                error.postValue("Error de excepcion")
                cargando.postValue(false)
            }
        }
    }

}