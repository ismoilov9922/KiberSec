package uz.roboticslab.kibersec.ui.test

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import uz.roboticslab.kibersec.R
import uz.roboticslab.kibersec.databinding.DialogAnswerBinding
import uz.roboticslab.kibersec.databinding.FragmentTestBinding
import uz.roboticslab.kibersec.models.Question
import uz.roboticslab.kibersec.utils.LoadList

class TestFragment : Fragment() {
    private lateinit var binding: FragmentTestBinding
    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var music: MediaPlayer = MediaPlayer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTestBinding.inflate(layoutInflater)
        mQuestionList = LoadList.getQuestions()
        setQuestion()
        val music = MediaPlayer.create(
            requireContext(),
            Uri.parse("https://firebasestorage.googleapis.com/v0/b/cyber-security-57a65.appspot.com/o/question1.ogg?alt=media&token=5a735103-9ff3-4402-a179-622350fc034b")
        )
        music.start()
        binding.tvOptionOne.setOnClickListener {
            selectedOptionView(binding.tvOptionOne, 1)
            mSelectedOptionPosition = 1
        }
        binding.tvOptionTwo.setOnClickListener {
            selectedOptionView(binding.tvOptionTwo, 2)
            mSelectedOptionPosition = 2
        }
        binding.tvOptionThree.setOnClickListener {
            selectedOptionView(binding.tvOptionThree, 3)
            mSelectedOptionPosition = 3
        }
        binding.tvOptionFour.setOnClickListener {
            selectedOptionView(binding.tvOptionFour, 4)
            mSelectedOptionPosition = 4
        }
        binding.btnSubmit.setOnClickListener {
            if (mSelectedOptionPosition == 0) {
                mCurrentPosition++
                when {
                    mCurrentPosition <= mQuestionList!!.size -> {
                        setQuestion()
                    }
                    else -> {
                        findNavController().popBackStack()
                        findNavController().navigate(R.id.successFragment)
                    }
                }
            } else {
                val question = mQuestionList?.get(mCurrentPosition - 1)
                if (question!!.correctOption == mSelectedOptionPosition) {
                    dialogAnswer(true)
                } else {
                    dialogAnswer(false)
                }
                if (mCurrentPosition == mQuestionList!!.size) {
                    binding.btnSubmit.text = "Yakunlash"
                } else {
                    binding.btnSubmit.text = "Keyingisiga O'tish"
                }
                mSelectedOptionPosition = 0
            }
        }
        onBackPressed()
        return binding.root
    }

    private fun setQuestion() {
        val question = mQuestionList!![mCurrentPosition - 1]
        defaultOptionsView()
        binding.tvQuestion.text = question.question
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)
        for (option in options) {
            option.setTextColor(Color.parseColor("#037AD8"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            requireContext(),
            R.drawable.selected_option_border_bg
        )
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

    private fun dialogAnswer(isCurrent: Boolean) {
        val dialogBinding =
            DialogAnswerBinding.inflate(layoutInflater)
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val alertDialog = dialogBuilder.create()
        if (isCurrent) {
            dialogBinding.animationView.setAnimation(R.raw.success_answer)
            dialogBinding.resultTv.text = "To'ri Javob"
            music = MediaPlayer.create(requireContext(), R.raw.success)
            music.start()
        } else {
            music = MediaPlayer.create(requireContext(), R.raw.error_3)
            music.start()
        }
        alertDialog.setView(dialogBinding.root)
        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        Handler(Looper.getMainLooper()).postDelayed({
            alertDialog.dismiss()
        }, 2000)
        alertDialog.show()
    }
}