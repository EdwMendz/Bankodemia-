package mx.kodemia.bankodemiaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class EnviarTransferencia : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enviar_transferencia)

        val Img_regreso: ImageButton = findViewById(R.id.Img_regreso)
        Img_regreso.setOnClickListener{
            startActivity(Intent(this,EnviarDinero::class.java))
        }

        val btnTransferencia: Button = findViewById(R.id.btnTransferencia)
        btnTransferencia.setOnClickListener{
            startActivity(Intent(this,Mensaje::class.java))
        }
    }
}