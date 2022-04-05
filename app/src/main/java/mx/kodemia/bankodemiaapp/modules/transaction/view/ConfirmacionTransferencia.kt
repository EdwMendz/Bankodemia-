package mx.kodemia.bankodemiaapp.modules.transaction.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.kodemia.bankodemiaapp.databinding.ActivityConfirmacionTransferenciaBinding
import mx.kodemia.bankodemiaapp.modules.home.view.HomeActivity

class ConfirmacionTransferencia : AppCompatActivity() {

    private lateinit var binding: ActivityConfirmacionTransferenciaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmacionTransferenciaBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.btnVolverAInicio.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }

    }
}