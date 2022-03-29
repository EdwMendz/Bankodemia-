package com.example.proyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class NuevoDestino : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_destino)

        val Bnt_NuevoDestinatario: Button = findViewById(R.id.Bnt_NuevoDestinatario)
        Bnt_NuevoDestinatario.setOnClickListener{
            startActivity(Intent(this,FinalizadoContacto::class.java))
        }
    }
}