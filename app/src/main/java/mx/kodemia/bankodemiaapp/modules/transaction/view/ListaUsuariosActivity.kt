package mx.kodemia.bankodemiaapp.modules.transaction.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.DialogExpiredToken
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.data.model.response.user.EspecificUserInfo
import mx.kodemia.bankodemiaapp.data.model.response.user.ListUsersResponse
import mx.kodemia.bankodemiaapp.databinding.ActivityListaUsuariosBinding
import mx.kodemia.bankodemiaapp.modules.transaction.view.adapter.UsersAdapter
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.ObtenerUsuariosViewModel

class ListaUsuariosActivity : AppCompatActivity() {

    lateinit var binding : ActivityListaUsuariosBinding

    val viewModel: ObtenerUsuariosViewModel by viewModels()

    //SharedPreferences
    lateinit var shared : SharedPreferencesInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaUsuariosBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        init()

        if(CheckInternet.isNetworkAvailable(this)){
            if(CheckToken.monitorToken(this, CheckToken.obtenerHoraActual())){
                solicitarUsuarios()
            }else{
                DialogExpiredToken.showDialogExpiredToken(this)
            }
        }else{
            Alerts.showToast(getString(R.string.no_internet_para_registros),this)
        }

        observers()

    }

    fun init(){
        shared = SharedPreferencesInstance.obtenerInstancia(this)

        viewModel.onCreate(context = this)
    }

    private fun observers(){
        viewModel.error.observe(this,::error)
        viewModel.cargando.observe(this,::cargando)
        viewModel.getUsersResponse.observe(this,::mostrarUsuarios)
    }

    private fun cargando(b: Boolean){
        //Esta cargando por defecto sin indicador
    }

    private fun error(error: String){
        Alerts.showSnackbar(getString(R.string.no_se_pudo_mostrar_usuarios), activity = this)
    }

    private fun mostrarUsuarios(usuarios: ListUsersResponse){
        binding.apply {
            progressBarUsuarios.isVisible = false
            textViewprogressBarUsuarios.isVisible = false
            initRecycler(usuarios.data.users, recyclerViewUsuarios)
        }
    }

    private fun solicitarUsuarios(){
        viewModel.getUsers()
    }

    private fun initRecycler(users: MutableList<EspecificUserInfo>, recyclerView: RecyclerView){
        val adaptador = UsersAdapter(this,users)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListaUsuariosActivity)
            adapter = adaptador
        }
    }

}