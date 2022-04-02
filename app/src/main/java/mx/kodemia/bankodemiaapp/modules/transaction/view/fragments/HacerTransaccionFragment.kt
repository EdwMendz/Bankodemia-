package mx.kodemia.bankodemiaapp.modules.transaction.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.kodemia.bankodemiaapp.R
import mx.kodemia.bankodemiaapp.core.SharedPreferencesInstance
import mx.kodemia.bankodemiaapp.databinding.FragmentCrearContrasenaBinding
import mx.kodemia.bankodemiaapp.databinding.FragmentHacerTransaccionBinding
import mx.kodemia.bankodemiaapp.modules.transaction.view.EnviarDinero

class HacerTransaccionFragment : Fragment() {

    private var binding: FragmentHacerTransaccionBinding? = null
    private lateinit var shared: SharedPreferencesInstance

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHacerTransaccionBinding.inflate(inflater, container, false)

        init()

        binding?.apply {
            ImgRegreso.setOnClickListener {
                startActivity(Intent(requireActivity(), EnviarDinero::class.java))
            }

            btnTransferencia.setOnClickListener {
                //Hacer la transaccion
            }
        }

        return binding!!.root
    }

    private fun init(){
        //SharedPreferences
        shared = SharedPreferencesInstance.obtenerInstancia(requireActivity())
    }

    private fun mandarDatosTransaccion(){

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}