package mx.kodemia.bankodemiaapp.modules.home.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.response.error.ErrorMessageResponse
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.modules.home.view.HomeActivity
import mx.kodemia.bankodemiaapp.network.service.LogInService

class IniciarSesionViewModel(context: Context) : ViewModel() {
    // Servicicio
    private val serviceLogin = LogInService(context)

    //LoginResponse
    private val logInResponse = MutableLiveData<LoginResponse>()

    //errores
    val error = MutableLiveData<ErrorMessageResponse>()

    //Cargando
    private val cargando = MutableLiveData<Boolean>()

    fun logIn(expires_in: String, logInRequest: LogInRequest, activity: Activity) {
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
                    lanzarActivity(activity)

                    //De acuerdo a la documentacion el error 401 es unautorizaed
                } else if (respuestaLogin.code() == 401) {
                    Log.e("LoginError", "Unauthorizaed")
                }
                cargando.postValue(true)
            } catch (err : Exception) {
              //  error.postValue(err.localizedMessage)
                cargando.postValue(false)
            }
        }
    }

    //Si la respuesta es correcta se inicializa activityHome
    private fun lanzarActivity(activity: Activity) {
        val intent = Intent(activity, HomeActivity::class.java)
        activity.startActivity(intent)
    }
}


