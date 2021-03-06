package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.databinding.ActivityMainBinding
import mx.kodemia.bankodemiaapp.modules.home.view.HomeActivity

class InicioActivityView : AppCompatActivity() {
    //Inicializamos el viewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        if(CheckToken.monitorToken(this,CheckToken.obtenerHoraActual())){
            lanzarActivitiHome()
        }

        super.onCreate(savedInstanceState)

        //Inicializar Binding
        inicializarBinding()
        //Activa el setOnCLickListener de los botones
        initButtons()
        //Return Nativo
        initReturnNativo()

    }

    private fun initButtons() {
        binding.apply {
            btnBienvenidaInisiarSesion.setOnClickListener {
                if (CheckInternet.isNetworkAvailable(this@InicioActivityView)){
                    lanzarIniciarSesion()
                }else{
                    Alerts.showToast(getString(R.string.no_acceso_internet),this@InicioActivityView)
                }
            }
            btnBienvenidaCrearCuenta.setOnClickListener {
                if (CheckInternet.isNetworkAvailable(this@InicioActivityView)){
                    lanzarCrearCuenta()
                }else{
                    Alerts.showToast(getString(R.string.no_acceso_internet),this@InicioActivityView)
                }
            }
        }
    }

    
    private fun initReturnNativo(){
        //Return Nativo Para salir de la app
        val callback = onBackPressedDispatcher.addCallback(this) {
            finish()
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
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
        supportActionBar?.hide()
        setContentView(binding.root)
    }

    private fun lanzarActivitiHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}