package com.example.fragmentexamples

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class StudentAdapter(
    private val context: Context,
    private var students: MutableList<StudentModel>, // Using MutableList to allow updates
    private val onEditClick: (StudentModel) -> Unit,
    private val onRemoveClick: (StudentModel) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int {
        return students.size
    }

    override fun getItem(position: Int): Any {
        return students[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.layout_student_item, parent, false)

            holder = ViewHolder().apply {
                nameTextView = view.findViewById(R.id.text_name)
                idTextView = view.findViewById(R.id.text_mssv)

            }

            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val student = students[position]

        // Set data to the views
        holder.nameTextView.text = student.studentName
        holder.idTextView.text = student.studentId

        // Set listeners for remove and edit actions
        holder.removeImageView.setOnClickListener {
            onRemoveClick(student)
        }

        holder.editImageView.setOnClickListener {
            onEditClick(student)
        }

        return view
    }

    // ViewHolder class to optimize UI element access
    private class ViewHolder {
        lateinit var nameTextView: TextView
        lateinit var idTextView: TextView
        lateinit var removeImageView: ImageView
        lateinit var editImageView: ImageView
    }

    // Method to update the student list in the adapter
    fun updateStudentList(updatedList: MutableList<StudentModel>) {
        students = updatedList
        notifyDataSetChanged() // Notify the adapter that data has changed
    }
}
