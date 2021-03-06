package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.animations.initParpadeoGuionLogo
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.databinding.ActivityIniciarSesionBinding
import mx.kodemia.bankodemiaapp.modules.home.view.HomeActivity
import mx.kodemia.bankodemiaapp.modules.inicioEd.viewModel.IniciarSesionViewModel

class IniciarSesionView : AppCompatActivity() {
    //Inicializa el viewBindin
    private lateinit var binding: ActivityIniciarSesionBinding

    //SharedPreferences
    lateinit var shared: SharedPreferencesInstance

    //Alertas por medio de Toast o SnackBar
    private val alert = Alerts

    //Union ViewModel con View
    val viewmodel: IniciarSesionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        returnNativo()
        initParpadeoGuionLogo(this,binding.imageViewCargaLogin)
        listenersCorreoYEmail()
        realizarPeticion()
        observadores()
    }

    //InicializaBindingViewModelyShared
    private fun init() {
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        shared = SharedPreferencesInstance.obtenerInstancia(this)
    }

    //Mandar peticion
    private fun realizarPeticion() {
        binding.apply {
            btnIniciarSesionIniciarSesion.setOnClickListener {
                if (CheckInternet.isNetworkAvailable(this@IniciarSesionView)) {
                    if (validarCorreoYContrasenia()) {
                        //Lanzar la peticion
                        val correo: String = tietIniciarSesisonCorreo.text.toString()
                        val pass: String = tietIniciarSesionContrasenia.text.toString()
                        val logIn = LogInRequest(correo, pass)
                        mandarDatosLogIn(getString(R.string.token_expired), logIn, this@IniciarSesionView)
                    }
                }else{
                    Alerts.showToast(getString(R.string.no_acceso_internet),this@IniciarSesionView)
                }
            }
        }
    }

    private fun lanzarActivitiHome() {
        val intent = Intent(this@IniciarSesionView, HomeActivity::class.java)
        startActivity(intent)
    }


    //Observadores
    private fun observadores() {
        //Observamos el progresbar
        viewmodel.cargando.observe(this,::cargando)
        //TEMPORAL-----------Inicio del bloque
        viewmodel.logInResponse.observe(this, ::guardarLogin)

        viewmodel.error.observe(this, ::errorLogin)
    }


    //TEMPORAL----------Final del bloque


    //Mostrar progresbar
    private fun cargando(cargando: Boolean) {
        if (!cargando && viewmodel.error.value?.isEmpty() == true) {
            finish()
            lanzarActivitiHome()
        }
    }

    //Guardar Login
    private fun guardarLogin(login: LoginResponse) {

        shared.guardarSesionLogin(login)
        shared.guardarHoraDeInicioDeSesion(CheckToken.obtenerHoraActual())
    }

    //MandarDatos
    private fun mandarDatosLogIn(
        expires_in: String,
        logInRequest: LogInRequest,
        activity: Activity
    ) {
        viewmodel.logIn(expires_in, logInRequest, activity)
    }


    //Si algo malo pasa mostramos el error
    private fun errorLogin(error: String): Boolean {
        if (error.isNotEmpty()) {
            alert.showToast(error, this)
            return true
        }
        return false
    }


    //**************************Validaciones del correo y email****************************************

    //    Validar Correo
    private fun validarCorreo(): Boolean {
        binding.apply {
            return if (tiet_IniciarSesison_Correo.text.toString().isEmpty()) {
                tilIniciarSesionCorreo.error = getString(R.string.campo_vacio)
                false
            } else {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(tietIniciarSesisonCorreo.text.toString())
                        .matches()
                ) {
                    tilIniciarSesionCorreo.isErrorEnabled = false
                    true
                } else {
                    tilIniciarSesionCorreo.error = getString(R.string.correo_no_valido)
                    false
                }
            }
        }
    }

    //
    //  Validar contrasenia
    private fun validarContrasenia(): Boolean {
        binding.apply {
            return if (tietIniciarSesionContrasenia.text.toString().isEmpty()) {
                tilIniciarSesionContrasenia.error = getString(R.string.campo_vacio)
                false
            } else {
                tilIniciarSesionContrasenia.isErrorEnabled = false
                true
            }
        }
    }

    //EscuchaTodoel tiempo los editText
    private fun listenersCorreoYEmail() {
        //validacion del email
        binding.apply {
            //Se escucha el email
            tietIniciarSesisonCorreo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(editText: Editable?) {
                    if (editText.toString().trim().isEmpty()) {
                        tilIniciarSesionCorreo.error = getString(R.string.el_campo_es_requerido)
                    } else {
                        tilIniciarSesionCorreo.isErrorEnabled = false
                        tilIniciarSesionCorreo.error = ""
                    }
                }

            })
            //Se escucha la contrasenia
            tietIniciarSesionContrasenia.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(editText: Editable?) {
                    if (editText.toString().trim().isEmpty()) {
                        tilIniciarSesionContrasenia.error = getString(R.string.el_campo_es_requerido)
                    } else {
                        tilIniciarSesionContrasenia.isErrorEnabled = false
                        tilIniciarSesionContrasenia.error = ""
                    }
                }
            })
        }
    }

    private fun returnNativo(){
        val callback = onBackPressedDispatcher.addCallback(this) {
            startActivity(Intent(this@IniciarSesionView,InicioActivityView::class.java))
        }
    }

    //Valida Correo y contrasenia juntos
    private fun validarCorreoYContrasenia(): Boolean {
        val result = arrayOf(validarCorreo(), validarContrasenia())
        return false !in result
    }
    //**************************FIN DE LAS Validaciones del correo y email****************************************

}

