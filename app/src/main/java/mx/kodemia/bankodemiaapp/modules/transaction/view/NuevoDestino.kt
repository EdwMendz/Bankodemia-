package mx.kodemia.bankodemiaapp.modules.transaction.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.DialogExpiredToken
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.data.model.request.SaveContactRequest
import mx.kodemia.bankodemiaapp.data.model.response.contacts.SaveContactResponse
import mx.kodemia.bankodemiaapp.data.model.response.makeTransaction.MakeTransactionResponse
import mx.kodemia.bankodemiaapp.databinding.ActivityEnviarTransferenciaBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityNuevoDestinoBinding
import mx.kodemia.bankodemiaapp.modules.transaction.view.fragments.CargandoTransaccionFragment
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.AgregarContactoViewModel
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.EnviarDineroViewModel
import java.text.SimpleDateFormat
import java.util.*

class NuevoDestino : AppCompatActivity() {

    val viewModel: AgregarContactoViewModel by viewModels()
    lateinit var binding: ActivityNuevoDestinoBinding
    private lateinit var shared: SharedPreferencesInstance
    private val alert = Alerts
    private val checkInternet = CheckInternet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNuevoDestinoBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        init()

        binding.apply {

            val id = shared.obtenerContacto()

            TVNumTarjeta.apply {
                isEnabled = false
                hint = id._id
            }
            TVNombreIngresar.hint = id.shortName

            BntNuevoDestinatario.setOnClickListener {
                if(CheckInternet.isNetworkAvailable(this@NuevoDestino)){
                    if(CheckToken.monitorToken(this@NuevoDestino, CheckToken.obtenerHoraActual())){
                        if (validacionCampo()){
                            val save = SaveContactRequest(
                                TVNombreIngresar.text.toString().trim(),
                                id._id!!
                            )
                            guardarContacto(save)
                        }
                    }else{
                        DialogExpiredToken.showDialogExpiredToken(this@NuevoDestino)
                    }
                }else{
                    alert.showToast("Necesitas internet para guardar el contacto", this@NuevoDestino)
                }
            }

        }

        observers()
    }

    private fun init() {
        //SharedPreferences
        shared = SharedPreferencesInstance.obtenerInstancia(this)

        viewModel.onCreate(context = this)
    }

    private fun observers(){
        viewModel.error.observe(this,::error)
        viewModel.cargando.observe(this, ::cargando)
        viewModel.saveContactResponse.observe(this,::procesoDeGuardarContacto)
    }

    private fun procesoDeGuardarContacto(saveContactResponse: SaveContactResponse){
        val intent = Intent(this, FinalizadoContacto::class.java)
        startActivity(intent)
    }

    private fun cargando(b: Boolean){
        //Esta cargando por defecto sin indicador
    }

    private fun error(error: String){
        Alerts.showSnackbar(error, activity = this)
    }

    private fun guardarContacto(saveContactRequest: SaveContactRequest){
        viewModel.saveContact(saveContactRequest)
    }

    fun validacionCampo(): Boolean{
        return if(binding.TVNombreIngresar.text.toString().isEmpty()){
            alert.showToast("Debes agregar un nombre para el contacto",this)
            false
        }else{
            true
        }
    }

}