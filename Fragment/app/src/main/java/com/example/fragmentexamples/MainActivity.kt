package com.example.fragmentexamples

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fragmentexamples.R
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var studentListView: ListView
    val students = mutableListOf(
        "Dinh Minh Anh - 20214491"
    )
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentListView = findViewById(R.id.listView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, students)
        studentListView.adapter = adapter

        studentListView.setOnItemClickListener { _, _, position, _ ->
            val student = students[position]
            Log.d("MainActivity", "Editing student: $student")
            // Navigate to EditFragment
            val fragment = EditStudentFragment()
            val bundle = Bundle()
            bundle.putString("student_name", student)
            fragment.arguments = bundle
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit()
        }

        registerForContextMenu(studentListView)
    }

    override fun onCreateContextMenu(
        menu: android.view.ContextMenu?,
        v: View?,
        menuInfo: android.view.ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val student = students[info.position]

        when (item.itemId) {
            R.id.edit -> {
                Log.d("MainActivity", "Editing student: $student")
                val fragment = EditStudentFragment()
                val bundle = Bundle()
                bundle.putString("student_name", student)
                fragment.arguments = bundle
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit()
                return true
            }
            R.id.remove -> {
                Log.d("MainActivity", "Deleting student: $student")

                // Lưu lại sinh viên vừa xóa để có thể hoàn tác
                val removedStudent = students[info.position]

                // Xóa sinh viên khỏi danh sách
                students.removeAt(info.position)
                adapter.notifyDataSetChanged() // Cập nhật adapter

                // Hiển thị Snackbar với tùy chọn Undo
                val snackbar = Snackbar.make(
                    findViewById(R.id.listView),
                    "Student removed",
                    Snackbar.LENGTH_LONG
                )
                snackbar.setAction("Undo") {

                    students.add(info.position, removedStudent)
                    adapter.notifyDataSetChanged()  // Cập nhật lại danh sách sau khi undo
                    Toast.makeText(this, "Undo successful", Toast.LENGTH_SHORT).show()
                }
                snackbar.show()

                // Hiển thị thông báo xóa
                Toast.makeText(this, "Student removed", Toast.LENGTH_SHORT).show()

                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_new -> {
                Log.d("MainActivity", "Adding new student")
                val fragment = AddStudentFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Method to update list when student is added or edited
    fun updateStudentList(updatedStudents: List<String>) {
        students.clear()
        students.addAll(updatedStudents)
        adapter.notifyDataSetChanged()
        Log.d("MainActivity", "Student list updated: $students")
    }
}
