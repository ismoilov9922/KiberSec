package uz.roboticslab.kibersec.ui.click

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import uz.roboticslab.kibersec.databinding.FragmentClickBinding


class ClickFragment : Fragment() {
    private lateinit var binding: FragmentClickBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClickBinding.inflate(layoutInflater)
        binding.click.setOnClickListener {
            val uri = "tel:*880*3*1*"
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse(uri)
            startActivity(intent)
        }
        return binding.root
    }
}