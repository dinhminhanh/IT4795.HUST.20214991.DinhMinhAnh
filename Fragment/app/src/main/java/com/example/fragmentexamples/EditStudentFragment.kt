package com.example.fragmentexamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentexamples.R
class EditStudentFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_student, container, false)

        nameEditText = view.findViewById(R.id.edit_name)
        idEditText = view.findViewById(R.id.edit_mssv)

        val studentName = arguments?.getString("student_name")
        if (studentName != null) {
            val parts = studentName.split(" - ")
            nameEditText.setText(parts[0])
            idEditText.setText(parts[1])
        }

        val saveButton: Button = view.findViewById(R.id.btn_update)
        saveButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedId = idEditText.text.toString()
            val updatedStudent = "$updatedName - $updatedId"

            if (activity is MainActivity) {
                // Call the function in MainActivity to update the list
                val updatedList = (activity as MainActivity).students.toMutableList()
                val index = updatedList.indexOf(studentName)
                if (index != -1) {
                    updatedList[index] = updatedStudent
                }
                (activity as MainActivity).updateStudentList(updatedList)
            }

            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}