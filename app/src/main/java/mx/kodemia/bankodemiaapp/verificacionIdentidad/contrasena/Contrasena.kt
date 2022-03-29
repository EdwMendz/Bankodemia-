package mx.kodemia.bankodemiaapp.verificacionIdentidad.contrasena

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_contrasena.*
import mx.kodemia.bankodemiaapp.R

class Contrasena : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrasena)
    }

    private fun validarContrasena(): Boolean{
        return if(tietPassword.text.toString().isEmpty()){
            tilPassword.error = getString(R.string.campo_vacio)
            false
        }else{
            tilPassword.isErrorEnabled = false
            true
        }
    }

    private fun validarContrasenaDos(): Boolean{
        return if(tietPasswordConfirm.text.toString().isEmpty()){
            tilPasswordConfirm.error = getString(R.string.campo_vacio)
            false
        }else{
            tilPasswordConfirm.isErrorEnabled = false
            true
        }
    }

    private fun validarSimilutud(): Boolean{
        val textoPassword: String = tietPassword.text?.trim().toString()
        val textoPassConfirm: String = tietPasswordConfirm.text?.trim().toString()
        return if (textoPassword != textoPassConfirm){
            Toast.makeText(this,"Contraseñas diferentes", Toast.LENGTH_SHORT).show()
            tietPassword.setText("")
            tietPasswordConfirm.setText("")
            false
        }else{
            true
        }
    }
}