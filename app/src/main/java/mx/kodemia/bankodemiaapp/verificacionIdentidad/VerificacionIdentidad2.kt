package mx.kodemia.bankodemiaapp.verificacionIdentidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_verificacion_identidad2.*
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.verificacionIdentidad.confirmacionDocumento.ConfirmarTipoDocumento

class VerificacionIdentidad2 : AppCompatActivity() {

    private val shared = SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacion_identidad2)



        tvTipoDocumento.setOnClickListener {
            shared.guardarTipoDocumento(getString(R.string.ine))
            startActivity(Intent(this, ConfirmarTipoDocumento::class.java))
        }

        textViewPasaporte.setOnClickListener {
            shared.guardarTipoDocumento(getString(R.string.pasaporte))
            startActivity(Intent(this, ConfirmarTipoDocumento::class.java))
        }

        textViewDocMigratorio.setOnClickListener {
            shared.guardarTipoDocumento(getString(R.string.documentoMigratorio))
            startActivity(Intent(this, ConfirmarTipoDocumento::class.java))
        }


    }
}