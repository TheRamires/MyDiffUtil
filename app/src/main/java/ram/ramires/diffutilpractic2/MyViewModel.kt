package ram.ramires.diffutilpractic2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class MyViewModel: ViewModel() {
    val liveData = MutableLiveData<ArrayList<MyData>>(arrayListOf())

}
fun <T>MutableLiveData<ArrayList<T>>.add(position: T){
    var newList  =this.value
    if (newList!=null){
        newList.add(position)
    }else {
        newList= arrayListOf(position)
    }
    this.value=newList
}
fun <T>MutableLiveData<ArrayList<T>>.delete(position: T){
    var newList  =this.value
    if (newList!=null && newList.contains(position)){
        newList.remove(position)
    }else {
        newList= arrayListOf(position)
    }
    this.value=newList
}