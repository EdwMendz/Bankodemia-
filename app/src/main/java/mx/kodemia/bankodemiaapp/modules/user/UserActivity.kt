package mx.kodemia.bankodemiaapp.modules.user

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.kodemia.bankodemiaapp.animations.initParpadeoGuionLogo
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.databinding.ActivityUserBinding
import mx.kodemia.bankodemiaapp.modules.inicioEd.view.InicioActivityView

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding
    lateinit var shared: SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        shared = SharedPreferencesInstance.obtenerInstancia(this)
        val informacionUsuario = shared.obtenerInformacionCompletaUsuario()

        binding.apply {

            initParpadeoGuionLogo(this@UserActivity,imageViewLogoBienvenida)
            initParpadeoGuionLogo(this@UserActivity,imageViewBarritaUser)

            textViewNombreUser.text = informacionUsuario.name
            textViewAppelidoUser.text = informacionUsuario.lastName
            textViewIdUser.text = informacionUsuario._id
            textViewCorreoUser.text = informacionUsuario.email

            textViewCerrarSesion.setOnClickListener {
                shared.limpiarPreferencias()
                lanzarInicio()
            }

        }



    }

    private fun lanzarInicio(){
        startActivity(Intent(this,InicioActivityView::class.java))
    }

}