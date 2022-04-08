package uz.roboticslab.kibersec.ui.description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.roboticslab.kibersec.databinding.FragmentDescriptionBinding

class DescriptionFragment : Fragment() {
    private lateinit var binding: FragmentDescriptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(layoutInflater)

        return binding.root
    }
}