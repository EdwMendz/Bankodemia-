package mx.kodemia.bankodemiaapp.modules.transaction.view.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.response.contacts.Contacto
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.databinding.ItemCardviewHomeBinding
import mx.kodemia.bankodemiaapp.databinding.ItemContactosBinding
import mx.kodemia.bankodemiaapp.modules.home.view.adapter.TransaccionesAdapter
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarDinero
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarTransferencia
import mx.kodemia.bankodemiaapp.modules.transaction.view.dialogs.Dialogs

class ContactosAdapter(val activity: Activity, val contactos: MutableList<Contacto>): RecyclerView.Adapter<ContactosAdapter.ContactosHolder>() {

    //SharedPreferences
    lateinit var shared : SharedPreferencesInstance

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactosAdapter.ContactosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contactos,parent,false)
        return ContactosAdapter.ContactosHolder(view)
    }

    override fun onBindViewHolder(holder: ContactosAdapter.ContactosHolder, position: Int) {
        val contacto = contactos.get(position)

        with(holder){

            binding.apply {

                shared = SharedPreferencesInstance.obtenerInstancia(activity)

                cardViewItemContacto.setOnClickListener {

                    shared.guardarContacto(contacto)

                    activity.startActivity(Intent(activity,EnviarTransferencia::class.java))
                }
                cardViewItemContacto.setOnLongClickListener {
                    val dialog = Dialogs
                    dialog.showDialogActionsContacts(activity,contacto._id!!)
                    true
                }

                textViewNombreContacto.text = contacto.shortName
                textViewTarjetaContacto.text = contacto._id

            }

        }

    }

    override fun getItemCount(): Int = contactos.size

    class ContactosHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemContactosBinding.bind(view)
    }
}