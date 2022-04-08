package uz.roboticslab.kibersec.ui.lesson

import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.core.view.marginTop
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import uz.roboticslab.kibersec.R
import uz.roboticslab.kibersec.database.database.AppDatabase
import uz.roboticslab.kibersec.database.entity.ImageEntity
import uz.roboticslab.kibersec.database.entity.LessonEntity
import uz.roboticslab.kibersec.databinding.DialogBackBinding
import uz.roboticslab.kibersec.databinding.FragmentLessonBinding

class LessonFragment : Fragment() {
    private lateinit var binding: FragmentLessonBinding
    private lateinit var appDatabase: AppDatabase
    private lateinit var imageList: ArrayList<ImageEntity>
    private lateinit var lessonList: ArrayList<LessonEntity>
    private var music: MediaPlayer = MediaPlayer()
    private var themePosition: Int = 1
    private var countText = 0
    private var type: String = "lesson1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLessonBinding.inflate(layoutInflater)
        init()
        binding.continueTv.setOnClickListener {
            addView(lessonList[countText])
        }
        binding.next.setOnClickListener {
            if (imageList.size == themePosition) {
                findNavController().popBackStack()
                findNavController().navigate(R.id.testFragment)
            } else {
                countText = 0
                themePosition++
                binding.next.visibility = View.GONE
                binding.continueTv.visibility = View.VISIBLE
                binding.textView.removeAllViews()
                lessonList = appDatabase.lessonDao()
                    .getLessonType(type, themePosition.toString()) as ArrayList<LessonEntity>
                addView(lessonList[countText])
                addStoryView(themePosition)
            }
        }
        binding.carnay.setOnClickListener {
            music.pause()
        }
        binding.close.setOnClickListener {
            backDialog()
        }
        binding.share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, "Cyber Security")
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "https://bit.ly/2Q7LeIH"
            )
            startActivity(Intent.createChooser(intent, "choose one"))
        }
        return binding.root
    }

    private fun init() {
        appDatabase = AppDatabase.getDatabase(requireContext())
        type = arguments?.getString("type") as String
        imageList = ArrayList<ImageEntity>()
        imageList = appDatabase.imageDao().getImageListType(type) as ArrayList<ImageEntity>
        addStoryView(themePosition)
        lessonList = ArrayList<LessonEntity>()
        lessonList = appDatabase.lessonDao()
            .getLessonType(type, themePosition.toString()) as ArrayList<LessonEntity>
        addView(lessonList[countText])
    }

    private fun addView(lessonData: LessonEntity) {
        music.stop()
        val cricketerView: View = layoutInflater.inflate(R.layout.item_text_view, null, false)
        val textView = cricketerView.findViewById<View>(R.id.text_item_1) as TextView
        textView.marginTop.compareTo(10)
        music = MediaPlayer.create(requireContext(), Uri.parse(lessonData.audio))
        music.start()
        textView.text = lessonData.text
        binding.textView.addView(cricketerView)
        if (countText == lessonList.size - 1) {
            binding.continueTv.visibility = View.GONE
            binding.next.visibility = View.VISIBLE
            countText = 0
        } else {
            countText++
        }
    }

    private fun addStoryView(themePosition: Int) {
        Picasso.get().load(imageList[themePosition - 1].imageUrl).into(binding.animationView)
        binding.story.removeAllViews()
        for (i in 1..imageList.size) {
            if (themePosition >= i) {
                val storyView: View = layoutInflater.inflate(R.layout.item_story, null, false)
                binding.story.addView(storyView)
            } else {
                val storyGrayView: View =
                    layoutInflater.inflate(R.layout.item_story_hide, null, false)
                binding.story.addView(storyGrayView)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        onBackPressed()
    }

    override fun onPause() {
        super.onPause()
        music.pause()
    }

    override fun onStart() {
        super.onStart()
        activity?.window?.statusBarColor = ContextCompat.getColor(requireContext(), R.color.blue)
    }

    override fun onDestroy() {
        super.onDestroy()
        music.stop()
    }

    private fun backDialog() {
        val dialogBinding =
            DialogBackBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val alertDialog = dialogBuilder.create()
        alertDialog.setView(dialogBinding.root)
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialogBinding.cancelBtn.setOnClickListener {
            alertDialog.dismiss()
        }
        dialogBinding.quit.setOnClickListener {
            alertDialog.dismiss()
            findNavController().popBackStack()
        }
        alertDialog.show()
    }

    private fun onBackPressed() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backDialog()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }
}