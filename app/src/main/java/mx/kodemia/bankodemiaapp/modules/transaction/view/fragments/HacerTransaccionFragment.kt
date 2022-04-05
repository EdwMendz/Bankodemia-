package mx.kodemia.bankodemiaapp.modules.transaction.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.CheckToken
import mx.kodemia.bankodemiaapp.core.DialogExpiredToken
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.core.internet.CheckInternet
import mx.kodemia.bankodemiaapp.data.model.request.MakeTransactionRequest
import mx.kodemia.bankodemiaapp.data.model.request.enummodels.TransactionType
import mx.kodemia.bankodemiaapp.data.model.response.contacts.GetSingleContactResponse
import mx.kodemia.bankodemiaapp.databinding.FragmentHacerTransaccionBinding
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarDinero
import mx.kodemia.bankodemiaapp.modules.transaction.view.dialogs.Dialogs
import mx.kodemia.bankodemiaapp.modules.transaction.viewmodel.ObtenerContactoUnicoViewModel

class HacerTransaccionFragment : Fragment() {

    private var binding: FragmentHacerTransaccionBinding? = null
    private lateinit var shared: SharedPreferencesInstance
    val viewModel: ObtenerContactoUnicoViewModel by viewModels()

    private val alert = Alerts

    private val dialog = Dialogs

    private val checkInternet = CheckInternet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHacerTransaccionBinding.inflate(inflater, container, false)

        init()

        binding?.apply {

            if (shared.obtenerModoTransaccion()){
                preparacionDatosDeposito()
                btnTransferencia.setOnClickListener{
                    if(CheckInternet.isNetworkAvailable(requireActivity())){
                        if(CheckToken.monitorToken(requireActivity(), CheckToken.obtenerHoraActual())){
                            mandarDatosTransaccionDeposito()
                        }else{
                            DialogExpiredToken.showDialogExpiredToken(requireActivity())
                        }
                    }else{
                        Alerts.showToast(requireActivity().getString(R.string.no_acceso_internet),requireActivity())
                    }
                }

            }else{
                preparacionDatosPago()
                btnTransferencia.setOnClickListener{
                    if(CheckInternet.isNetworkAvailable(requireActivity())){
                        if(CheckToken.monitorToken(requireActivity(), CheckToken.obtenerHoraActual())){
                            mandarDatosTransaccionPago()
                        }else{
                            DialogExpiredToken.showDialogExpiredToken(requireActivity())
                        }
                    }else{
                        Alerts.showToast(requireActivity().getString(R.string.no_acceso_internet),requireActivity())
                    }

                }
            }

            ImgRegreso.setOnClickListener {
                startActivity(Intent(requireActivity(), EnviarDinero::class.java))
            }
        }

        observers()

        return binding!!.root
    }

    private fun init(){
        //SharedPreferences
        shared = SharedPreferencesInstance.obtenerInstancia(requireActivity())

        viewModel.onCreate(context = requireActivity())
    }

    private fun observers(){
        viewModel.error.observe(viewLifecycleOwner,::error)
        viewModel.cargando.observe(viewLifecycleOwner,::cargando)
        viewModel.getSingleContactResponse.observe(viewLifecycleOwner,::obtenerContactoUnico)
    }

    private fun error(error: String){
        alert.showSnackbar(requireActivity().getString(R.string.no_pago_por_el_momento), activity = requireActivity())
        binding?.btnTransferencia?.isEnabled = false
    }

    private fun cargando(b: Boolean){

    }

    private fun obtenerContactoUnico(contacto: GetSingleContactResponse){
        shared.guardarContactoUnico(contacto.data.contact.user._id!!)
        binding?.btnTransferencia?.isEnabled = true
    }

    private fun validarCampos(): Boolean{
        binding?.apply {
            val monto = etMontoPago.text.toString().trim()
            val concepto = etConceptoPago.text.toString()
            return if(monto.isEmpty() && concepto.isEmpty()){
                alert.showToast(requireActivity().getString(R.string.favor_datos_solicitados),requireActivity())
                false
            }else if (monto.isNotEmpty() && concepto.isEmpty()){
                alert.showToast(requireActivity().getString(R.string.favor_concepto),requireActivity())
                false
            }else if (monto.isEmpty() && concepto.isNotEmpty()){
                alert.showToast(requireActivity().getString(R.string.favor_monto),requireActivity())
                false
            }else{
                true
            }
        }
        return false
    }

    private fun mandarDatosTransaccionPago(){
        binding?.apply {
            if(checkInternet.isNetworkAvailable(requireActivity())){
                if (validarCampos()){
                    val montoString = etMontoPago.text.toString().trim()
                    val monto: Int = montoString.toInt()
                    val id = shared.obtenerContactoUnico()
                    val makeTransaction = MakeTransactionRequest(
                        monto,
                        TransactionType.PAYMENT.toString(), //Si se quiere hacer un deposito a otra cuenta se pone PAYMENT
                        id, // Si es deposito no se pone Usuario de Destino
                        etConceptoPago.text.toString().trim()
                    )
                    dialog.showDialogTransaction(requireActivity(),makeTransaction)
                    //(context as EnviarTransferencia).viewModel.makeTransaction(makeTransaction)
                }
            }else{
                alert.showSnackbar(requireActivity().getString(R.string.favor_revisar_internet), activity = requireActivity())
            }
        }
    }

    private fun mandarDatosTransaccionDeposito(){
        binding?.apply {
            if(checkInternet.isNetworkAvailable(requireActivity())){
                if (validarCampos()){
                    val montoString = etMontoPago.text.toString().trim()
                    val monto: Int = montoString.toInt()
                    val makeTransaction = MakeTransactionRequest(
                        monto,
                        TransactionType.DEPOSIT.toString(), //Si se quiere hacer un deposito a otra cuenta se pone PAYMENT
                        null, // Si es deposito no se pone Usuario de Destino
                        etConceptoPago.text.toString().trim()
                    )
                    dialog.showDialogTransaction(requireActivity(),makeTransaction)
                    //(context as EnviarTransferencia).viewModel.makeTransaction(makeTransaction)
                }
            }else{
                alert.showSnackbar(requireActivity().getString(R.string.favor_revisar_internet), activity = requireActivity())
            }
        }
    }

    private fun preparacionDatosPago(){
        binding?.apply {
            val nombreContacto = shared.obtenerContacto().shortName
            val idContacto = shared.obtenerContacto()._id

            textViewNombreHacerTransaccion.text = nombreContacto
            textViewIdHacerTransaccion.text = idContacto

            btnTransferencia.isEnabled = false

            solicitarContactoUnico(idContacto!!)
        }
    }

    private fun preparacionDatosDeposito(){
        binding?.apply {
            textViewNombreHacerTransaccion.text = requireActivity().getString(R.string.deposito_cuenta_propia)
            textViewIdHacerTransaccion.text = shared.obtenerIdPropio()
        }
    }

    private fun solicitarContactoUnico(id: String){
        viewModel.getSingleContact(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}