package uz.roboticslab.kibersec.adapters

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.roboticslab.kibersec.ui.home.MainFragment
import uz.roboticslab.kibersec.ui.description.DescriptionFragment

open class PagerAdapter(
    private val fragmentActivity: FragmentActivity,
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                return MainFragment()
            }
            1 -> {
                return DescriptionFragment()
            }
        }
        return MainFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }

    open fun isViewFromObject(view: View, obj: Any): Boolean {
        TODO("Not yet implemented")
    }

    open fun getCount(): Int {
        TODO("Not yet implemented")
    }

    open fun instantiateItem(container: ViewGroup, position: Int): Any {
        TODO("Not yet implemented")
    }

    open fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {}
}