package mx.kodemia.bankodemiaapp.modules.inicioEd.viewModel

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.response.error.ErrorResponse
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.network.service.LogInService

class IniciarSesionViewModel() : ViewModel() {
    // Servicicio
    lateinit var serviceLogin: LogInService
//***************** Mutable Live Datas *********************
    //LoginResponse
    val logInResponse = MutableLiveData<LoginResponse>()
    //LoginRequest
    val logInRequest = MutableLiveData<LogInRequest>()
    //errores
    val error = MutableLiveData<String>()
    //Cargando
    val cargando = MutableLiveData<Boolean>()
//*************** Fin mutable LiveData **********************

    //Se lanza el servicio a la vista del Activity o Fragment
    fun pasarContext(context: Context) {
        serviceLogin = LogInService(context)
    }

    /*
    Funcion donde se realiza la peticion a la API y la respuesta la recibe ViewModel
    para despues mandarla a su respectiva vista "IniciarSesionView"
    */

    fun logIn(expires_in: String, logInRequest: LogInRequest) {
        //Se lanza la corrutina
        viewModelScope.launch {
            //Se lanza una carga
            cargando.postValue(true)
            //Se obtiene la respuesta
            val respuestaLogin = serviceLogin.LogIn(expires_in, logInRequest)
            //Se lanza una doble validacion
            try {
                //Si la respuesta es satisfactoria
                if (respuestaLogin.isSuccessful) {
                    //Se actualizara el Mutable LoginResponse
                    //Voy a cargar la informacion para que le avise a la vista que esta oks
                    logInResponse.postValue(respuestaLogin.body())
                    //De acuerdo a la documentacion el error 401 es unautorizaed
                } else if (respuestaLogin.code() == 401) {
                    Log.e("LoginError", "Unauthorizaed")
                }
                cargando.postValue(true)
            } catch (err: Exception) {
                error.postValue(err.localizedMessage)
                cargando.postValue(false)

            }
        }
    }

}


