package com.example.firstapp.reinaldo

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.R
import com.example.firstapp.reinaldo.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton


class TODOActivity : AppCompatActivity() {

    private val categories = listOf(
        Business,
        Other,
        Personal
    )

    private val tasks = mutableListOf(
        Task("Business", TaskCategory.Business),
        Task("Other", TaskCategory.Other),
        Task("Personal", TaskCategory.Personal)
    )


    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    private lateinit var fabAddTask: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todoactivity)


        initComponents()
        initUI()
        initListeners()

    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.item_todo_dialog)

        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()) {
                val idSelected = rgCategories.checkedRadioButtonId
                val radioButtonSelected: RadioButton = rgCategories.findViewById(idSelected)
                val currentCategory: TaskCategory = when (radioButtonSelected.text) {
                    getString(R.string.todo_dialog_category_business) -> Business
                    getString(R.string.todo_dialog_category_personal) -> Personal

                    else -> Other
                }
                tasks.add(Task(currentTask, currentCategory))
                updateTasks()
                dialog.hide()

            }

        }

        dialog.show()
    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.tvTask)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories) {position -> updateCategory(position)}
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter


        tasksAdapter = TasksAdapter(tasks) {position -> onItemSelected(position)}
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }



    private fun onItemSelected(position: Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

    private fun updateCategory(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    /*  cuando agregamos nuevas tareas es necesario avisarle al adaptador que hemnos agregado una nueva tarea
    * recordando que el adapatador es el que se encarga de tomar las tareas y convertirlas en items que se pintaran en la pantalla
    *
    *   */
    fun updateTasks() {
        val categoriesSelected: List<TaskCategory> = categories.filter{ it.isSelected }
        val newTasks = tasks.filter { categoriesSelected.contains(it.category) }
        tasksAdapter.tasks = newTasks
        tasksAdapter.notifyDataSetChanged()
    }
}