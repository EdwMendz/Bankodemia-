package mx.kodemia.bankodemiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.databinding.ActivityMainBinding
import mx.kodemia.bankodemiaapp.modules.home.view.IniciarSesionView

class MainActivity : AppCompatActivity() {
    //Inicializamos el viewBinding
    private lateinit var binding: ActivityMainBinding

    //

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
        val intent = Intent(this, IniciarSesionView::class.java)
        startActivity(intent)
        finish()
    }

    fun lanzarActivityCrearCuenta() {
        val intent = Intent(this, CrearCuenta::class.java)
        startActivity(intent)
        finish()
    }

}