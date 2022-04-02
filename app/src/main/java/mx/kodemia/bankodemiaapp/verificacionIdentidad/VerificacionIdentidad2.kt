package mx.kodemia.bankodemiaapp.verificacionIdentidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_verificacion_identidad2.*
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.verificacionIdentidad.confirmacionDocumento.ConfirmarTipoDocumento

class VerificacionIdentidad2 : AppCompatActivity() {

    private lateinit var shared: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacion_identidad2)

        shared = SharedPreferencesInstance.obtenerInstancia(this)
        val lanzarActivity = Intent(this, ConfirmarTipoDocumento::class.java)

        tvTipoDocumento.setOnClickListener {
            shared.guardarTipoDocumento(getString(R.string.ine))
            startActivity(lanzarActivity)
        }

        textViewPasaporte.setOnClickListener {
            shared.guardarTipoDocumento(getString(R.string.pasaporte))
            startActivity(lanzarActivity)
        }

        textViewDocMigratorio.setOnClickListener {
            shared.guardarTipoDocumento(getString(R.string.documentoMigratorio))
            startActivity(lanzarActivity)
        }


    }
}