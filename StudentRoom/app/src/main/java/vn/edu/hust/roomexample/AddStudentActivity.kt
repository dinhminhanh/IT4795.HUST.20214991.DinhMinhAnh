package vn.edu.hust.roomexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import vn.edu.hust.roomexample.databinding.ActivityAddStudentBinding

class AddStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStudentBinding
    private lateinit var studentDao: StudentDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        studentDao = StudentDatabase.getInstance(this).studentDao()

        binding.buttonAddStudent.setOnClickListener {
            val hoten = binding.inputHoten.text.toString()
            val mssv = binding.inputMssv.text.toString()
            val ngaysinh = binding.inputNgaySinh.text.toString()
            val email = binding.inputEmail.text.toString()

            if (hoten.isBlank() || mssv.isBlank() || ngaysinh.isBlank() || email.isBlank()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                studentDao.insertStudent(Student(hoten = hoten, mssv = mssv, ngaysinh = ngaysinh, email = email))
                runOnUiThread {
                    Toast.makeText(this@AddStudentActivity, "Student added", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
