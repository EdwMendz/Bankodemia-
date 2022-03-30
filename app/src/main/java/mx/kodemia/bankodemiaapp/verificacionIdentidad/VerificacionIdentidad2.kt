package mx.kodemia.bankodemiaapp.verificacionIdentidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_verificacion_identidad2.*
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.verificacionIdentidad.confirmacionDocumento.ConfirmarTipoDocumento

class VerificacionIdentidad2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacion_identidad2)

        tvTipoDocumento.setOnClickListener {
            pasarDatos(this,getString(R.string.ine))
        }

        textViewPasaporte.setOnClickListener {
            pasarDatos(this,getString(R.string.pasaporte))
        }

        textViewDocMigratorio.setOnClickListener {
            pasarDatos(this,getString(R.string.documentoMigratorio))
        }


    }
}