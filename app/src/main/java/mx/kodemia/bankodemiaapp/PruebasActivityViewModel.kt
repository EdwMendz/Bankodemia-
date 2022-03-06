package mx.kodemia.bankodemiaapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.request.MakeTransactionRequest
import mx.kodemia.bankodemiaapp.data.model.request.SignUpResquest
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.data.model.response.makeTransaction.MakeTransactionResponse
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse
import mx.kodemia.bankodemiaapp.network.service.ListTransactionService
import mx.kodemia.bankodemiaapp.network.service.LogInService
import mx.kodemia.bankodemiaapp.network.service.MakeTransactionService
import mx.kodemia.bankodemiaapp.network.service.SignUpService

class PruebasActivityViewModel: ViewModel() {

    //Service
    lateinit var serviceSignUp: SignUpService
    lateinit var serviceLogin: LogInService
    lateinit var serviceMakeTransaction: MakeTransactionService
    lateinit var serviceListTransaction: ListTransactionService

    //LiveDatas
    val signUpResponse = MutableLiveData<SignUpResponse>()
    val logInResponse = MutableLiveData<LoginResponse>()
    val makeTransactionResponse = MutableLiveData<MakeTransactionResponse>()
    val listTransactionResponse = MutableLiveData<ListaTransaccionesResponse>()

    fun onCreate(context: Context){
        serviceSignUp = SignUpService(context)
        serviceLogin = LogInService(context)
        serviceMakeTransaction = MakeTransactionService(context)
        serviceListTransaction = ListTransactionService(context)
    }

    //Funcion
    fun signUp(signUpResquest: SignUpResquest){
        viewModelScope.launch {
            val response = serviceSignUp.SigUp(signUpResquest)
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

    fun makeTransaction(makeTransactionRequest: MakeTransactionRequest){
        viewModelScope.launch {
            val response = serviceMakeTransaction.MakeTransaction(makeTransactionRequest)
            if(response.isSuccessful){
                makeTransactionResponse.postValue(response.body())
            }else if(response.code() == 400) {
                Log.e("MAKETRANSERROR", "Mala peticion")
            }else if(response.code() == 401){
                Log.e("MAKETRANSERROR", "Unauthorized")
            }else if(response.code() == 402){
                Log.e("MAKETRANSERROR","No tienes feria")
            }else if(response.code() == 412){
                Log.e("MAKETRANSERROR","Saldo insuficiente")
            }else{
                Log.e("MAKETRANSERROR",response.code().toString())
            }
        }
    }

    fun listTransacciones(){
        viewModelScope.launch {
            val response = serviceListTransaction.ListTransaction()
            if (response.isSuccessful){
                listTransactionResponse.postValue(response.body())
            }else {
                Log.e("LISTTRANSERROR",response.code().toString())
            }
        }
    }

}