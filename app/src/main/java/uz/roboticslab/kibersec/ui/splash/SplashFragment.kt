package uz.roboticslab.kibersec.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import uz.roboticslab.kibersec.R
import uz.roboticslab.kibersec.database.database.AppDatabase
import uz.roboticslab.kibersec.database.entity.ImageEntity
import uz.roboticslab.kibersec.database.entity.LessonEntity
import uz.roboticslab.kibersec.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    lateinit var firebaseFirestore: FirebaseFirestore
    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(layoutInflater)
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

        firebaseFirestore.collection("images").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document != null) {
                        val images = document.toObject(ImageEntity::class.java)
                        appDatabase.imageDao().insertImage(images)
                    }
                }
            }

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.anim)
        binding.text2.animation = animation

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().popBackStack()
            findNavController().navigate(R.id.homeFragment)
        }, 3000)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue)
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.window?.statusBarColor =
            ContextCompat.getColor(requireContext(), R.color.app_color)
    }
}