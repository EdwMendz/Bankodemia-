package mx.kodemia.bankodemiaapp.modules.home.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.kodemia.bankodemiaapp.databinding.FragmentServiciosBinding
import mx.kodemia.bankodemiaapp.modules.home.viewmodel.ServiciosFragmentViewModel

class ServiciosFragment : Fragment() {

    private var _binding: FragmentServiciosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ServiciosFragmentViewModel::class.java)

        _binding = FragmentServiciosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            //textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}