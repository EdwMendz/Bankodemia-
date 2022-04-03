package mx.kodemia.bankodemiaapp.modules.transaction.view.dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.data.model.request.MakeTransactionRequest
import mx.kodemia.bankodemiaapp.data.model.request.UpdateContactRequest
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarDinero
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarTransferencia
import mx.kodemia.bankodemiaapp.modules.transaction.view.fragments.HacerTransaccionFragment

object Dialogs {

    fun showDialogActionsContacts(context: Context, id: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val layout_dialog = LayoutInflater.from(context).inflate(R.layout.dialog_opciones_contactos, null)
        builder.setView(layout_dialog)

        val btnActualizar = layout_dialog.findViewById<Button>(R.id.btn_actualizar)
        val btnBorrar = layout_dialog.findViewById<Button>(R.id.btn_borrar)


        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.getWindow()?.setGravity(Gravity.CENTER)

        btnBorrar.setOnClickListener {
            showDialogDeleteContact(context,id)
            dialog.dismiss()
        }

        btnActualizar.setOnClickListener {
            showDialogUpdateContact(context,id)
            dialog.dismiss()
        }
    }

    fun showDialogDeleteContact(context: Context, id: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val layout_dialog = LayoutInflater.from(context).inflate(R.layout.dialog_borrar_contacto, null)
        builder.setView(layout_dialog)

        val btnAceptar = layout_dialog.findViewById<Button>(R.id.btn_aceptar_borrar)
        val btnCancelar = layout_dialog.findViewById<Button>(R.id.btn_cancelar_borrar)


        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.getWindow()?.setGravity(Gravity.CENTER)

        btnAceptar.setOnClickListener {
            if(CheckInternet.isNetworkAvailable(context)){
                if(CheckToken.monitorToken(context, CheckToken.obtenerHoraActual())){
                    (context as EnviarDinero).viewModelActions.deleteContact(id)
                    dialog.dismiss()
                }
            }
        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun showDialogUpdateContact(context: Context, id: String) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val layout_dialog = LayoutInflater.from(context).inflate(R.layout.dialog_actualizar_contacto, null)
        builder.setView(layout_dialog)

        val btnAceptar = layout_dialog.findViewById<Button>(R.id.btn_aceptar_actualizar)
        val btnCancelar = layout_dialog.findViewById<Button>(R.id.btn_cancelar_actualizar)
        val datoNuevoNombre = layout_dialog.findViewById<EditText>(R.id.tiet_actualizarContacto)


        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.getWindow()?.setGravity(Gravity.CENTER)

        btnAceptar.setOnClickListener {
            val nombre = datoNuevoNombre.text.toString().trim()
            if(CheckInternet.isNetworkAvailable(context)){
                if(CheckToken.monitorToken(context, CheckToken.obtenerHoraActual())){
                    if(nombre.isNotEmpty()){
                        (context as EnviarDinero).viewModelActions.updateContact(id,UpdateContactRequest(nombre))
                    }else{
                        showDialogUpdateContact(context,id)
                    }
                    dialog.dismiss()
                }
            }

        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }
    }

    fun showDialogTransaction(context: Context, makeTransactionRequest: MakeTransactionRequest) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val layout_dialog = LayoutInflater.from(context).inflate(R.layout.activity_mensaje, null)
        builder.setView(layout_dialog)

        val btnAceptar = layout_dialog.findViewById<Button>(R.id.btn_aceptar)
        val btnCancelar = layout_dialog.findViewById<Button>(R.id.btn_cancelar)


        val dialog: AlertDialog = builder.create()
        dialog.show()
        dialog.setCancelable(true)

        dialog.getWindow()?.setGravity(Gravity.CENTER)

        btnAceptar.setOnClickListener {
                (context as EnviarTransferencia).viewModel.makeTransaction(makeTransactionRequest)
                dialog.dismiss()
        }

        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }
    }

}