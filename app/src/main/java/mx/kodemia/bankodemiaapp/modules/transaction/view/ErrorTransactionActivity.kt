package mx.kodemia.bankodemiaapp.modules.transaction.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
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