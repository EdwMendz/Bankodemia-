package mx.kodemia.bankodemiaapp.modules.inicioEd.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.network.service.LogInService

class IniciarSesionViewModel : ViewModel() {
    // Servicicio
    private lateinit var serviceLogin: LogInService
//***************** Mutable Live Datas *********************
    //LoginResponse
    val logInResponse = MutableLiveData<LoginResponse>()
    //errores
    val error = MutableLiveData<String>()
    //Cargando
    val cargando = MutableLiveData<Boolean>()
//*************** Fin mutable LiveData **********************

    //Se lanza el servicio a la vista del Activity o Fragment
//    fun pasarContext(context: Context) {
//        serviceLogin = LogInService(context)
//    }

    /*
    Funcion donde se realiza la peticion a la API y la respuesta la recibe ViewModel
    para despues mandarla a su respectiva vista "IniciarSesionView"
    */

    fun logIn(expires_in: String, logInRequest: LogInRequest,context: Context) {
        serviceLogin = LogInService(context)
        //Se lanza la corrutina
        viewModelScope.launch {
            //Se lanza una carga
            cargando.postValue(true)
            //Se obtiene la respuesta
            val respuestaLogin = serviceLogin.logIn(expires_in, logInRequest)
            //Se lanza una doble validacion
            try {
                //Si la respuesta es satisfactoria
                when {
                    respuestaLogin.isSuccessful -> {
                        //Se actualizara el Mutable LoginResponse
                        //Voy a cargar la informacion para que le avise a la vista que esta oks
                        logInResponse.postValue(respuestaLogin.body())
                        error.postValue("")

                        //De acuerdo a la documentacion el error 401 es unautorizaed
                    }
                    respuestaLogin.code() == 401 -> {
                        error.postValue("Usuario no registrado")
                    }
                    else -> {
                        error.postValue("Ha ocurrido un error")
                    }
                }
                cargando.postValue(false)
            } catch (err: Exception) {
                error.postValue(err.localizedMessage)
                cargando.postValue(false)

            }
        }
    }
}


