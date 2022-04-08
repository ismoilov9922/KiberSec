package uz.roboticslab.kibersec.ui.pro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import uz.roboticslab.kibersec.R
import uz.roboticslab.kibersec.databinding.FragmentProBinding

class ProFragment : Fragment() {
    private lateinit var binding: FragmentProBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProBinding.inflate(layoutInflater)

        binding.exit.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        onBackPressed()
    }

    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.try_pro)
    }

    private fun onBackPressed() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
                findNavController().navigate(R.id.homeFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
}