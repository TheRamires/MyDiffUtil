package ram.ramires.diffutilpractic2

import androidx.recyclerview.widget.RecyclerView

class DiffUtiFromMySelf <T> (val oldList :List<T>, val newList :List<T>) {

    fun findAddPositions(): List<T>{
        val newPositions  = arrayListOf<T>()
        for (position in newList){
            if (!oldList.contains(position)){
                newPositions.add(position)
            }
        }
        return newPositions
    }

    fun findRemovePositions():List<T>{
        val removePositions = arrayListOf<T>()
        for (position in oldList){
            if (!newList.contains(position)){
                removePositions.add(position)
            }
        }
        return removePositions
    }
    fun calculate(adapter: BaseAdapter<T>){
        val newPositions=findAddPositions()
        for (posiiton in newPositions){
            adapter.addData(posiiton)
        }
        val removePositions=findRemovePositions()
        for (posiiton in removePositions){
            adapter.removeData(posiiton)
        }
    }
}