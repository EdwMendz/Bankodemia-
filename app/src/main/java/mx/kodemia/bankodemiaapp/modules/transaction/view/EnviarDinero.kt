package mx.kodemia.bankodemiaapp.modules.transaction.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.data.model.response.contacts.AllContacts
import mx.kodemia.bankodemiaapp.data.model.response.contacts.Contacto
import mx.kodemia.bankodemiaapp.data.model.response.contacts.GetSingleContactResponse
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.databinding.ActivityEnviarDineroBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityHomeBinding
import mx.kodemia.bankodemiaapp.modules.home.view.adapter.TransaccionesAdapter
import mx.kodemia.bankodemiaapp.modules.home.viewmodel.InicioFragmentViewModel
import mx.kodemia.bankodemiaapp.modules.transaction.view.adapter.ContactosAdapter
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.ObtenerContactoUnicoViewModel
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.ObtenerContactosViewModel

class EnviarDinero : AppCompatActivity() {

    //View Binding
    private lateinit var binding: ActivityEnviarDineroBinding

    //viewModel
    val viewModel: ObtenerContactosViewModel by viewModels()

    //SharedPreferences
    lateinit var shared : SharedPreferencesInstance

    //Internet
    val checkInternet = CheckInternet

    //Alerts
    val alert = Alerts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()

        if(checkInternet.isNetworkAvailable(this)){
            solicitarContactos()
        }else{
            alert.showToast("No tienes internet para ver los contactos",this)
        }

        observers()

    }

    fun init(){
        binding = ActivityEnviarDineroBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        setContentView(binding.root)

        shared = SharedPreferencesInstance.obtenerInstancia(this)

        viewModel.onCreate(context = this)
    }

    fun observers(){

        viewModel.error.observe(this,::error)
        viewModel.cargando.observe(this,::cargando)
        viewModel.getContactsResponse.observe(this,::mostrarContactos)

    }

    fun cargando(b: Boolean){

    }

    fun error(error: String){

    }

    fun mostrarContactos(contactos: AllContacts){
        binding.apply {
            initRecycler(contactos.data.contacts,recyclerViewContactos)
        }

    }

    fun solicitarContactos(){
        viewModel.getContacts()
    }

    //Inicializacion de RecyclerView
    private fun initRecycler(contactos: MutableList<Contacto>, recyclerView: RecyclerView){
        val adaptador = ContactosAdapter(this,contactos)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EnviarDinero)
            adapter = adaptador
        }
    }

}