package mx.kodemia.bankodemiaapp.modules.transaction.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.response.makeTransaction.MakeTransactionResponse
import mx.kodemia.bankodemiaapp.databinding.ActivityContrasenaBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityEnviarDineroBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityEnviarTransferenciaBinding
import mx.kodemia.bankodemiaapp.modules.identity_verification.viewmodel.RegistroViewModel
import mx.kodemia.bankodemiaapp.modules.transaction.view.fragments.HacerTransaccionFragment
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.EnviarDineroViewModel

class EnviarTransferencia : AppCompatActivity() {

    val viewModel: EnviarDineroViewModel by viewModels()
    lateinit var binding: ActivityEnviarTransferenciaBinding
    private lateinit var shared: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEnviarTransferenciaBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        lanzarFragment(HacerTransaccionFragment())

        init()

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
        viewModel.makeTransactionResponse.observe(this,::procesoDeTransaccion)
    }

    private fun procesoDeTransaccion(transaction: MakeTransactionResponse){

    }

    private fun cargando(b: Boolean){

    }

    private fun error(error: String){

    }

    private fun lanzarFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.registro_fragmet_container,fragment)
            .commit()
    }

}