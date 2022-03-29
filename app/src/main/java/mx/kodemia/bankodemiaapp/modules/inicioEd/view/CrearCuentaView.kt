package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.checkForInternet
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.databinding.ActivityCrearCuentaBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityDatosBinding


class CrearCuentaView : AppCompatActivity() {
    private lateinit var binding: ActivityCrearCuentaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //Inicializa el binding
        inicializarBinding()



        binding.ivIniciarSesionRegresar.setOnClickListener {
            lanzarActivityRegresar()
//            finish()
        }
    }

    fun realizarPeticion() {
        binding.apply {
            if (checkForInternet(applicationContext)) {
                val correo: String = tietCrearcuentaCorreo.text.toString()
                btnCrearCuentaContinuar.setOnClickListener {
                    if (validarCorreo()) {
                        lanzarAcivityDatos()
                    } else {
                        Toast.makeText(applicationContext, "upps algo paso", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

    }


    //Validar Correo
    private fun validarCorreo(): Boolean {
        binding.apply {

            return if (tiet_IniciarSesison_Correo.text.toString().isEmpty()) {
                tilCrearcuentaCorreo.error = getString(R.string.campo_vacio)
                false
            } else {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher(tietCrearcuentaCorreo.text.toString())
                        .matches()
                ) {
                    tilCrearcuentaCorreo.isErrorEnabled = false
                    true
                } else {
                    tilCrearcuentaCorreo.error = getString(R.string.correo_no_valido)
                    false
                }
            }
        }
    }


    fun lanzarAcivityDatos() {
        val intent = Intent(this, DatosView::class.java)
        startActivity(intent)
    }

    fun lanzarActivityRegresar() {
        val intent = Intent(this, InicioActivityView::class.java)
        startActivity(intent)
    }

    //Infla el view Binding
    private fun inicializarBinding() {
        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}