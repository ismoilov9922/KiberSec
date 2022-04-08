package uz.roboticslab.kibersec.ui.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import uz.roboticslab.kibersec.R
import uz.roboticslab.kibersec.databinding.FragmentSuccessBinding

class SuccessFragment : Fragment() {
    private lateinit var binding: FragmentSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSuccessBinding.inflate(layoutInflater)

        binding.exit.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
        }
        binding.subscribePro.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
        }
        onBackPressed()
        return binding.root
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