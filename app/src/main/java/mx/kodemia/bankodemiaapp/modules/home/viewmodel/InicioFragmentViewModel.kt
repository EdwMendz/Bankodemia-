package mx.kodemia.bankodemiaapp.modules.home.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.response.error.ErrorResponse
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.data.model.response.user.GetUserFullResponse
import mx.kodemia.bankodemiaapp.network.service.GetUserInformationService
import mx.kodemia.bankodemiaapp.network.service.ListTransactionService
import mx.kodemia.bankodemiaapp.network.service.LogInService
import java.io.IOException

class InicioFragmentViewModel : ViewModel() {

    //Service
    lateinit var serviceListTransaction: ListTransactionService
    lateinit var serviceGetUserInformation: GetUserInformationService

    //LiveDatas
    val listTransactionResponse = MutableLiveData<ListaTransaccionesResponse>()
    val getUserInformationResponse = MutableLiveData<GetUserFullResponse>()
    val errorTrans = MutableLiveData<String>()
    val cargandoTrans = MutableLiveData<Boolean>()
    val errorUser = MutableLiveData<String>()
    val cargandoUser = MutableLiveData<Boolean>()

    //Se lanza el servicio a la vista del Activity o Fragment
    fun onCreate(context: Context){
        //TEMPORAL--------Inicio del Bloque
        serviceLogin = LogInService(context)
        //TEMPORAL---------Final del Bloque

        serviceListTransaction = ListTransactionService(context)
        serviceGetUserInformation = GetUserInformationService(context)
    }

    /*
    Funcion donde se realiza la peticion a la API y la respuesta la recibe ViewModel
    para despues mandarla a su respectiva vista "InicioFragment"
    */
    fun listTransacciones(){
        viewModelScope.launch {
            cargandoTrans.postValue(true)
            val response = serviceListTransaction.ListTransaction()
            try{
                if (response.isSuccessful){
                    listTransactionResponse.postValue(response.body())
                    errorTrans.postValue("")
                }else if(response.code() == 401) {
                    errorTrans.postValue("Unauthorized")
                }else {
                    errorTrans.postValue("Ha ocurrido un error")
                }
                cargandoTrans.postValue(false)
            }catch (io: IOException){
                errorTrans.postValue("Error general")
                cargandoTrans.postValue(false)
            }
        }
    }

    fun getUserFullProfile(){
        viewModelScope.launch {
            cargandoUser.postValue(true)
            val response = serviceGetUserInformation.getUserFull()
            try {
                if(response.isSuccessful){
                    getUserInformationResponse.postValue(response.body())
                }else if (response.code() == 401){
                    Log.e("UserError",response.code().toString())
                } else{
                    errorUser.postValue(response.message())
                }
                cargandoUser.postValue(false)
            }catch (io: IOException){
                errorUser.postValue(io.message)
                cargandoUser.postValue(false)
            }
        }
    }

    //TEMPORAL-----------Inicio del bloque
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
    //TEMPORAL------------Final del bloque

}