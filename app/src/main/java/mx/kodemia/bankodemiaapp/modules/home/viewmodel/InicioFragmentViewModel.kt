package mx.kodemia.bankodemiaapp.modules.home.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.network.service.ListTransactionService
import mx.kodemia.bankodemiaapp.network.service.LogInService

class InicioFragmentViewModel : ViewModel() {

    //Service
    lateinit var serviceListTransaction: ListTransactionService

    //LiveDatas
    val listTransactionResponse = MutableLiveData<ListaTransaccionesResponse>()

    fun onCreate(context: Context){
        //TEMPORAL
        serviceLogin = LogInService(context)

        serviceListTransaction = ListTransactionService(context)
    }

    /*
    Funcion donde se realiza la peticion a la API y la respuesta la recibe ViewModel
    para despues mandarla a su respectiva vista "InicioFragment"
    */
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

    //TEMPORAL
    lateinit var serviceLogin: LogInService
    val logInResponse = MutableLiveData<LoginResponse>()
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