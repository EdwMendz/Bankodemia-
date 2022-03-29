package mx.kodemia.bankodemiaapp.modules.inicioEd.view

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import mx.kodemia.bankodemiaapp.core.checkForInternet
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.databinding.ActivityDatosBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityIniciarSesionBinding
import mx.kodemia.bankodemiaapp.modules.inicioEd.viewModel.DatosViewModel
import mx.kodemia.bankodemiaapp.modules.inicioEd.viewModel.IniciarSesionViewModel
import java.text.SimpleDateFormat
import java.util.*

class DatosView : AppCompatActivity() {
    var formatDate = SimpleDateFormat("dd MMMM yyyy", Locale.US)
    //Inicializa el viewBindin
    private lateinit var binding: ActivityDatosBinding
    //Union ViewModel con View
    val viewmodel: DatosViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inicializarBinding()

        binding.apply {
            ivIniciarSesionRegresar.setOnClickListener {
                lanzarActivityCrearC()
            }
            tietDatosFechaNaci.setOnClickListener(View.OnClickListener {
                val getDate: Calendar = Calendar.getInstance()
                val datepicker = DatePickerDialog(
                    this@DatosView,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                        val selectDate: Calendar = Calendar.getInstance()
                        selectDate.set(Calendar.YEAR, i)
                        selectDate.set(Calendar.MONTH, i2)
                        selectDate.set(Calendar.DAY_OF_MONTH, i3)

                        val date: String? = formatDate.format(selectDate.time)
                        Toast.makeText(this@DatosView, "Fecha: " + date, Toast.LENGTH_SHORT)
                            .show()
                        tietDatosFechaNaci.setText(date)

                    },
                    getDate.get(Calendar.YEAR),
                    getDate.get(Calendar.MONTH),
                    getDate.get(Calendar.DAY_OF_MONTH)
                )
                datepicker.show()
            })
            btnIniciarSesionIniciarSesion.setOnClickListener {
                lanzarActivityTelefono()
            }

        }
    }
    //Infla el view Binding
    private fun inicializarBinding() {
        binding = ActivityDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    fun lanzarActivityCrearC() {
        val intent = Intent(this, CrearCuentaView::class.java)
        startActivity(intent)
    }
    fun lanzarActivityTelefono(){
        val intent = Intent(this, TelefonoView::class.java)
        startActivity(intent)
    }

}