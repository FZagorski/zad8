package com.example.zad8

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class AddActivity : AppCompatActivity() {
    private lateinit var gradeViewModel: GradeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        gradeViewModel = ViewModelProvider(this)[GradeViewModel::class.java]

        val nameEditText = findViewById<EditText>(R.id.NameAddEditText)
        val gradeEditText = findViewById<EditText>(R.id.GradeAddEditText)
        val addButton = findViewById<Button>(R.id.AddButton)

        addButton.setOnClickListener {
            val subject = nameEditText.text.toString()
            val gradeText = gradeEditText.text.toString()

            if (subject.isNotEmpty() && gradeText.isNotEmpty()) {
                val gradeValue = gradeText.toDoubleOrNull()
                if (gradeValue != null && gradeValue in 2.0..5.0) {
                    val grade = Grade(subject = subject, grade = gradeValue)
                    gradeViewModel.insert(grade)
                    finish()
                } else {
                    gradeEditText.error = "Podaj ocenę w zakresie 2.0 - 5.0"
                }
            } else {
                if (subject.isEmpty()) nameEditText.error = "Podaj nazwę przedmiotu"
                if (gradeText.isEmpty()) gradeEditText.error = "Podaj ocenę"
            }
        }
    }
}

