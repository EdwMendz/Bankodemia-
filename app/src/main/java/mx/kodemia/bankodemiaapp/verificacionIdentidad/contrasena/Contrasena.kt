package mx.kodemia.bankodemiaapp.verificacionIdentidad.contrasena

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.request.enummodels.DocumentType
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse
import mx.kodemia.bankodemiaapp.databinding.ActivityContrasenaBinding
import mx.kodemia.bankodemiaapp.verificacionIdentidad.ImageConverter
import mx.kodemia.bankodemiaapp.verificacionIdentidad.RegistroViewModel
import mx.kodemia.bankodemiaapp.verificacionIdentidad.activitiesrespuesta.ConfirmacionRegistroActivity
import mx.kodemia.bankodemiaapp.verificacionIdentidad.activitiesrespuesta.ErrorRegistroActivity
import mx.kodemia.bankodemiaapp.verificacionIdentidad.fragments.CargandoRegistroFragment
import mx.kodemia.bankodemiaapp.verificacionIdentidad.fragments.CrearContrasenaFragment

class Contrasena : AppCompatActivity() {

    val viewModel: RegistroViewModel by viewModels()
    lateinit var binding: ActivityContrasenaBinding
    private lateinit var shared: SharedPreferencesInstance
    private val imageConverter = ImageConverter
    private lateinit var tipoDocumento: DocumentType

    //Alertas por medio de Toast o SnackBar
    private val alert = Alerts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContrasenaBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.registro_fragmet_container, CrearContrasenaFragment())
            .commit()

        init()

        observers()
    }

    private fun init() {
        //SharedPreferences
        shared = SharedPreferencesInstance.obtenerInstancia(this)

        viewModel.onCreate(context = this)
    }

    fun observers() {

        viewModel.error.observe(this, ::error)
        viewModel.cargando.observe(this, ::cargando)
        viewModel.signUpResponse.observe(this, ::procesoDeRegistro)

    }

    fun procesoDeRegistro(signUpResponse: SignUpResponse) {
        alert.showToast("Exito",this)
        val intent = Intent(this,ConfirmacionRegistroActivity::class.java)
        startActivity(intent)
        /*supportFragmentManager.beginTransaction()
            .replace(R.id.registro_fragmet_container, ConfirmacionRegistroFragment())
            .commit()*/
    }

    fun cargando(b: Boolean) {
        alert.showToast("Cargando",this)
        supportFragmentManager.beginTransaction()
            .replace(R.id.registro_fragmet_container, CargandoRegistroFragment())
            .commit()
    }


    fun error(error: String) {
        alert.showToast("Error",this)
        val intent = Intent(this,ErrorRegistroActivity::class.java)
        startActivity(intent)
        /*supportFragmentManager.beginTransaction()
            .replace(R.id.registro_fragmet_container, ErrorRegistroFragment())
            .commit()*/
    }

}