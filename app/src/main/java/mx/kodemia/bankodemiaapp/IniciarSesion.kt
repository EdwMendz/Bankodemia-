package mx.kodemia.bankodemiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.databinding.ActivityIniciarSesionBinding

class IniciarSesion : AppCompatActivity() {
    private lateinit var binding:ActivityIniciarSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

    binding.ivIniciarSesionRegresar.setOnClickListener {
        lanzarAcivity()
    }

    }




    fun lanzarAcivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}