package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import mx.kodemia.bankodemiaapp.databinding.ActivityMainBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityTelefonoBinding
import mx.kodemia.bankodemiaapp.verificacionIdentidad.VerificacionIdentidadActivity

class TelefonoView : AppCompatActivity() {

    private lateinit var binding: ActivityTelefonoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializarBinding()
        autoCompletTextView()
        lanzarActivityVerificacion()
        //Crear array de numeros/ items para el adaptador del autocompletetexview

    }

    //InicializarBindong
    private fun inicializarBinding() {
        binding = ActivityTelefonoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    private fun lanzarActivityVerificacion(){
        binding.apply {
            btnCrearCuentaContinuar.setOnClickListener {
                lanzarActivityVerificacion1()
            }
        }
    }

    private fun autoCompletTextView() {
        binding.apply {
            val numeros: List<Int> = listOf(55, 52, 31, 52, 52, 52, 56, 54)
            val listAdapter =
                ArrayAdapter<Int>(this@TelefonoView, android.R.layout.select_dialog_item, numeros)
            with(actvTelefono) {
                setAdapter(listAdapter)
                setOnItemClickListener { parent, view, position, id ->
                    val selectedItem = parent.getItemAtPosition(position).toString()
                    Toast.makeText(
                        this@TelefonoView,
                        "Seleccionaste" + selectedItem,
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
                setOnDismissListener {
                    Toast.makeText(this@TelefonoView, "opciones cerradas", Toast.LENGTH_LONG).show()
                }
                onFocusChangeListener = View.OnFocusChangeListener { view, b ->
                    showDropDown()
                }
            }

        }
    }
    private fun validarTelefono() {

    }
    private fun lanzarActivityVerificacion1(){
        val intent = Intent(this@TelefonoView, VerificacionIdentidadActivity::class.java)
        startActivity(intent)
    }
}