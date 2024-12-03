package com.example.fragmentexamples

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.fragmentexamples.R

class AddStudentFragment : Fragment() {

    private lateinit var nameEditText: EditText
    private lateinit var idEditText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_student, container, false)

        nameEditText = view.findViewById(R.id.edit_name)
        idEditText = view.findViewById(R.id.edit_mssv)

        val addButton: Button = view.findViewById(R.id.btn_add_student)
        val cancelButton: Button = view.findViewById(R.id.btn_cancel)

        // Xử lý sự kiện bấm nút "Add Student"
        addButton.setOnClickListener {
            val studentName = nameEditText.text.toString()
            val studentId = idEditText.text.toString()
            val student = "$studentName - $studentId"
            if (activity is MainActivity) {
                // Gọi hàm trong MainActivity để cập nhật danh sách
                val updatedList = (activity as MainActivity).students.toMutableList()
                updatedList.add(student)
                (activity as MainActivity).updateStudentList(updatedList)
            }
            requireActivity().supportFragmentManager.popBackStack() // Đóng fragment
        }

        // Xử lý sự kiện bấm nút "Cancel"
        cancelButton.setOnClickListener {
            // Quay lại fragment trước đó
            requireActivity().supportFragmentManager.popBackStack()
        }

        return view
    }
}
