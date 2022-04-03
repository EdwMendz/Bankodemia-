package mx.kodemia.bankodemiaapp.modules.transaction.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.data.model.response.user.EspecificUserInfo
import mx.kodemia.bankodemiaapp.data.model.response.user.ListUsersResponse
import mx.kodemia.bankodemiaapp.databinding.ActivityEnviarTransferenciaBinding
import mx.kodemia.bankodemiaapp.databinding.ActivityListaUsuariosBinding
import mx.kodemia.bankodemiaapp.modules.home.view.adapter.TransaccionesAdapter
import mx.kodemia.bankodemiaapp.modules.home.viewmodel.InicioFragmentViewModel
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
                Alerts.showSnackbar("Tu token ha caducado", activity = this)
            }
        }else{
            Alerts.showToast("No tienes conexion a internet",this)
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

    }

    private fun error(error: String){

    }

    private fun mostrarUsuarios(usuarios: ListUsersResponse){
        binding.apply {
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