package mx.kodemia.bankodemiaapp.modules.home.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.response.listatransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.user.GetUserFullResponse
import mx.kodemia.bankodemiaapp.network.service.GetUserInformationService
import mx.kodemia.bankodemiaapp.network.service.ListTransactionService
import java.io.IOException

class InicioFragmentViewModel : ViewModel() {

    //Service
    private lateinit var serviceListTransaction: ListTransactionService
    private lateinit var serviceGetUserInformation: GetUserInformationService

    //LiveDatas
    val listTransactionResponse = MutableLiveData<ListaTransaccionesResponse>()
    val getUserInformationResponse = MutableLiveData<GetUserFullResponse>()
    val errorTrans = MutableLiveData<String>()
    val cargandoTrans = MutableLiveData<Boolean>()
    private val errorUser = MutableLiveData<String>()
    private val cargandoUser = MutableLiveData<Boolean>()

    //Se lanza el servicio a la vista del Activity o Fragment
    fun onCreate(context: Context){
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
            val response = serviceListTransaction.listTransaction()
            try{
                when {
                    response.isSuccessful -> {
                        listTransactionResponse.postValue(response.body())
                        errorTrans.postValue("")
                    }
                    response.code() == 401 -> {
                        errorTrans.postValue("Usuario no autorizado")
                    }
                    else -> {
                        errorTrans.postValue("Ha ocurrido un error")
                    }
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
                when {
                    response.isSuccessful -> {
                        getUserInformationResponse.postValue(response.body())
                    }
                    response.code() == 401 -> {
                        Log.e("UserError",response.code().toString())
                    }
                    else -> {
                        errorUser.postValue(response.message())
                    }
                }
                cargandoUser.postValue(false)
            }catch (io: IOException){
                errorUser.postValue(io.message)
                cargandoUser.postValue(false)
            }
        }
    }

}