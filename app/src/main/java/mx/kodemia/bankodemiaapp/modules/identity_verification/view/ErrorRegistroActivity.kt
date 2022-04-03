package mx.kodemia.bankodemiaapp.modules.identity_verification.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.databinding.ActivityErrorRegistroBinding

class ErrorRegistroActivity : AppCompatActivity() {

    lateinit var binding: ActivityErrorRegistroBinding
    lateinit var shared: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorRegistroBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        shared = SharedPreferencesInstance.obtenerInstancia(this)

        binding.apply {
            tvError.text = shared.obtenerError()
        }

        binding.buttonVolverARegistro.setOnClickListener {
            val intent = Intent(this, VerificacionIdentidadActivity::class.java)
            startActivity(intent)
        }
    }
}