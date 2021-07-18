package ram.ramires.diffutilpractic2

interface BaseAdapter<T> {
    abstract var list: ArrayList<MyData>
    fun addData( myData: T)
    fun removeData( myData: T)
}