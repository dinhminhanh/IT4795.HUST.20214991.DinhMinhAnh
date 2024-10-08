package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private var firstNumber = 0.0
    private var operation = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvResult = findViewById(R.id.tvResult)

        // Number buttons
        val numberButtons = arrayOf(
            findViewById<Button>(R.id.btn0),
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )

        // Set click listeners for number buttons
        numberButtons.forEachIndexed { index, button ->
            button.setOnClickListener {
                onNumberClick(index.toString())
            }
        }

        // Operation buttons
        findViewById<Button>(R.id.btnPlus).setOnClickListener { onOperationClick("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { onOperationClick("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { onOperationClick("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { onOperationClick("/") }
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearCalculator() }
        findViewById<Button>(R.id.btnCE).setOnClickListener { clearEntry() }
        findViewById<Button>(R.id.btnDelete).setOnClickListener { deleteLastChar() }
        findViewById<Button>(R.id.btnDot).setOnClickListener { onDotClick() }
        findViewById<Button>(R.id.btnPlusMinus).setOnClickListener { toggleSign() }
    }

    private fun onNumberClick(number: String) {
        if (isNewOperation) {
            tvResult.text = number
            isNewOperation = false
        } else {
            if (tvResult.text == "0") {
                tvResult.text = number
            } else {
                tvResult.text = "${tvResult.text}$number"
            }
        }
    }

    private fun onOperationClick(op: String) {
        firstNumber = tvResult.text.toString().toDouble()
        operation = op
        isNewOperation = true
    }

    private fun calculateResult() {
        try {
            val secondNumber = tvResult.text.toString().toDouble()
            val result = when (operation) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "×" -> firstNumber * secondNumber
                "/" -> {
                    if (secondNumber == 0.0) throw ArithmeticException("Division by zero")
                    firstNumber / secondNumber
                }
                else -> secondNumber
            }
            tvResult.text = if (result % 1 == 0.0) {
                result.toLong().toString()
            } else {
                result.toString()
            }
            isNewOperation = true
        } catch (e: Exception) {
            tvResult.text = "Error"
        }
    }

    private fun clearCalculator() {
        tvResult.text = "0"
        firstNumber = 0.0
        operation = ""
        isNewOperation = true
    }

    private fun clearEntry() {
        tvResult.text = "0"
        isNewOperation = true
    }

    private fun deleteLastChar() {
        val currentText = tvResult.text.toString()
        if (currentText.length > 1) {
            tvResult.text = currentText.substring(0, currentText.length - 1)
        } else {
            tvResult.text = "0"
        }
    }

    private fun onDotClick() {
        if (!tvResult.text.contains(".")) {
            tvResult.text = "${tvResult.text}."
        }
    }

    private fun toggleSign() {
        try {
            val number = tvResult.text.toString().toDouble()
            tvResult.text = (-number).toString()
        } catch (e: Exception) {
            tvResult.text = "Error"
        }
    }
}