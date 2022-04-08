package uz.roboticslab.kibersec.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import uz.roboticslab.kibersec.MainActivity
import uz.roboticslab.kibersec.R
import uz.roboticslab.kibersec.adapters.PagerAdapter
import uz.roboticslab.kibersec.database.database.AppDatabase
import uz.roboticslab.kibersec.database.entity.LessonEntity
import uz.roboticslab.kibersec.databinding.FragmentHomeBinding
import uz.roboticslab.kibersec.databinding.TabViewBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var pagerAdapter: PagerAdapter
    lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var appDatabase: AppDatabase
    var doubleBackToExitPressedOnce = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        pagerAdapter = PagerAdapter(requireActivity())
        binding.mainViewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tablayout, binding.mainViewPager) { tab, position ->
            val bindingTab = TabViewBinding.inflate(layoutInflater)
            tab.customView = bindingTab.root
            when (position) {
                0 -> bindingTab.batTv.text = "Darsliklar"
                1 -> bindingTab.batTv.text = "Tafsif"
            }
        }.attach()
        binding.drawerMenu.setOnClickListener {
            binding.mainDrawerLayout.openDrawer(Gravity.LEFT)
        }
        firebaseFirestore = FirebaseFirestore.getInstance()
        appDatabase = AppDatabase.getDatabase(requireContext())

        FirebaseFirestore.getInstance().collection("lesson1").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document != null) {
                        val lesson = document.toObject(LessonEntity::class.java)
                        appDatabase.lessonDao().insertLesson(lesson)
                    }
                }
            }

        binding.mainNavigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.settings -> {
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
                    findNavController().navigate(R.id.settingsFragment)
                }
                R.id.rate_us -> {
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
                    val uri: Uri =
                        Uri.parse("https://play.google.com/store/apps/details?id=cyber.security.learn.programming.coding.hacking.software.development.cybersecurity")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
                R.id.follow -> {
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
                    findNavController().navigate(R.id.followFragment)
                }
                R.id.exit -> {
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
                    (activity as MainActivity).onBackPressed()
                }
            }
            false
        }
        binding.tryPro.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.proFragment)
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.app_color)
    }
}