package mx.kodemia.bankodemiaapp.modules.transaction.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.response.contacts.GetSingleContactResponse
import mx.kodemia.bankodemiaapp.network.service.GetSingleContactService
import java.io.IOException

class ObtenerContactoUnicoViewModel: ViewModel() {

    //Service
    private lateinit var serviceGetSingleContact : GetSingleContactService

    //Lives Data
    val getSingleContactResponse = MutableLiveData<GetSingleContactResponse>()
    val error = MutableLiveData<String>()
    val cargando = MutableLiveData<Boolean>()

    fun onCreate(context: Context){
        serviceGetSingleContact = GetSingleContactService(context)
    }

    fun getSingleContact(id: String){
        viewModelScope.launch {
            cargando.postValue(true)
            val response = serviceGetSingleContact.getSingleContact(id)
            try {
                when {
                    response.isSuccessful -> {
                        getSingleContactResponse.postValue(response.body())
                    }
                    response.code() == 400 -> {
                        error.postValue("Se mandaron datos incorrectos")
                    }
                    response.code() == 401 -> {
                        error.postValue("No se tiene permiso para hacer la transaccion")
                    }
                    else -> {
                        error.postValue("Ha ocurrido un error por parte del servidor")
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