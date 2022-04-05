package mx.kodemia.bankodemiaapp.core

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.modules.inicioEd.view.IniciarSesionView

object DialogExpiredToken {

    fun showDialogExpiredToken(context: Context) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val layoutDialog = LayoutInflater.from(context).inflate(R.layout.dialog_token_caducado, null)
        builder.setView(layoutDialog)

        val btnAceptar = layoutDialog.findViewById<Button>(R.id.btn_aceptar_ir_a_inicio)

        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.window?.setGravity(Gravity.CENTER)

        btnAceptar.setOnClickListener {
            context.startActivity(Intent(context,IniciarSesionView::class.java))
        }

    }

}