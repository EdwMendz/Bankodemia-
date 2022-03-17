package mx.kodemia.bankodemiaapp.verificacionIdentidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_verificacion_identidad1.*
import mx.kodemia.bankodemiaapp.R

class VerificacionIdentidadActivitySael : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacion_identidad1)


        btnIdentidad.setOnClickListener {
            startActivity(Intent(this, VerificacionIdentidad2::class.java))
        }
    }
}