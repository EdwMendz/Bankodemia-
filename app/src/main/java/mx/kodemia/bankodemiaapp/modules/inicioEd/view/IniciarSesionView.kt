package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.checkForInternet
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.databinding.ActivityIniciarSesionBinding
import mx.kodemia.bankodemiaapp.modules.home.view.HomeActivity
import mx.kodemia.bankodemiaapp.modules.inicioEd.viewModel.IniciarSesionViewModel

class IniciarSesionView : AppCompatActivity() {
    //Inicializa el viewBindin
    private lateinit var binding: ActivityIniciarSesionBinding

    //Union ViewModel con View
    //val viewmodel: IniciarSesionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        escuchadoresCorreoYEmail()
        realizarPeticion()
    }


    //Mandar peticion
    private fun realizarPeticion() {
        if (checkForInternet(applicationContext))
            binding.apply {
                btnIniciarSesionIniciarSesion.setOnClickListener {
                    if (validarCorreoYContrasenia()) {
                        //Lanzar la peticion
                        lanzarActivitiHome()
                    }
                }
            }
    }

    private fun lanzarActivitiHome() {
        val intent = Intent(this@IniciarSesionView, HomeActivity::class.java)
        startActivity(intent)
    }

    //    //
//    //Observadores
//    private fun observadores() {
//        //Observamos el progresbar
//        viewmodel.cargando.observe(this) { cargando ->
//            cargando(cargando)
//        }
//    }
//
////    //Realizar Peticion
////    private fun realizarPeticion() {
////        if (checkForInternet(applicationContext))
////            binding.apply {
////                val correo: String = tietIniciarSesisonCorreo.text.toString()
////                val pass: String = tietIniciarSesionContrasenia.text.toString()
////                btnIniciarSesionIniciarSesion.setOnClickListener {
////                    validarCorreoYContrasenia()
////                    val logIng = LogInRequest(correo, pass)
////                    viewmodel.logIn("1h", logIng, this@IniciarSesionView)
////                }
////            }
////    }
//
//

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
    private fun escuchadoresCorreoYEmail() {
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
                        tilIniciarSesionCorreo.setError("El campo es requerido")
                    } else {
                        tilIniciarSesionCorreo.setErrorEnabled(false)
                        tilIniciarSesionCorreo.setError("")
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
                        tilIniciarSesionContrasenia.setError("El campo es requerido")
                    } else {
                        tilIniciarSesionContrasenia.setErrorEnabled(false)
                        tilIniciarSesionContrasenia.setError("")
                    }
                }
            })
        }
    }


    //Valida Correo y contrasenia juntos
    private fun validarCorreoYContrasenia(): Boolean {
        val result = arrayOf(validarCorreo(), validarContrasenia())
        return false !in result
    }
}

//    //Mostrar progresbar
//    private fun cargando(cargando: Boolean) {
//        binding.apply {
//            if (cargando) {
//                pbIniciarSesion.visibility = View.VISIBLE
//            } else {
//                pbIniciarSesion.visibility = View.GONE
//            }
//        }
//
//    }

