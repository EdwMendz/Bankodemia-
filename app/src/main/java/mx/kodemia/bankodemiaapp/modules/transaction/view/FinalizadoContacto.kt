package mx.kodemia.bankodemiaapp.modules.transaction.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.kodemia.bankodemiaapp.databinding.ActivityFinalizadoContactoBinding

class FinalizadoContacto : AppCompatActivity() {

    lateinit var binding: ActivityFinalizadoContactoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinalizadoContactoBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.btnVolverInicio.setOnClickListener {
            startActivity(Intent(this,EnviarDinero::class.java))
        }

    }
}