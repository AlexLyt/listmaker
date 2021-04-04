package com.raywenderlich.listmaker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 * Use the [ListDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListDetailFragment : Fragment() {

    lateinit var listItemsRecyclerView: RecyclerView
    var list: TaskList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            list = it.getParcelable(MainActivity.INTENT_LIST_KEY)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
// Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_detail,
            container, false)
        view?.let {
            listItemsRecyclerView =
                it.findViewById(R.id.list_items_recyclerview)
            listItemsRecyclerView.adapter =
                list?.let { it1 -> ListItemsRecyclerViewAdapter(it1) }      // page 216
            listItemsRecyclerView.layoutManager =
                LinearLayoutManager(context)
        }
        return view
    }

    companion object {
        private const val ARG_LIST = "list"
        fun newInstance(list: TaskList): ListDetailFragment {
            val fragment = ListDetailFragment()
            val args = Bundle()
            args.putParcelable(ARG_LIST, list)
            fragment.arguments = args
            return fragment
        }
    }

    fun addTask(item: String) {
        list?.tasks?.add(item)
        val listRecyclerAdapter = listItemsRecyclerView.adapter as
                ListItemsRecyclerViewAdapter
        listRecyclerAdapter.list = list!!
        listRecyclerAdapter.notifyDataSetChanged()
    }
}



