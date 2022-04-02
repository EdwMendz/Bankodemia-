package mx.kodemia.bankodemiaapp.verificacionIdentidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import kotlinx.android.synthetic.main.activity_verificacion_identidad.*
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.modules.inicioEd.view.InicioActivityView

class VerificacionIdentidadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacion_identidad)


        btnIdentidad.setOnClickListener {
            startActivity(Intent(this, VerificacionIdentidad2::class.java))
        }

        //Return Nativo A InicioActivityView
        val callback = onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(this@VerificacionIdentidadActivity,InicioActivityView::class.java)
            startActivity(intent)
        }

    }
}