package uz.roboticslab.kibersec.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.roboticslab.kibersec.databinding.ItemLessonBinding
import uz.roboticslab.kibersec.models.TitleData

class LessonAdapter(private val list: List<TitleData>, private val listener: OnCLickListener) :
    RecyclerView.Adapter<LessonAdapter.Vh>() {

    inner class Vh(private val itemBinding: ItemLessonBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(lessonData: TitleData, position: Int) {
            itemBinding.title.text = lessonData.title
            itemBinding.title2.text = lessonData.title
            if (position == 0) {
                itemBinding.mainCard.visibility = View.VISIBLE
                itemBinding.title2.isSelected = true
                itemBinding.item11.visibility = View.GONE
            }
            itemBinding.item11.setOnClickListener {
                itemBinding.mainCard.visibility = View.VISIBLE
                itemBinding.title2.isSelected = true
                itemBinding.item11.visibility = View.GONE
            }
            itemBinding.itemHide.setOnClickListener {
                itemBinding.mainCard.visibility = View.GONE
                itemBinding.item11.visibility = View.VISIBLE
            }
            itemBinding.continueBtn.setOnClickListener {
                listener.continueBtn(lessonData, position+1)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemLessonBinding.inflate(LayoutInflater.from(parent.context), null, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnCLickListener {
        fun continueBtn(titleData: TitleData, position: Int)
    }
}