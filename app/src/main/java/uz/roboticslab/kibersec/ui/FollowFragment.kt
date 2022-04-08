package uz.roboticslab.kibersec.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.roboticslab.kibersec.R
import uz.roboticslab.kibersec.databinding.FragmentFollowBinding

class FollowFragment : Fragment() {
    private lateinit var binding: FragmentFollowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(layoutInflater)
        binding.backHome.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.instagram.setOnClickListener {
            val uri: Uri =
                Uri.parse("http://www.instagram.com")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.facebook.setOnClickListener {
            val uri: Uri =
                Uri.parse("http://www.facebook.com")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.linkedLn.setOnClickListener {
            val uri: Uri =
                Uri.parse("http://www.linkedLn.com")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.twitter.setOnClickListener {
            val uri: Uri =
                Uri.parse("http://www.twitter.com")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        binding.playStore.setOnClickListener {
            val uri: Uri =
                Uri.parse("https://play.google.com/store")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)
        }
        onBackPressed()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue)
    }

    private fun onBackPressed() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
}