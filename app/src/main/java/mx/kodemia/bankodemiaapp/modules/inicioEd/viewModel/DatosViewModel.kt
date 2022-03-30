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

class DatosViewModel()  {

        //Si la respuesta es correcta se inicializa activityHome
        private fun lanzarActivity(activity: Activity) {
            val intent = Intent(activity, TelefonoView::class.java)
            activity.startActivity(intent)
    }
}
