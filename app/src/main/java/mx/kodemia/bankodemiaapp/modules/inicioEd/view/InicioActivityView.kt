package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.databinding.ActivityDatosBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityMainBinding
import mx.kodemia.bankodemiaapp.modules.home.view.HomeActivity

class InicioActivityView : AppCompatActivity() {
    //Inicializamos el viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       //Inicializar Binding
        inicializarBinding()
        //Activa el setOnCLickListener de los botones
        initButtons()

    }

    private fun initButtons() {
        binding.apply {
            btnBienvenidaInisiarSesion.setOnClickListener {
                lanzarIniciarSesion()
            }
            btnBienvenidaCrearCuenta.setOnClickListener {
                lanzarCrearCuenta()
            }
        }
    }

    private fun lanzarIniciarSesion() {
        val intent = Intent(this@InicioActivityView,IniciarSesionView::class.java)
        startActivity(intent)
    }

    private fun lanzarCrearCuenta() {
        val intent = Intent(this@InicioActivityView, CrearCuentaView::class.java)
        startActivity(intent)
    }

    //Infla el view Binding
    private fun inicializarBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}