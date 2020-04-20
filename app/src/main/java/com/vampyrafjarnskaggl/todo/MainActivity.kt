package com.vampyrafjarnskaggl.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal lateinit var itemlist: ArrayList<String>
    internal lateinit var adapter: ArrayAdapter<String>

    internal lateinit var addButton: Button
    internal lateinit var deleteButton: Button
    internal lateinit var clearButton: Button

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemlist = arrayListOf<String>()
        adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)

        Log.d(TAG, "onCreate called.")

        addButton = findViewById(R.id.addButton)
        deleteButton = findViewById(R.id.deleteButton)
        clearButton = findViewById(R.id.clearButton)

        addButton.setOnClickListener { view ->
            addToDoListItem()
        }

        deleteButton.setOnClickListener { view ->
            deleteToDoItem()
        }

        clearButton.setOnClickListener { view ->
            clearToDoList()
        }

        toDoListView.setOnItemClickListener { adapterView, view, i, l ->
            android.widget.Toast.makeText(
                this,
                "List Item Clicked: " + itemlist.get(i),
                android.widget.Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun addToDoListItem() {
        itemlist.add(editText.text.toString())
        toDoListView.adapter = adapter
        adapter.notifyDataSetChanged()
        editText.text.clear()
    }

    private fun clearToDoList() {
        itemlist.clear()
        adapter.notifyDataSetChanged()
    }

    private fun deleteToDoItem() {
        val position: SparseBooleanArray = toDoListView.checkedItemPositions
        val count = toDoListView.count
        var item = count - 1
        while (item >= 0) {
            if (position.get(item)) {
                adapter.remove(itemlist.get(item))
            }
            item--
        }
        position.clear()
        adapter.notifyDataSetChanged()
    }
}
