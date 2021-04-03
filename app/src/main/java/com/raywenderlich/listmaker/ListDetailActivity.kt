package com.raywenderlich.listmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListDetailActivity : AppCompatActivity() {

    lateinit var listItemsRecyclerView : RecyclerView
    var list: TaskList?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)as TaskList?
        title = list?.name
    }

}


