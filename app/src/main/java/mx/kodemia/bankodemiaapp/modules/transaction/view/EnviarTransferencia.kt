package mx.kodemia.bankodemiaapp.modules.transaction.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.response.contacts.GetSingleContactResponse
import mx.kodemia.bankodemiaapp.data.model.response.makeTransaction.MakeTransactionResponse
import mx.kodemia.bankodemiaapp.databinding.ActivityContrasenaBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityEnviarDineroBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityEnviarTransferenciaBinding
import mx.kodemia.bankodemiaapp.databinding.FragmentHacerTransaccionBinding
import mx.kodemia.bankodemiaapp.modules.identity_verification.view.ConfirmacionRegistroActivity
import mx.kodemia.bankodemiaapp.modules.identity_verification.view.ErrorRegistroActivity
import mx.kodemia.bankodemiaapp.modules.identity_verification.view.fragments.CargandoRegistroFragment
import mx.kodemia.bankodemiaapp.modules.identity_verification.viewmodel.RegistroViewModel
import mx.kodemia.bankodemiaapp.modules.transaction.view.fragments.CargandoTransaccionFragment
import mx.kodemia.bankodemiaapp.modules.transaction.view.fragments.HacerTransaccionFragment
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.EnviarDineroViewModel
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.ObtenerContactoUnicoViewModel

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