package mx.kodemia.bankodemiaapp.modules.transaction.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.databinding.ActivityConfirmacionTransferenciaBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityErrorTransactionBinding
import mx.kodemia.bankodemiaapp.modules.home.view.HomeActivity

class ErrorTransactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityErrorTransactionBinding
    lateinit var shared: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorTransactionBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        shared = SharedPreferencesInstance.obtenerInstancia(this)

        binding.textViewErrorTransaction.text = shared.obtenerError()
        binding.btnVolverAInicioErrorTransaccion.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}