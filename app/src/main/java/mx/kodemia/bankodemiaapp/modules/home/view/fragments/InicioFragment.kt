package mx.kodemia.bankodemiaapp.modules.home.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.formatos.darFormatoDinero
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.data.model.response.user.GetUserFullResponse
import mx.kodemia.bankodemiaapp.databinding.FragmentInicioBinding
import mx.kodemia.bankodemiaapp.modules.home.view.adapter.TransaccionesAdapter
import mx.kodemia.bankodemiaapp.modules.home.viewmodel.InicioFragmentViewModel

class InicioFragment : Fragment() {

    val TAG = "LOGIN"

    //binding
    lateinit var binding: FragmentInicioBinding

    //viewModel
    val viewModel: InicioFragmentViewModel by viewModels()

    //SharedPreferences
    lateinit var shared : SharedPreferencesInstance

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        init()

        //TEMPORAL (Login Automatico)
        val logIn = LogInRequest(
            "federico123@kodemia.com",
            "FedericoGonza123"
        )
        mandarDatosLogIn("1h",logIn)

        binding.apply {
            observers(recyclerViewHome)
        }

        return root
    }

    //Inicializacion de SharedPreferences
    fun init(){
        shared = SharedPreferencesInstance.obtenerInstancia(requireActivity())

        viewModel.onCreate(context = requireActivity())
    }

    private fun observers(recyclerView: RecyclerView){

        viewModel.logInResponse.observe(requireActivity()) {logIn: LoginResponse ->
            shared.guardarSesionLogin(logIn)
            lifecycleScope.launch {
                logIn.apply {
                    Log.e(TAG,this.token.toString())
                    Log.e(TAG,this.expiresIn.toString())
                }
            }
        }

        viewModel.listTransacciones()
        viewModel.listTransactionResponse.observe(requireActivity()){ listTransaccion: ListaTransaccionesResponse ->
            lifecycleScope.launch {
                listTransaccion.apply {
                    Log.e(TAG,this.data.transactions[5].concept)
                    Log.e(TAG,this.success.toString())
                    initRecycler(this.data.transactions,recyclerView)
                }
            }
        }

        viewModel.getUserFullProfile()
        viewModel.getUserInformationResponse.observe(requireActivity()){ getUserFull: GetUserFullResponse ->
            lifecycleScope.launch{
                getUserFull.apply {
                    binding.textViewDineroDisponible.text = darFormatoDinero(this.data.balance)
                }
            }
        }

    }

    private fun mandarDatosLogIn(expires_in: String, logInRequest: LogInRequest) {
        viewModel.logIn(expires_in,logInRequest)
    }

    private fun initRecycler(lista: MutableList<Transaccion>, recyclerView: RecyclerView){
        val adaptador = TransaccionesAdapter(requireActivity(),lista)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adaptador
        }
    }

    /*
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    */
}