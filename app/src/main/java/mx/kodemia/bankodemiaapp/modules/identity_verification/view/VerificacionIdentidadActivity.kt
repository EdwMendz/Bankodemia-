package mx.kodemia.bankodemiaapp.modules.identity_verification.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_verificacion_identidad.*
import mx.kodemia.bankodemiaapp.R

class VerificacionIdentidadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacion_identidad)

        btnIdentidad.setOnClickListener {
            startActivity(Intent(this, VerificacionIdentidad2::class.java))
        }

    }
}