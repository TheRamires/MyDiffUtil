package ram.ramires.diffutilpractic2

import androidx.recyclerview.widget.DiffUtil

class MyDiffCallBacker(val oldList: List<MyData>, val newList :List<MyData>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size

    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem=oldList[oldItemPosition]
        val newItem=newList[newItemPosition]
        return oldItem.id!=newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem=oldList[oldItemPosition]
        val newItem=newList[newItemPosition]
        return oldItem.name!=newItem.name
    }
}