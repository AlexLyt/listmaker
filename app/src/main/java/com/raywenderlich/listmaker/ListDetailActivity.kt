package com.raywenderlich.listmaker

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListDetailActivity : AppCompatActivity() {
    lateinit var addTaskButton: FloatingActionButton
    lateinit var listItemsRecyclerView: RecyclerView
    var list: TaskList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY) as TaskList?
        title = list?.name
        listItemsRecyclerView = findViewById(R.id.list_items_recyclerview)
        listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list!!)
        listItemsRecyclerView.layoutManager = LinearLayoutManager(this)

        addTaskButton = findViewById(R.id.add_task_button)
        addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }
    }

    private fun showCreateTaskDialog() {
//1
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT
//2
        AlertDialog.Builder(this)
            .setTitle(R.string.task_to_add)
            .setView(taskEditText)
            .setPositiveButton(R.string.add_task) { dialog, _ ->
// 3
                val task = taskEditText.text.toString()
                list?.tasks?.add(task)
// 4
                val recyclerAdapter = listItemsRecyclerView.adapter
                        as ListItemsRecyclerViewAdapter
                list?.tasks?.size?.minus(1)?.let { recyclerAdapter.notifyItemInserted(it) }
//5
                dialog.dismiss()
            }
//6
            .create()
            .show()
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }

}



