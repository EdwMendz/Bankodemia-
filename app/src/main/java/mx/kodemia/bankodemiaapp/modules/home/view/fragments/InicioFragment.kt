package mx.kodemia.bankodemiaapp.modules.home.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.animations.initParpadeoGuionLogo
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.DialogExpiredToken
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.formatos.darFormatoDinero
import mx.kodemia.bankodemiaapp.data.model.response.listatransacciones.ListaTransaccionesResponse
import mx.kodemia.bankodemiaapp.data.model.response.listatransacciones.Transaccion
import mx.kodemia.bankodemiaapp.data.model.response.user.GetUserFullResponse
import mx.kodemia.bankodemiaapp.databinding.FragmentInicioBinding
import mx.kodemia.bankodemiaapp.formatos.darFormatoFechaActual
import mx.kodemia.bankodemiaapp.modules.home.view.adapter.TransaccionesAdapter
import mx.kodemia.bankodemiaapp.modules.home.viewmodel.InicioFragmentViewModel
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarDinero
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarTransferencia
import mx.kodemia.bankodemiaapp.modules.user.UserActivity

class InicioFragment : Fragment() {

    //View Binding
    private var binding: FragmentInicioBinding? = null

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

        init()

        binding?.apply {
            textViewFecha.text = darFormatoFechaActual()
            initParpadeoGuionLogo(requireContext(),imageViewGuionLogo)

            buttonEnviarHome.isEnabled = false
            buttonRecibirHome.isEnabled = false

            buttonEnviarHome.setOnClickListener {
                shared.guardarModoTransaccion(false)
                val intent = Intent(activity, EnviarDinero::class.java)
                startActivity(intent)
            }

            buttonRecibirHome.setOnClickListener {
                shared.guardarModoTransaccion(true)
                val intent = Intent(activity, EnviarTransferencia::class.java)
                startActivity(intent)
            }

            buttonUserHome.setOnClickListener {
                requireActivity().startActivity(Intent(requireActivity(),UserActivity::class.java))
            }

            returnNativo()

        }

        if(CheckInternet.isNetworkAvailable(requireActivity())){
            if(CheckToken.monitorToken(requireActivity(), CheckToken.obtenerHoraActual())){
                solicitarInformacionDeUsuario()
                solicitarTransacciones()
            }else{
                DialogExpiredToken.showDialogExpiredToken(requireActivity())
            }
        }else{
            Alerts.showToast(requireActivity().getString(R.string.no_acceso_internet),requireActivity())
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
        viewModel.listTransactionResponse.observe(viewLifecycleOwner,::mostrarTransacciones)

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
        //No se requiere ninguna accion aqui
    }

    //Funcion para observer de muestra de error en caso de fallo con la API
    private fun errorTrans(error: String){
        if(error.isNotEmpty()){
            Alerts.showSnackbar(error, activity = requireActivity())
        }
    }

    //Funcion para observer para llenar el RecyclerView con la informacion obtenida de la API
    private fun mostrarTransacciones(transacciones: ListaTransaccionesResponse){
        binding?.apply {
            progressBarTransacciones.isVisible = false
            textViewprogressBarTransacciones.isVisible = false
            initRecycler(transacciones.data.transactions, recyclerViewHome)
        }
    }

    private fun mostrarInfoUsuario(userFull: GetUserFullResponse){
        shared.guardarIdPropio(userFull.data.user._id!!)
        shared.guardarInformacionCompletaUsuario(userFull.data.user)
        binding?.apply {
            buttonEnviarHome.isEnabled = true
            buttonRecibirHome.isEnabled = true
            textViewDineroDisponible.text = darFormatoDinero(userFull.data.balance)
        }

    }

    private fun solicitarInformacionDeUsuario(){
        viewModel.getUserFullProfile()
    }

    private fun solicitarTransacciones(){
        viewModel.listTransacciones()
    }

    private fun returnNativo(){
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}