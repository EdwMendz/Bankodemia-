package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.addCallback
import mx.kodemia.bankodemiaapp.databinding.ActivityMainBinding

class InicioActivityView : AppCompatActivity() {
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

        //Return Nativo Para salir de la app
        val callback = onBackPressedDispatcher.addCallback(this) {
            finish()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

    }


    fun lanzarActivityIniciarSesion() {
        val intent = Intent(this, IniciarSesionView::class.java)
        startActivity(intent)
        finish()
    }

    fun lanzarActivityCrearCuenta() {
        val intent = Intent(this, CrearCuentaView::class.java)
        startActivity(intent)
        finish()
    }

}