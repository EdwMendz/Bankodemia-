package mx.kodemia.bankodemiaapp.core

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.modules.inicioEd.view.IniciarSesionView
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarDinero

object DialogExpiredToken {

    fun showDialogExpiredToken(context: Context) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val layout_dialog = LayoutInflater.from(context).inflate(R.layout.dialog_token_caducado, null)
        builder.setView(layout_dialog)

        val btnAceptar = layout_dialog.findViewById<Button>(R.id.btn_aceptar_ir_a_inicio)

        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.getWindow()?.setGravity(Gravity.CENTER)

        btnAceptar.setOnClickListener {
            context.startActivity(Intent(context,IniciarSesionView::class.java))
        }

    }

}