package mx.kodemia.bankodemiaapp

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import mx.kodemia.bankodemiaapp.databinding.ActivityIngresaTusDatosBinding
import java.text.SimpleDateFormat
import java.util.*

class IngresaTusDatos : AppCompatActivity() {
    var formatDate = SimpleDateFormat("dd/mm/yyyy",Locale.US)
    private lateinit var binding : ActivityIngresaTusDatosBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresaTusDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)


    binding.apply {
        ivIniciarSesionRegresar.setOnClickListener {
            lanzarActivityCrearC()
        }
        tietIngTusDatFechaNaci.setOnClickListener(View.OnClickListener {
            val getDate: Calendar = Calendar.getInstance()
            val datepicker = DatePickerDialog(this@IngresaTusDatos,android.R.style.Theme_Holo_Light_Dialog_MinWidth,DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                val selectDate: Calendar = Calendar.getInstance()
                selectDate.set(Calendar.YEAR,i)
                selectDate.set(Calendar.MONTH,i2)
                selectDate.set(Calendar.DAY_OF_WEEK_IN_MONTH,i3)

                val date:String? = formatDate.format(selectDate.time)
                Toast.makeText(this@IngresaTusDatos,"Fecha: " + date, Toast.LENGTH_SHORT).show()
                tietIngTusDatFechaNaci.setText(date)

            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH),getDate.get(Calendar.DAY_OF_MONTH))
            datepicker.show()
        })


    }
    }



    fun lanzarActivityCrearC(){
        val intent = Intent(this, CrearCuenta::class.java)
        startActivity(intent)
    }
}