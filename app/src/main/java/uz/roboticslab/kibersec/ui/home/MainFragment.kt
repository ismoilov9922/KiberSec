package uz.roboticslab.kibersec.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager
import uz.roboticslab.kibersec.adapters.ImagesAdapter
import uz.roboticslab.kibersec.adapters.LessonAdapter
import uz.roboticslab.kibersec.R
import uz.roboticslab.kibersec.databinding.FragmentMainBinding
import uz.roboticslab.kibersec.models.TitleData
import uz.roboticslab.kibersec.utils.LoadList
import uz.roboticslab.kibersec.utils.NetworkHelper
import uz.roboticslab.kibersec.utils.errorToast

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var lessonAdapter: LessonAdapter
    lateinit var list: ArrayList<TitleData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        list = LoadList.getTitleList() as ArrayList<TitleData>
        lessonAdapter = LessonAdapter(list, object : LessonAdapter.OnCLickListener {
            override fun continueBtn(titleData: TitleData, position: Int) {
                NetworkHelper().check {
                    if (it) {
                        if (position == 1) {
                            val bundle = Bundle()
                            bundle.putString("type", "lesson${position}")
                            findNavController().navigate(R.id.lessonFragment, bundle)
                        } else {
                            errorToast(
                                requireContext(),
                                "Bu kategroiyaga xali \\nma'lumotlar kirgizlimagan"
                            )
                        }
                    } else {
                        errorToast(
                            requireContext(),
                            "Internet mavjud emas!!"
                        )
                    }
                }
            }
        })
        binding.subscribePro.setOnClickListener {
            findNavController().popBackStack()
            findNavController().navigate(R.id.proFragment)
        }
        binding.lessonRv.adapter = lessonAdapter
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val images = listOf(
            "https://firebasestorage.googleapis.com/v0/b/kiber-xafsizlik.appspot.com/o/images%2Fpager1.png.jpg?alt=media&token=e359703a-c274-4ce7-9158-01d9830f2b4b",
            "https://firebasestorage.googleapis.com/v0/b/kiber-xafsizlik.appspot.com/o/images%2Fpager2.png.jpg?alt=media&token=233fe0e3-57a3-4d2e-9fe0-094e96688bb1",
            "https://firebasestorage.googleapis.com/v0/b/kiber-xafsizlik.appspot.com/o/images%2Fpager3.png.jpg?alt=media&token=e7ea23de-e03a-4908-ba80-995a2ae322b6",
        )
        binding.viewPager.adapter = ImagesAdapter(requireContext(), images)
        binding.viewPager.setInterval(2000)
        binding.viewPager.setDirection(AutoScrollViewPager.Direction.RIGHT)
        binding.viewPager.setCycle(true)
        binding.viewPager.setBorderAnimation(true)
        binding.viewPager.setSlideBorderMode(AutoScrollViewPager.SlideBorderMode.TO_PARENT)
        binding.viewPager.startAutoScroll()
    }
}