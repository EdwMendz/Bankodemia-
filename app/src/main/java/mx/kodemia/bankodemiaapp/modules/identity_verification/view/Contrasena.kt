package mx.kodemia.bankodemiaapp.modules.identity_verification.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.response.signUp.SignUpResponse
import mx.kodemia.bankodemiaapp.databinding.ActivityContrasenaBinding
import mx.kodemia.bankodemiaapp.modules.identity_verification.viewmodel.RegistroViewModel
import mx.kodemia.bankodemiaapp.modules.identity_verification.view.fragments.CargandoRegistroFragment
import mx.kodemia.bankodemiaapp.modules.identity_verification.view.fragments.CrearContrasenaFragment

class Contrasena : AppCompatActivity() {

    val viewModel: RegistroViewModel by viewModels()
    lateinit var binding: ActivityContrasenaBinding
    private lateinit var shared: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContrasenaBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        lanzarFragment(CrearContrasenaFragment())

        init()

        observers()
    }

    private fun init() {
        //SharedPreferences
        shared = SharedPreferencesInstance.obtenerInstancia(this)

        viewModel.onCreate(context = this)
    }

    private fun observers() {

        viewModel.error.observe(this, ::error)
        viewModel.cargando.observe(this, ::cargando)
        viewModel.signUpResponse.observe(this, ::procesoDeRegistro)

    }

    fun procesoDeRegistro(signUpResponse: SignUpResponse) {
        val intent = Intent(this, ConfirmacionRegistroActivity::class.java)
        startActivity(intent)
    }

    fun cargando(b: Boolean) {
        lanzarFragment(CargandoRegistroFragment())
    }


    fun error(error: String) {
        shared.guardarError(error)
        val intent = Intent(this, ErrorRegistroActivity::class.java)
        startActivity(intent)
    }

    private fun lanzarFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.registro_fragmet_container,fragment)
            .commit()
    }

}