package mx.kodemia.bankodemiaapp.modules.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.animations.initParpadeoGuionLogo
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.databinding.ActivityHomeDetailsTransactionBinding

class HomeDetailsTransactionActivity : AppCompatActivity() {

    //View Binding
    lateinit var binding: ActivityHomeDetailsTransactionBinding

    //SharedPrerences
    lateinit var shared : SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        binding.apply {
            shared.obtenerElementoListaTransacciones(
                textViewMontoHome,
                textViewConceptoHome,
                textViewConcepto2Home,
                textViewFechaHome,
                textViewCuentaEmisoraHome,
                textViewIdTransaccionHome,
                applicationContext
            )
            initParpadeoGuionLogo(applicationContext,imageViewGuionLogo)
        }
    }

    private fun init(){
        //SharedPreferencesIntance
        shared = SharedPreferencesInstance.obtenerInstancia(this)

        //View Binding
        binding = ActivityHomeDetailsTransactionBinding.inflate(layoutInflater)
        supportActionBar?.hide() // Por temas de diseño se oculta El ActionBar de la parte superior del Activity
        setContentView(binding.root)
    }
}