package ram.ramires.diffutilpractic2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView

class Adapter : RecyclerView.Adapter<Adapter.Item>(), BaseAdapter<MyData> {
    override var list: ArrayList<MyData> = arrayListOf()
    lateinit var deletingFun : (data: MyData)->Unit

    fun setData( list: ArrayList<MyData>){
        this.list=list
    }

    fun setDeletingFunction(function: (data: MyData)->Unit){
        deletingFun=function
    }

    override fun addData( myData: MyData){
        this.list.add(myData)
        notifyItemInserted(list.indexOf(myData))
    }
    override fun removeData( myData: MyData){
        val index=list.indexOf(myData)
        list.removeAt(index)
        notifyItemRemoved(index)

    }

    fun getData(): List<MyData>{
        return list
    }
    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView1: TextView
        val textView2: TextView
        val delete: ImageButton
        init {
            textView1=itemView.findViewById(R.id.text1)
            textView2=itemView.findViewById(R.id.text2)
            delete=itemView.findViewById(R.id.delete)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item, parent,false)
        return Item(view)
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        val item= list[position]
        holder.textView1.text=item.name
        holder.textView2.text=item.weight
        holder.delete.setOnClickListener {
            /*notifyItemRemoved(list.indexOf(item))
            list.remove(item)*/
            deletingFun(item)
        }
        holder.textView1.doAfterTextChanged { item.name=it.toString() }
        holder.textView2.doAfterTextChanged {
            item.weight=it.toString()  }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}