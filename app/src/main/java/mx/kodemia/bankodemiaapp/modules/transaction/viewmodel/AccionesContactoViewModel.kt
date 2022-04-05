package mx.kodemia.bankodemiaapp.modules.transaction.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.UpdateContactRequest
import mx.kodemia.bankodemiaapp.data.model.response.contacts.ActionsContactResponse
import mx.kodemia.bankodemiaapp.network.service.DeleteContactService
import mx.kodemia.bankodemiaapp.network.service.UpdateContactService
import java.io.IOException

class AccionesContactoViewModel: ViewModel() {

    //Service
    private lateinit var serviceDeleteContact : DeleteContactService
    private lateinit var serviceUpdateContact : UpdateContactService

    val deleteContactResponse = MutableLiveData<ActionsContactResponse>()
    val updateContactResponse = MutableLiveData<ActionsContactResponse>()
    val cargandoDelete = MutableLiveData<Boolean>()
    val errorDelete = MutableLiveData<String>()
    val cargandoUpdate = MutableLiveData<Boolean>()
    val errorUpdate = MutableLiveData<String>()

    fun onCreate(context: Context){
        serviceDeleteContact = DeleteContactService(context)
        serviceUpdateContact = UpdateContactService(context)
    }

    fun deleteContact(id: String){
        viewModelScope.launch {
            cargandoDelete.postValue(true)
            val response = serviceDeleteContact.deleteContact(id)
            try {
                when {
                    response.isSuccessful -> {
                        deleteContactResponse.postValue(response.body())
                    }
                    response.code() == 400 -> {
                        errorDelete.postValue("Se mandaron datos incorrectos")
                    }
                    response.code() == 401 -> {
                        errorDelete.postValue("No se tiene permiso para hacer la transaccion")
                    }
                    else -> {
                        errorDelete.postValue("Ha ocurrido un error por parte del servidor")
                    }
                }
                cargandoDelete.postValue(false)
            }catch (io: IOException){
                errorDelete.postValue("Error de excepcion")
                cargandoDelete.postValue(false)
            }
        }
    }

    fun updateContact(id: String, updateContactRequest: UpdateContactRequest){
        viewModelScope.launch {
            cargandoDelete.postValue(true)
            val response = serviceUpdateContact.updateContact(id,updateContactRequest)
            try {
                when {
                    response.isSuccessful -> {
                        updateContactResponse.postValue(response.body())
                    }
                    response.code() == 400 -> {
                        errorUpdate.postValue("Se mandaron datos incorrectos")
                    }
                    response.code() == 401 -> {
                        errorUpdate.postValue("No se tiene permiso para hacer la transaccion")
                    }
                    else -> {
                        errorUpdate.postValue("Ha ocurrido un error por parte del servidor")
                    }
                }
                cargandoUpdate.postValue(false)
            }catch (io: IOException){
                errorUpdate.postValue("Error de excepcion")
                cargandoUpdate.postValue(false)
            }
        }
    }

}