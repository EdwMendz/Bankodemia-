package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.User
import mx.kodemia.bankodemiaapp.databinding.ActivityCrearCuentaBinding
import mx.kodemia.bankodemiaapp.formatos.darFormatoDinero
import mx.kodemia.bankodemiaapp.formatos.darFormatoHoraMinutos
import mx.kodemia.bankodemiaapp.modules.home.view.HomeDetailsTransactionActivity
import mx.kodemia.bankodemiaapp.modules.inicioEd.viewModel.CrearCuentaViewModel


class CrearCuentaView : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityCrearCuentaBinding
    //SharedPreferences
    private lateinit var shared: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shared = SharedPreferencesInstance.obtenerInstancia(this)
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
                    binding.apply {
                        val email = binding.tietCrearcuentaCorreo.text.toString().trim()
                       shared.guardarCorreo(email)
                        lanzarDatos()
                    }
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