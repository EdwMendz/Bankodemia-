package mx.kodemia.bankodemiaapp.modules.transaction.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.SaveContactRequest
import mx.kodemia.bankodemiaapp.data.model.response.contacts.SaveContactResponse
import mx.kodemia.bankodemiaapp.network.service.SaveContactService
import java.io.IOException

class AgregarContactoViewModel: ViewModel() {

    //Service
    private lateinit var serviceSaveContact : SaveContactService

    val saveContactResponse = MutableLiveData<SaveContactResponse>()
    val cargando = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun onCreate(context: Context){
        serviceSaveContact = SaveContactService(context)
    }

    fun saveContact(saveContactRequest: SaveContactRequest){
        viewModelScope.launch {
            cargando.postValue(true)
            val response = serviceSaveContact.saveContact(saveContactRequest)
            try {
                when {
                    response.isSuccessful -> {
                        saveContactResponse.postValue(response.body())
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