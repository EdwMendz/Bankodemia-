package mx.kodemia.bankodemiaapp.modules.transaction.view.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.response.contacts.Contacto
import mx.kodemia.bankodemiaapp.data.model.response.user.EspecificUserInfo
import mx.kodemia.bankodemiaapp.databinding.ItemUsersBinding
import mx.kodemia.bankodemiaapp.modules.transaction.view.NuevoDestino

class UsersAdapter(val activity: Activity, private val usuarios: MutableList<EspecificUserInfo>) :
    RecyclerView.Adapter<UsersAdapter.UsersHolder>() {

    //SharedPreferences
    lateinit var shared: SharedPreferencesInstance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users,parent,false)
        return UsersHolder(view)
    }

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        val usuario = usuarios[position]

        with(holder){

            binding.apply {

                cardViewItemUser.setOnClickListener {

                    shared = SharedPreferencesInstance.obtenerInstancia(activity)
                    val usuarioInfo = Contacto(
                        usuario._id,
                        usuario.name
                    )
                    shared.guardarContacto(usuarioInfo)
                    activity.startActivity(Intent(activity, NuevoDestino::class.java))
                }

                textViewNombreUsuario.text = usuario.name
                textViewApellidoUsuario.text = usuario.lastName
                textViewIdUsuario.text = usuario._id
                textViewEmailUsuario.text = usuario.email
            }

        }
    }

    override fun getItemCount(): Int = usuarios.size

    class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemUsersBinding.bind(view)
    }
}