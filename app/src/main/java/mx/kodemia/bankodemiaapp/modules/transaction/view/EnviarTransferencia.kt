package mx.kodemia.bankodemiaapp.modules.transaction.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.response.maketransaction.MakeTransactionResponse
import mx.kodemia.bankodemiaapp.databinding.ActivityEnviarTransferenciaBinding
import mx.kodemia.bankodemiaapp.modules.transaction.view.fragments.CargandoTransaccionFragment
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
        val intent = Intent(this, ConfirmacionTransferencia::class.java)
        startActivity(intent)
    }

    private fun cargando(b: Boolean){
        lanzarFragment(CargandoTransaccionFragment())
    }

    private fun error(error: String){
        shared.guardarError(error)
        val intent = Intent(this, ErrorTransactionActivity::class.java)
        startActivity(intent)
    }

    private fun lanzarFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.transaccion_container,fragment)
            .commit()
    }

}