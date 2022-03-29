package mx.kodemia.bankodemiaapp.verificacionIdentidad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_verificacion_identidad2.*
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.verificacionIdentidad.documentoMigratorio.DocMigratorio
import mx.kodemia.bankodemiaapp.verificacionIdentidad.ine.Ine
import mx.kodemia.bankodemiaapp.verificacionIdentidad.pasaporte.Pasaporte

class VerificacionIdentidad2 : AppCompatActivity() {
<<<<<<< HEAD
<<<<<<< HEAD
=======

>>>>>>> ed5fafecf4b2fa80c6a9d321aff75ce15233d78c

    /*lateinit var binding: ActivityVerificacionIdentidad2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificacionIdentidad2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

        }*/
<<<<<<< HEAD
=======
>>>>>>> 637e0df1d59016c67377006be78339c28472b8da
=======

>>>>>>> ed5fafecf4b2fa80c6a9d321aff75ce15233d78c
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificacion_identidad2)

        textViewIne.setOnClickListener {
            startActivity(Intent(this, Ine::class.java))
        }

        textViewDocMigratorio.setOnClickListener {
            startActivity(Intent(this, DocMigratorio::class.java))
        }

        textViewPasaporte.setOnClickListener {
            startActivity(Intent(this, Pasaporte::class.java))
        }
    }
}