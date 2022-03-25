package mx.kodemia.bankodemiaapp.modules.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*
import mx.kodemia.bankodemiaapp.MainActivity
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.checkForInternet
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.databinding.ActivityIniciarSesionBinding
import mx.kodemia.bankodemiaapp.modules.home.viewmodel.IniciarSesionViewModel

class IniciarSesionView : AppCompatActivity() {
    //Inicializa el viewBindin
    private lateinit var binding: ActivityIniciarSesionBinding

    //Union ViewModel con View
    val viewmodel: IniciarSesionViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Infla el binding
        inicializarBinding()
        realizarPeticion()
    }

    private fun realizarPeticion() {
        if (checkForInternet(applicationContext))
            binding.apply {
                val correo: String = tietIniciarSesisonCorreo.text.toString()
                val pass: String = tietIniciarSesionContrasenia.text.toString()
                btnIniciarSesionIniciarSesion.setOnClickListener {
                    validarCorreoYContrasenia()
                    val logIng = LogInRequest(correo, pass)

                    viewmodel.logIn("1h", logIng, this@IniciarSesionView)
                }
            }
    }

    //Validar Correo y contraseña
    private fun validarCorreo(): Boolean {
        binding.apply {

            return if (tiet_IniciarSesison_Correo.text.toString().isEmpty()) {
                tilIniciarSesisonCorreo.error = getString(R.string.campo_vacio)
                false
            } else {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(tietIniciarSesisonCorreo.text.toString())
                        .matches()
                ) {
                    tilIniciarSesisonCorreo.isErrorEnabled = false
                    true
                } else {
                    tilIniciarSesisonCorreo.error = getString(R.string.correo_no_valido)
                    false
                }
            }
        }
    }
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
    //Valida Correo y contrasenia juntos
    private fun validarCorreoYContrasenia() {
        val result = arrayOf(validarCorreo(), validarContrasenia())
        if (false in result)
            return
    }

    //Infla el view Binding
    private fun inicializarBinding() {
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}