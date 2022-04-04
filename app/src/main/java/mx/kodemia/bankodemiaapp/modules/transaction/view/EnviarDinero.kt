package mx.kodemia.bankodemiaapp.modules.transaction.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.DialogExpiredToken
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.data.model.request.UpdateContactRequest
import mx.kodemia.bankodemiaapp.data.model.response.contacts.ActionsContactResponse
import mx.kodemia.bankodemiaapp.data.model.response.contacts.AllContacts
import mx.kodemia.bankodemiaapp.data.model.response.contacts.Contacto
import mx.kodemia.bankodemiaapp.data.model.response.contacts.GetSingleContactResponse
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.databinding.ActivityEnviarDineroBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityHomeBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityListaUsuariosBinding
import mx.kodemia.bankodemiaapp.modules.home.view.HomeActivity
import mx.kodemia.bankodemiaapp.modules.home.view.adapter.TransaccionesAdapter
import mx.kodemia.bankodemiaapp.modules.home.viewmodel.InicioFragmentViewModel
import mx.kodemia.bankodemiaapp.modules.inicioEd.view.InicioActivityView
import mx.kodemia.bankodemiaapp.modules.transaction.view.adapter.ContactosAdapter
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.AccionesContactoViewModel
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.ObtenerContactoUnicoViewModel
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.ObtenerContactosViewModel
import java.text.SimpleDateFormat
import java.util.*

class EnviarDinero : AppCompatActivity() {

    //View Binding
    private lateinit var binding: ActivityEnviarDineroBinding

    //viewModel
    val viewModel: ObtenerContactosViewModel by viewModels()
    val viewModelActions: AccionesContactoViewModel by viewModels()

    //SharedPreferences
    lateinit var shared : SharedPreferencesInstance

    //Internet
    val checkInternet = CheckInternet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
        returnNativo()

        if(checkInternet.isNetworkAvailable(this)){
            if(CheckToken.monitorToken(this, CheckToken.obtenerHoraActual())){
                solicitarContactos()
            }else{
                DialogExpiredToken.showDialogExpiredToken(this)
            }
        }else{
            Alerts.showToast(getString(R.string.no_acceso_internet),this)
        }

        binding.apply {
            ImgAgrega.setOnClickListener{
                startActivity(Intent(this@EnviarDinero,ListaUsuariosActivity::class.java))
            }
        }

        observers()
        observersActions()

    }

    fun init(){
        binding = ActivityEnviarDineroBinding.inflate(layoutInflater)

        supportActionBar?.hide()
        setContentView(binding.root)

        shared = SharedPreferencesInstance.obtenerInstancia(this)

        viewModel.onCreate(context = this)
        viewModelActions.onCreate(context = this)
    }

    private fun observers(){

        viewModel.error.observe(this,::error)
        viewModel.cargando.observe(this,::cargando)
        viewModel.getContactsResponse.observe(this,::mostrarContactos)

    }

    private fun observersActions(){
        viewModelActions.errorUpdate.observe(this,::errorUpdate)
        viewModelActions.cargandoUpdate.observe(this,::cargandoUpdate)
        viewModelActions.updateContactResponse.observe(this,::confirmacionActualizado)

        viewModelActions.errorDelete.observe(this,::errorDelete)
        viewModelActions.cargandoDelete.observe(this,::cargandoDelete)
        viewModelActions.deleteContactResponse.observe(this,::confirmacionBorrado)
    }

    private fun cargandoDelete(b: Boolean){
        //Esta cargando por defecto sin indicador
    }

    private fun errorDelete(error: String){
        Alerts.showSnackbar(getString(R.string.no_borrar_contacto), activity = this)
    }

    private fun cargandoUpdate(b: Boolean){
        //Esta cargando por defecto sin indicador
    }

    private fun errorUpdate(error: String){
        Alerts.showSnackbar(getString(R.string.no_actualizar_contacto), activity = this)
    }

    private fun cargando(b: Boolean){
        //Esta cargando por defecto sin indicador
    }

    private fun error(error: String){
        Alerts.showSnackbar(getString(R.string.no_acciones_contacto), activity = this)
    }

    private fun mostrarContactos(contactos: AllContacts){
        binding.apply {
            progressBarContactos.isVisible = false
            textViewprogressBarContactos.isVisible = false
            initRecycler(contactos.data.contacts,recyclerViewContactos)
        }

    }

    private fun confirmacionBorrado(actionsContactResponse: ActionsContactResponse){
        Alerts.showSnackbar(getString(R.string.borrado_exitoso), activity = this)
        viewModel.getContacts()
    }

    private fun confirmacionActualizado(actionsContactResponse: ActionsContactResponse){
        Alerts.showSnackbar(getString(R.string.actualizado_exitoso), activity = this)
        viewModel.getContacts()
    }

    private fun solicitarContactos(){
        viewModel.getContacts()
    }

    private fun returnNativo(){
        val callback = onBackPressedDispatcher.addCallback(this) {
            startActivity(Intent(this@EnviarDinero, HomeActivity::class.java))
        }
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