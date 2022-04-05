package mx.kodemia.bankodemiaapp.modules.transaction.view.dialogs

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.data.model.request.MakeTransactionRequest
import mx.kodemia.bankodemiaapp.data.model.request.UpdateContactRequest
import mx.kodemia.bankodemiaapp.modules.inicioEd.view.IniciarSesionView
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarDinero
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarTransferencia

object Dialogs {

    fun showDialogActionsContacts(context: Context, id: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val layoutDialog = LayoutInflater.from(context).inflate(R.layout.dialog_opciones_contactos, null)
        builder.setView(layoutDialog)

        val btnActualizar = layoutDialog.findViewById<Button>(R.id.btn_actualizar)
        val btnBorrar = layoutDialog.findViewById<Button>(R.id.btn_borrar)


        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.window?.setGravity(Gravity.CENTER)

        btnBorrar.setOnClickListener {
            showDialogDeleteContact(context,id)
            dialog.dismiss()
        }

        btnActualizar.setOnClickListener {
            showDialogUpdateContact(context,id)
            dialog.dismiss()
        }
    }

    private fun showDialogDeleteContact(context: Context, id: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val layoutDialog = LayoutInflater.from(context).inflate(R.layout.dialog_borrar_contacto, null)
        builder.setView(layoutDialog)

        val btnAceptar = layoutDialog.findViewById<Button>(R.id.btn_aceptar_borrar)
        val btnCancelar = layoutDialog.findViewById<Button>(R.id.btn_cancelar_borrar)
        val tvErrorDelete = layoutDialog.findViewById<TextView>(R.id.textViewErrorDelete)


        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.window?.setGravity(Gravity.CENTER)

        btnAceptar.setOnClickListener {
            if(CheckInternet.isNetworkAvailable(context)){
                if(CheckToken.monitorToken(context, CheckToken.obtenerHoraActual())){
                    tvErrorDelete.visibility = View.GONE
                    (context as EnviarDinero).viewModelActions.deleteContact(id)
                    dialog.dismiss()
                }else{
                    tvErrorDelete.text = context.getString(R.string.token_caducado_vuelve_inicio)
                    btnAceptar.text = context.getString(R.string.volver_a_inicio)
                    btnAceptar.setOnClickListener {
                        context.startActivity(Intent(context, IniciarSesionView::class.java))
                    }
                }
            }else{
                tvErrorDelete.text = context.getString(R.string.no_acceso_internet)
            }
        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showDialogUpdateContact(context: Context, id: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val layoutDialog = LayoutInflater.from(context).inflate(R.layout.dialog_actualizar_contacto, null)
        builder.setView(layoutDialog)

        val btnAceptar = layoutDialog.findViewById<Button>(R.id.btn_aceptar_actualizar)
        val btnCancelar = layoutDialog.findViewById<Button>(R.id.btn_cancelar_actualizar)
        val tvErrorUpdate = layoutDialog.findViewById<TextView>(R.id.textViewErrorUpdate)
        val datoNuevoNombre = layoutDialog.findViewById<EditText>(R.id.tiet_actualizarContacto)


        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.window?.setGravity(Gravity.CENTER)

        btnAceptar.setOnClickListener {
            val nombre = datoNuevoNombre.text.toString().trim()
            if(CheckInternet.isNetworkAvailable(context)){
                if(CheckToken.monitorToken(context, CheckToken.obtenerHoraActual())){
                    if(nombre.isNotEmpty()){
                        (context as EnviarDinero).viewModelActions.updateContact(id,UpdateContactRequest(nombre))
                        dialog.dismiss()
                    }else{
                        tvErrorUpdate.text = context.getString(R.string.campo_vacio)
                    }
                }else{
                    tvErrorUpdate.text = context.getString(R.string.token_caducado_vuelve_inicio)
                    btnAceptar.text = context.getString(R.string.volver_a_inicio)
                    btnAceptar.setOnClickListener {
                        context.startActivity(Intent(context, IniciarSesionView::class.java))
                    }
                }
            }else{
                tvErrorUpdate.text = context.getString(R.string.no_acceso_internet)
            }
        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun showDialogTransaction(context: Context, makeTransactionRequest: MakeTransactionRequest) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val layoutDialog = LayoutInflater.from(context).inflate(R.layout.activity_mensaje, null)
        builder.setView(layoutDialog)

        val btnAceptar = layoutDialog.findViewById<Button>(R.id.btn_aceptar)
        val btnCancelar = layoutDialog.findViewById<Button>(R.id.btn_cancelar)


        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.window?.setGravity(Gravity.CENTER)

        btnAceptar.setOnClickListener {
                (context as EnviarTransferencia).viewModel.makeTransaction(makeTransactionRequest)
                dialog.dismiss()
        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }
    }

}