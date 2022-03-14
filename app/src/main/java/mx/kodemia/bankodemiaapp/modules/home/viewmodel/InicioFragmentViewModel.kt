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
import mx.kodemia.bankodemiaapp.data.model.response.user.GetUserFullResponse
import mx.kodemia.bankodemiaapp.network.service.GetUserInformationService
import mx.kodemia.bankodemiaapp.network.service.ListTransactionService
import mx.kodemia.bankodemiaapp.network.service.LogInService

class InicioFragmentViewModel : ViewModel() {

    //Service
    lateinit var serviceListTransaction: ListTransactionService
    lateinit var serviceGetUserInformation: GetUserInformationService

    //LiveDatas
    val listTransactionResponse = MutableLiveData<ListaTransaccionesResponse>()
    val getUserInformationResponse = MutableLiveData<GetUserFullResponse>()

    //Se lanza el servicio a la vista del Activity o Fragment
    fun onCreate(context: Context){
        //TEMPORAL
        serviceLogin = LogInService(context)

        serviceListTransaction = ListTransactionService(context)
        serviceGetUserInformation = GetUserInformationService(context)
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

    fun getUserFullProfile(){
        viewModelScope.launch {
            val response = serviceGetUserInformation.getUserFull()
            if(response.isSuccessful){
                getUserInformationResponse.postValue(response.body())
            }else{
                Log.e("USERSERROR",response.code().toString())
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