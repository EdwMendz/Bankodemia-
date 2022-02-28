package mx.kodemia.bankodemiaapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnBienvenidaInisiarSesion.setOnClickListener {
            lanzarActivityIniciarSesion()
        }
        binding.btnBienvenidaCrearCuenta.setOnClickListener {
            lanzarActivityCrearCuenta()
        }
    }


    fun lanzarActivityIniciarSesion() {
        val intent = Intent(this, IniciarSesion::class.java)
        startActivity(intent)
        finish()
    }

    fun lanzarActivityCrearCuenta() {
        val intent = Intent(this, CrearCuenta::class.java)
        startActivity(intent)
        finish()
    }

}