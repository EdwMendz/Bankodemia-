package mx.kodemia.bankodemiaapp.modules.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.kodemia.bankodemiaapp.animations.initParpadeoGuionLogo
import mx.kodemia.bankodemiaapp.databinding.FragmentTarjetaBinding

class TarjetaFragment : Fragment() {

    private var binding: FragmentTarjetaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTarjetaBinding.inflate(inflater, container, false)

        binding?.apply {
            initParpadeoGuionLogo(requireContext(),imageViewGuionLogo)
        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}