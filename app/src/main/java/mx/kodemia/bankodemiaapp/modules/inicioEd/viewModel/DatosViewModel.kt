package mx.kodemia.bankodemiaapp.modules.inicioEd.viewModel

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.request.SignUpResquest
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse
import mx.kodemia.bankodemiaapp.modules.home.view.HomeActivity
import mx.kodemia.bankodemiaapp.modules.inicioEd.view.TelefonoView
import mx.kodemia.bankodemiaapp.network.service.LogInService
import mx.kodemia.bankodemiaapp.network.service.SignUpService

class DatosViewModel() : ViewModel() {
    // Servicicio
    lateinit var serviceLogin: LogInService

    //SignUpResponse
    val signUpResponse = MutableLiveData<SignUpResponse>()

    //SignUpResquest
    val SignUpResquest = MutableLiveData<SignUpResquest>()


    //Cargando
    val cargando = MutableLiveData<Boolean>()


    fun logIn(signUpResquest: SignUpResquest,expires_in:String) {
        //Se lanza la corrutina
        viewModelScope.launch {
            //Se lanza la carga
            cargando.postValue(true)
            //Se obtiene la respuesta
            val response = serviceLogin.LogIn(expires_in,signUpResquest)
            //Se lanza una doble validacion
            try {
                if (response.isSuccessful) {
                    //Se actualizara el Mutable signUpResponse
                    //Voy a cargar la informacion para que le avise a la vista que esta oks
                    signUpResponse.postValue(response.body())
                    //Si la respuesta es oks se lanza la ActivityTelefono
                    //Los siguientes errores se encuentran en la documentacion
                } else if (response.code() == 400) {
                    Log.e("SIGNUPERROR", response.message())
                } else if (response.code() == 412) {
                    Log.e("SIGNUPERROR", "User already exists")
                }

            } catch (err: Exception) {
                //error.postValue(err.localizedMessage)
                cargando.postValue(false)
            }
        }
    }





        //Si la respuesta es correcta se inicializa activityHome
        private fun lanzarActivity(activity: Activity) {
            val intent = Intent(activity, TelefonoView::class.java)
            activity.startActivity(intent)
    }
}


//
//fun logIn(expires_in: String, logInRequest: LogInRequest, activity: Activity) {
//    //Se lanza la corrutina
//    viewModelScope.launch {
//        //Se lanza la carga del progresbar
//        cargando.postValue(true)
//        //Se obtiene la respuesta
//        val respuestaLogin = serviceLogin.LogIn(expires_in, logInRequest)
//        //Se lanza una doble validacion
//        try {
//            //Si la respuesta es satisfactoria
//            if (respuestaLogin.isSuccessful) {
//                //Se actualizara el Mutable LoginResponse
//                //Voy a cargar la informacion para que le avise a la vista que esta oks
//                logInResponse.postValue(respuestaLogin.body())
//                lanzarActivity(activity)
//
//                //De acuerdo a la documentacion el error 401 es unautorizaed
//            } else if (respuestaLogin.code() == 401) {
//                Log.e("LoginError", "Unauthorizaed")
//            }
//            cargando.postValue(true)
//        } catch (err : Exception) {
//            //  error.postValue(err.localizedMessage)
//            cargando.postValue(false)
//        }
//    }
//}