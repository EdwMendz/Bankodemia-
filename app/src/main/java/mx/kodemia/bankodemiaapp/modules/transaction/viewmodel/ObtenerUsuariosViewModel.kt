package mx.kodemia.bankodemiaapp.modules.transaction.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.response.user.ListUsersResponse
import mx.kodemia.bankodemiaapp.network.service.ListUsersService
import java.io.IOException

class ObtenerUsuariosViewModel: ViewModel() {

    //Service
    lateinit var serviceGetUsers : ListUsersService

    val getUsersResponse = MutableLiveData<ListUsersResponse>()
    val cargando = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun onCreate(context: Context){
        serviceGetUsers = ListUsersService(context)
    }

    fun getUsers(){
        viewModelScope.launch {
            cargando.postValue(true)
            val response = serviceGetUsers.ListUser()
            try {
                if(response.isSuccessful){
                    getUsersResponse.postValue(response.body())
                }else if(response.code() == 400) {
                    error.postValue("Se mandaron datos incorrectos")
                }else if(response.code() == 401){
                    error.postValue("No se tiene permiso para hacer la transaccion")
                }else{
                    error.postValue("Ha ocurrido un error por parte del servidor")
                }
                cargando.postValue(false)
            }catch (io: IOException){
                error.postValue("Error de excepcion")
                cargando.postValue(false)
            }
        }
    }

}