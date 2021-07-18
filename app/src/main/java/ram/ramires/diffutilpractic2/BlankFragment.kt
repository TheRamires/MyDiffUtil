package ram.ramires.diffutilpractic2

import android.content.res.TypedArray
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
const val DATA="data"
class BlankFragment : Fragment() {
    lateinit var viewModel :MyViewModel
    lateinit var button : Button
    lateinit var recycler: RecyclerView
    lateinit var adapter :Adapter
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =ViewModelProvider(this).get(MyViewModel::class.java)

        setHasOptionsMenu(true)
        adapter= Adapter().apply {
            setDeletingFunction {data->
                delete(data) }
        }
        println("--------------------- on onCreate")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        println("--------------------- on onViewStateRestored")
        val id =savedInstanceState?.getString(DATA)
        id?.let {
            println("------------------------ id $id")
            editText.setText(id)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("--------------------- on onCreateView")
        val view=inflater.inflate(R.layout.fragment_blank, container, false)
        editText=view.findViewById(R.id.edit_text)
        recycler=view.findViewById(R.id.recycler)
        recycler.adapter=adapter
        button=view.findViewById(R.id.add)

        return view.rootView
    }

    override fun onStart() {
        super.onStart()
        println("--------------------- onStart")
        (activity as AppCompatActivity).supportActionBar?.title="436346"
        button.setOnClickListener {
            //adapter.addData(MyData())
            add(MyData())
        }
        viewModel.liveData.observe(viewLifecycleOwner,{ newData->
            println("newData $newData")
            val diffUtil = DiffUtiFromMySelf<MyData>(adapter.getData(), newData)
            diffUtil.calculate(adapter)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        requireActivity().menuInflater.inflate(R.menu.fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.save){
            val list=adapter.getData()
            println(list)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        println("-------------- onSaveInstanceState")
        val text=editText.text.toString()
        outState.putString(DATA, text )
    }
    fun add(position: MyData){
        viewModel.liveData.add(position)
    }
    fun delete(position: MyData){
        viewModel.liveData.delete(position)
    }
}