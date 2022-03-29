package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.kodemia.bankodemiaapp.databinding.ActivityTelefonoBinding

class TelefonoView : AppCompatActivity() {

    private lateinit var binding: ActivityTelefonoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTelefonoBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Crear array de numeros/ items para el adaptador del autocompletetexview

        //val numeros: List<Int> =  listOf(55)
    }
}