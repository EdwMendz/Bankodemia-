package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_crear_cuenta.*
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.databinding.ActivityCrearCuentaBinding


class CrearCuentaView : AppCompatActivity() {
    private lateinit var binding: ActivityCrearCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicializa el binding
        inicializarBinding()
        //vamos a hacer validacion de correo y si pasa lanzar continuar
        lanzarActivities()
    }

    //mandamos las activities
    private fun lanzarActivities() {
        binding.apply {
            btnCrearCuentaContinuar.setOnClickListener {
                if (validarCorreo()) {
                    lanzarDatos()
                }
            }
        }
    }


    private fun lanzarDatos() {
        val intent = Intent(this@CrearCuentaView, DatosView::class.java)
        startActivity(intent)
    }

    //Validar Correo
    private fun validarCorreo(): Boolean {
        binding.apply {

            return if (tietCrearcuentaCorreo.text.toString().isEmpty()) {
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


    //   Infla el view Binding
    private fun inicializarBinding() {
        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}