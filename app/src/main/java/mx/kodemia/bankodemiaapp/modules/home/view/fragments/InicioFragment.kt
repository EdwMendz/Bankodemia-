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
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.animations.initParpadeoGuionLogo
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.formatos.darFormatoDinero
import mx.kodemia.bankodemiaapp.data.model.request.LogInRequest
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.listaTransacciones.Transaccion
import mx.kodemia.bankodemiaapp.data.model.response.logIn.LoginResponse
import mx.kodemia.bankodemiaapp.data.model.response.user.GetUserFullResponse
import mx.kodemia.bankodemiaapp.databinding.FragmentInicioBinding
import mx.kodemia.bankodemiaapp.formatos.darFormatoFechaActual
import mx.kodemia.bankodemiaapp.modules.home.view.adapter.TransaccionesAdapter
import mx.kodemia.bankodemiaapp.modules.home.viewmodel.InicioFragmentViewModel

class InicioFragment : Fragment() {

    val TAG = "LOGIN"

    //binding
    private var binding: FragmentInicioBinding? = null

    //viewModel
    val viewModel: InicioFragmentViewModel by viewModels()

    //SharedPreferences
    lateinit var shared : SharedPreferencesInstance

    private val alert = Alerts

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInicioBinding.inflate(inflater, container, false)

        init()

        //TEMPORAL (Login Automatico)
        val logIn = LogInRequest(
            "federico123@kodemia.com",
            "FedericoGonza123"
        )
        mandarDatosLogIn("1h",logIn)

        binding?.apply {
            textViewFecha.text = darFormatoFechaActual()
            initParpadeoGuionLogo(requireContext(),imageViewGuionLogo)
        }

        observers()

        return binding!!.root
    }

    //Inicializacion de SharedPreferences y Dar el contexto a ViewModel
    fun init(){
        shared = SharedPreferencesInstance.obtenerInstancia(requireActivity())

        viewModel.onCreate(context = requireActivity())
    }

    private fun observers(){

        viewModel.logInResponse.observe(viewLifecycleOwner,::guardarLogin)

        viewModel.errorTrans.observe(viewLifecycleOwner,::errorTrans)
        viewModel.cargandoTrans.observe(viewLifecycleOwner,::cargandoTrans)
        viewModel.listTransacciones()
        viewModel.listTransactionResponse.observe(viewLifecycleOwner,::mostrarTransacciones)

        viewModel.getUserFullProfile()
        viewModel.getUserInformationResponse.observe(requireActivity()){ getUserFull: GetUserFullResponse ->
            lifecycleScope.launch{
                getUserFull.apply {
                    binding?.textViewDineroDisponible?.text  = darFormatoDinero(this.data.balance)
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

    private fun cargandoTrans(b: Boolean){

    }

    private fun errorTrans(error: String){
        alert.showSnackbar(error, activity = requireActivity())
    }

    private fun mostrarTransacciones(transacciones: ListaTransaccionesResponse){
        binding?.let { initRecycler(transacciones.data.transactions, it.recyclerViewHome) }
    }

    private fun guardarLogin(login: LoginResponse){
        shared.guardarSesionLogin(login)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}