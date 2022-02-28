package mx.kodemia.bankodemiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.databinding.ActivityCrearCuentaBinding


class CrearCuenta : AppCompatActivity() {
    private lateinit var binding: ActivityCrearCuentaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCrearCuentaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.ivIniciarSesionRegresar.setOnClickListener {
            lanzarActivityRegresar()
//            finish()
        }
        binding.btnCrearCuentaContinuar.setOnClickListener {
            lanzarAcivityIngresaTusDatos()
//            finish()
        }





    }





    fun lanzarAcivityIngresaTusDatos(){
        val intent = Intent(this, IngresaTusDatos::class.java)
        startActivity(intent)


    }
    fun lanzarActivityRegresar(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}