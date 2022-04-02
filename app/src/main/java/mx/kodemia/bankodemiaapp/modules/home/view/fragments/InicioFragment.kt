package mx.kodemia.bankodemiaapp.modules.home.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.bankodemiaapp.EnviarDinero
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
import mx.kodemia.bankodemiaapp.modules.identity_verification.view.VerificacionIdentidadActivity

class InicioFragment : Fragment() {

    //View Binding
    private var binding: FragmentInicioBinding? = null

    //viewModel
    val viewModel: InicioFragmentViewModel by viewModels()

    //SharedPreferences
    lateinit var shared : SharedPreferencesInstance

    //Alertas por medio de Toast o SnackBar
    private val alert = Alerts

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInicioBinding.inflate(inflater, container, false)

        init()

        binding?.apply {
            textViewFecha.text = darFormatoFechaActual()
            initParpadeoGuionLogo(requireContext(),imageViewGuionLogo)

            buttonEnviarHome.setOnClickListener {
                val intent = Intent(activity,EnviarDinero::class.java)
                startActivity(intent)
            }

            buttonRecibirHome.setOnClickListener {
                Toast.makeText(requireActivity(),"Se necesita una vista para recibir dinero", Toast.LENGTH_LONG).show()
            }

        }

        observers()

        return binding!!.root
    }

    //Inicializacion de SharedPreferences y dar el contexto a ViewModel para recibir el servicio
    fun init(){
        shared = SharedPreferencesInstance.obtenerInstancia(requireActivity())

        viewModel.onCreate(context = requireActivity())
    }

    //Observers pendientes a cambios en los datos por parte de MVVM
    private fun observers(){

        viewModel.errorTrans.observe(viewLifecycleOwner,::errorTrans)
        viewModel.cargandoTrans.observe(viewLifecycleOwner,::cargandoTrans)
        viewModel.listTransacciones()
        viewModel.listTransactionResponse.observe(viewLifecycleOwner,::mostrarTransacciones)

        viewModel.getUserFullProfile()
        viewModel.getUserInformationResponse.observe(viewLifecycleOwner,::mostrarInfoUsuario)

    }

    //Inicializacion de RecyclerView que contiene la informacion de las transacciones
    private fun initRecycler(lista: MutableList<Transaccion>, recyclerView: RecyclerView){
        val adaptador = TransaccionesAdapter(requireActivity(),lista)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = adaptador
        }
    }

    //Funcion para observer de carga cuando se esta haciendo la solicitud a la API
    private fun cargandoTrans(b: Boolean){

    }

    //Funcion para observer de muestra de error en caso de fallo con la API
    private fun errorTrans(error: String){
        if(error.isNotEmpty()){
            alert.showSnackbar(error, activity = requireActivity())
        }
    }

    //Funcion para observer para llenar el RecyclerView con la informacion obtenida de la API
    private fun mostrarTransacciones(transacciones: ListaTransaccionesResponse){
        binding?.let { initRecycler(transacciones.data.transactions, it.recyclerViewHome) }
    }

    private fun mostrarInfoUsuario(userFull: GetUserFullResponse){
        binding?.textViewDineroDisponible?.text  = darFormatoDinero(userFull.data.balance)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}