package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.databinding.ActivityMainBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityTelefonoBinding
import mx.kodemia.bankodemiaapp.verificacionIdentidad.VerificacionIdentidadActivity

class TelefonoView : AppCompatActivity() {
    private lateinit var shared : SharedPreferencesInstance

    val alert = Alerts

    private lateinit var binding: ActivityTelefonoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        shared = SharedPreferencesInstance.obtenerInstancia(this)
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
    //Mandar a la otra activity
    private fun lanzarActivityVerificacion(){
        binding.apply {
            btnCrearCuentaContinuar.setOnClickListener {
                val telefono = actvTelefono.text.toString()
                if (validarTelefono(telefono)){
                    shared.guardarTelefono(telefono)
                    lanzarActivityVerificacion1()
                }
            }
        }
    }
    //AutoCompletarTextview
    private fun autoCompletTextView() {
        binding.apply {
            val numeros: List<String> = listOf("+55", "+52", "+31", "+56", "+54")
            val listAdapter =
                ArrayAdapter<String>(this@TelefonoView, android.R.layout.select_dialog_item, numeros)
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
    //Validar telefono
    private fun validarTelefono(telefono:String):Boolean {
        if(telefono.length==13){
            return true

        }
        alert.showToast("Numero De telefono Invalido",this)
        return false
    }
    private fun lanzarActivityVerificacion1(){
        val intent = Intent(this@TelefonoView, VerificacionIdentidadActivity::class.java)
        startActivity(intent)
    }
}