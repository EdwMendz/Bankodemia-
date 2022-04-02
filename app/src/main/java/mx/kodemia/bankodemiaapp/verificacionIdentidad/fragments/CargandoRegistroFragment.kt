package mx.kodemia.bankodemiaapp.verificacionIdentidad.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mx.kodemia.bankodemiaapp.databinding.FragmentCargandoRegistroBinding

class CargandoRegistroFragment : Fragment() {

    private var binding: FragmentCargandoRegistroBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCargandoRegistroBinding.inflate(inflater, container, false)

        //(context as Contrasena).error("")

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}