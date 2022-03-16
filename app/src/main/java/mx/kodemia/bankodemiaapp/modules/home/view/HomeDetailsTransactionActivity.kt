package mx.kodemia.bankodemiaapp.modules.home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import mx.kodemia.bankodemiaapp.animations.initParpadeoGuionLogo
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.databinding.ActivityHomeDetailsTransactionBinding
import mx.kodemia.bankodemiaapp.modules.home.viewmodel.HomeDetailsTransactionViewModel

class HomeDetailsTransactionActivity : AppCompatActivity() {

    //Binding
    lateinit var binding: ActivityHomeDetailsTransactionBinding

    //Shared
    lateinit var shared : SharedPreferencesInstance

    //ViewModel
    val viewModel: HomeDetailsTransactionViewModel by viewModels()

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
        //Shared
        shared = SharedPreferencesInstance.obtenerInstancia(this)

        //binding
        binding = ActivityHomeDetailsTransactionBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
    }
}