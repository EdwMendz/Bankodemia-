package mx.kodemia.bankodemiaapp.verificacionIdentidad.activitiesrespuesta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.databinding.ActivityConfirmacionRegistroBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityErrorRegistroBinding
import mx.kodemia.bankodemiaapp.modules.inicioEd.view.IniciarSesionView
import mx.kodemia.bankodemiaapp.modules.inicioEd.view.InicioActivityView

class ConfirmacionRegistroActivity : AppCompatActivity() {

    lateinit var binding: ActivityConfirmacionRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmacionRegistroBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.buttonVolverAInicioActivity.setOnClickListener {
            val intent = Intent(this, InicioActivityView::class.java)
            startActivity(intent)
        }

    }
}