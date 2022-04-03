package mx.kodemia.bankodemiaapp.modules.home.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import mx.kodemia.bankodemiaapp.animations.initParpadeoGuionLogo
import mx.kodemia.bankodemiaapp.databinding.FragmentServiciosBinding
import mx.kodemia.bankodemiaapp.modules.user.UserActivity

class ServiciosFragment : Fragment() {

    //View Binding
    private var binding: FragmentServiciosBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentServiciosBinding.inflate(inflater, container, false)

        binding?.apply {
            initParpadeoGuionLogo(requireContext(),imageViewGuionLogo)

            buttonUserHome.setOnClickListener {
                requireActivity().startActivity(Intent(requireActivity(), UserActivity::class.java))
            }

        }

        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}