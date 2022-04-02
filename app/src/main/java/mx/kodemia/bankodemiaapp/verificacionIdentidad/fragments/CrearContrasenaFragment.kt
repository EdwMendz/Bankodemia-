package mx.kodemia.bankodemiaapp.verificacionIdentidad.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.Alerts
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.data.model.request.SignUpResquest
import mx.kodemia.bankodemiaapp.data.model.request.enummodels.DocumentType
import mx.kodemia.bankodemiaapp.databinding.FragmentCrearContrasenaBinding
import mx.kodemia.bankodemiaapp.verificacionIdentidad.ImageConverter
import mx.kodemia.bankodemiaapp.verificacionIdentidad.contrasena.Contrasena

class CrearContrasenaFragment : Fragment() {

    private var binding: FragmentCrearContrasenaBinding? = null
    private lateinit var shared: SharedPreferencesInstance
    private val imageConverter = ImageConverter
    private lateinit var tipoDocumento: DocumentType

    //Alertas por medio de Toast o SnackBar
    private val alert = Alerts

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCrearContrasenaBinding.inflate(inflater, container, false)

        init()

        //(context as Contrasena).viewModel.signUp()

        binding?.apply {

            buttonPassword.setOnClickListener {

                val pathFoto = shared.obtenerFotoArchivo()
                val archivoFotoBase64 = imageConverter.PathToBase64(pathFoto!!)

                when(shared.obtenerTipoDocumento()){
                    getString(R.string.ine) ->{tipoDocumento = DocumentType.INE}
                    getString(R.string.pasaporte) ->{tipoDocumento = DocumentType.PASSPORT}
                    getString(R.string.documentoMigratorio) ->{tipoDocumento = DocumentType.MIGRATION_FORM}
                }

                val signUp =
                    SignUpResquest(
                        "miguelitope@kodemia.com",
                        "Miguelitope",
                        "Ponchitope",
                        "Loverpe",
                        "1987-09-27",
                        "MiguelitoLoverPe",
                        "+523315246774",
                        archivoFotoBase64,
                        tipoDocumento.toString()
                    )
                mandarDatosSignUp(signUp)
            }
        }
        //observers()

        return binding!!.root
    }

    private fun init(){
        //SharedPreferences
        shared = SharedPreferencesInstance.obtenerInstancia(requireActivity())
    }

    private fun validarContrasena(){
        binding?.apply {
            return if(tietPassword.text.toString().isEmpty()){
                tilPassword.error = getString(R.string.campo_vacio)
            }else{
                tilPassword.isErrorEnabled = false

            }
        }
    }

    private fun validarContrasenaDos(){
        binding?.apply {
            return if(tietPasswordConfirm.text.toString().isEmpty()){
                tilPasswordConfirm.error = getString(R.string.campo_vacio)
            }else{
                tilPasswordConfirm.isErrorEnabled = false
            }
        }
    }

    private fun validarSimilutud(){
        binding?.apply {
            val textoPassword: String = tietPassword.text?.trim().toString()
            val textoPassConfirm: String = tietPasswordConfirm.text?.trim().toString()
            if (textoPassword != textoPassConfirm){
                alert.showToast("Contrasenias Diferentes",requireActivity())
                tietPassword.setText("")
                tietPasswordConfirm.setText("")
            }else{

            }
        }
    }

    private fun mandarDatosSignUp(signUpResquest: SignUpResquest) {
        (context as Contrasena).viewModel.signUp(signUpResquest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}