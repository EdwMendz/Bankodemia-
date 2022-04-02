package mx.kodemia.bankodemiaapp.verificacionIdentidad.activitiesrespuesta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.databinding.ActivityContrasenaBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityErrorRegistroBinding
import mx.kodemia.bankodemiaapp.modules.inicioEd.view.IniciarSesionView
import mx.kodemia.bankodemiaapp.verificacionIdentidad.VerificacionIdentidadActivity

class ErrorRegistroActivity : AppCompatActivity() {

    lateinit var binding: ActivityErrorRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityErrorRegistroBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.buttonVolverARegistro.setOnClickListener {
            val intent = Intent(this, VerificacionIdentidadActivity::class.java)
            startActivity(intent)
        }
    }
}