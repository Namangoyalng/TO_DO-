package com.example.to_doapp

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = TodoAdapter(mutableListOf())

        rvTodoItems.adapter = todoAdapter
        rvTodoItems.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { _ ->
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Add TO DO Items")
            alert.setMessage("Enter Items to Add")

            val editTasks = EditText(this)
            alert.setView(editTasks)

            alert.setPositiveButton("Add") { dialog, _ ->
                val edittext = editTasks.text.toString()
                if (edittext.isNotEmpty()) {
                    val todo = Todo(edittext)
                    todoAdapter.addTodo(todo)
                    Toast.makeText(this, "Task added and saved", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Invalid input, Please try again", Toast.LENGTH_LONG)
                        .show()
                }
                editTasks.text.clear()
                dialog.dismiss()
            }
            alert.setNegativeButton("Cancel") { dialog, _ ->
                Toast.makeText(this, "clicked cancel", Toast.LENGTH_LONG).show()
                editTasks.text.clear()
                dialog.dismiss()
            }
            alert.show()
        }

        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#E77120")))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.deleteAll ->{
                todoAdapter.deleteAll()
                true
            }
            R.id.deleteDone ->{
                todoAdapter.deleteDoneTodos()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

}

