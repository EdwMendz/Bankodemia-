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

                if(validarContrasena() && validarContrasenaDos() && validarSimilutud() && validarLongitud() && validarConsecutivos()){
                    val pathFoto = shared.obtenerFotoArchivo()
                    val archivoFotoBase64 = imageConverter.PathToBase64(pathFoto!!)

                    when(shared.obtenerTipoDocumento()){
                        getString(R.string.ine) ->{tipoDocumento = DocumentType.INE}
                        getString(R.string.pasaporte) ->{tipoDocumento = DocumentType.PASSPORT}
                        getString(R.string.documentoMigratorio) ->{tipoDocumento = DocumentType.MIGRATION_FORM}
                    }

                    val signUp =
                        SignUpResquest(
                            "miguelitopa@kodemia.com",
                            "Miguelitopa",
                            "Ponchitopa",
                            "Loverpa",
                            "1987-09-27",
                            tietPassword.text.toString(),
                            "+523315246987",
                            archivoFotoBase64,
                            tipoDocumento.toString()
                        )
                    mandarDatosSignUp(signUp)
                }
            }
        }


        return binding!!.root
    }

    private fun init(){
        //SharedPreferences
        shared = SharedPreferencesInstance.obtenerInstancia(requireActivity())
    }

    private fun validarContrasena():Boolean{
        binding?.apply {
            return if(tietPassword.text.toString().isEmpty()){
                tilPassword.error = getString(R.string.campo_vacio)
                false
            }else{
                tilPassword.isErrorEnabled = false
                true
            }
        }
        return false
    }

    private fun validarContrasenaDos(): Boolean{
        binding?.apply {
            return if(tietPasswordConfirm.text.toString().isEmpty()){
                tilPasswordConfirm.error = getString(R.string.campo_vacio)
                false
            }else{
                tilPasswordConfirm.isErrorEnabled = false
                true
            }
        }
        return false
    }

    private fun validarSimilutud(): Boolean{
        binding?.apply {
            val textoPassword: String = tietPassword.text?.trim().toString()
            val textoPassConfirm: String = tietPasswordConfirm.text?.trim().toString()
            return if (textoPassword != textoPassConfirm){
                alert.showToast("Contrasenias Diferentes",requireActivity())
                tietPassword.setText("")
                tietPasswordConfirm.setText("")
                false
            }else{
                true
            }
        }
        return false
    }

    private fun validarLongitud(): Boolean{
        binding?.apply {
            val textoPassword: String = tietPassword.text?.trim().toString()
            val textoPassConfirm: String = tietPasswordConfirm.text?.trim().toString()
            return if(textoPassword.length < 6 || textoPassConfirm.length < 6){
                alert.showToast("Se necesitan minimo 6 caracteres para la contraseña", requireActivity())
                false
            }else{
                true
            }
        }
        return false
    }

    private fun validarConsecutivos(): Boolean{
        binding?.apply {
            val textoPassword: String = tietPassword.text?.trim().toString()
            var contador = 1
            var coincidencias = 0
            for (item in textoPassword){
                if(contador == textoPassword.length){
                    break
                }else{
                    if(item.isDigit()){
                        if(item.code == textoPassword[contador].code-1){
                            coincidencias++
                        }else if (item.code == textoPassword[contador].code+1){
                            coincidencias++
                        }
                    }else{
                        if (item == textoPassword[contador]){
                            coincidencias++
                        }
                    }
                    contador++
                }
            }
            if(coincidencias>=2){
                alert.showToast("No se permiten caracteres consecutivos o repetidos", requireContext())
                return false
            }else{
                return true
            }
        }
        return false
    }

    private fun mandarDatosSignUp(signUpResquest: SignUpResquest) {
        (context as Contrasena).viewModel.signUp(signUpResquest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}