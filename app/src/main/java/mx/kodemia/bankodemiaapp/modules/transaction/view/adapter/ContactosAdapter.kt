package mx.kodemia.bankodemiaapp.modules.transaction.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.DialogExpiredToken
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.data.model.response.contacts.Contacto
import mx.kodemia.bankodemiaapp.databinding.ItemContactosBinding
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarTransferencia
import mx.kodemia.bankodemiaapp.modules.transaction.view.dialogs.Dialogs

class ContactosAdapter(val activity: Activity, private val contactos: MutableList<Contacto>): RecyclerView.Adapter<ContactosAdapter.ContactosHolder>() {

    //SharedPreferences
    lateinit var shared : SharedPreferencesInstance

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactosHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contactos,parent,false)
        return ContactosHolder(view)
    }

    override fun onBindViewHolder(holder: ContactosHolder, position: Int) {
        val contacto = contactos[position]

        with(holder){

            binding.apply {

                shared = SharedPreferencesInstance.obtenerInstancia(activity)

                cardViewItemContacto.setOnClickListener {

                    shared.guardarContacto(contacto)

                    activity.startActivity(Intent(activity,EnviarTransferencia::class.java))
                }
                cardViewItemContacto.setOnLongClickListener {
                    if(CheckInternet.isNetworkAvailable(activity)){
                        if(CheckToken.monitorToken(activity, CheckToken.obtenerHoraActual())){
                            val dialog = Dialogs
                            dialog.showDialogActionsContacts(activity,contacto._id!!)
                        }else{
                            DialogExpiredToken.showDialogExpiredToken(activity)
                        }
                    }else{
                        Alerts.showToast(activity.getString(R.string.no_acceso_internet),activity)
                    }
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