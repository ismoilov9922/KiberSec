package uz.roboticslab.kibersec.ui.setting

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.suke.widget.SwitchButton
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.findNavController
import uz.roboticslab.kibersec.databinding.FragmentSettingsBinding
import uz.roboticslab.kibersec.databinding.InfoAppBinding
import uz.roboticslab.kibersec.utils.Settings

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var sharedPreferencesTheme: SharedPreferences
    private lateinit var sharedPreferencesLan: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater)
        sharedPreferencesTheme =
            activity?.getSharedPreferences(Settings.PREF_NAME, Context.MODE_PRIVATE)!!
        sharedPreferencesLan =
            activity?.getSharedPreferences(Settings.PREF_LAN_NAME, Context.MODE_PRIVATE)!!
        binding.switchBtn.toggle(true)
        binding.switchBtn.setShadowEffect(true)
        binding.switchBtn.isEnabled = true
        binding.switchBtn.setEnableEffect(true)
        checkNightModeActivity()
        binding.switchBtn.setOnCheckedChangeListener(SwitchButton.OnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                saveNightMode(true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                saveNightMode(false)
                // activity?.recreate()
            }
        })
        binding.share.setOnClickListener {
            intentMessageTelegram("https://t.me/ismoilov9922")
        }
        binding.info.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            val binding1 = InfoAppBinding.inflate(inflater, null, false)
            builder.setView(binding1.root)
            val alertDialog = builder.create()
            alertDialog.show()
        }
        binding.switchBtn.setOnCheckedChangeListener(SwitchButton.OnCheckedChangeListener { view, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                saveNightMode(true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                saveNightMode(false)
                // activity?.recreate()
            }
        })
        onBackPressed()
        return binding.root
    }

    private fun intentMessageTelegram(msg: String?) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Kiber Xafsizlik")
        intent.putExtra(Intent.EXTRA_TEXT, msg)
        context?.startActivity(Intent.createChooser(intent, "choose one"))
    }

    private fun onBackPressed() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
    private fun saveNightMode(b: Boolean) {
        var editor = sharedPreferencesTheme.edit()
        editor.putBoolean(Settings.NIGHT_MODE_KEY, b)
        editor.apply()
    }

    private fun checkNightModeActivity() {
        binding.switchBtn.isChecked =
            sharedPreferencesTheme.getBoolean(Settings.NIGHT_MODE_KEY, false)
    }
}