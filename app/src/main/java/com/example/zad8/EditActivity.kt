package com.example.zad8

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class EditActivity : AppCompatActivity() {
    private lateinit var gradeViewModel: GradeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        gradeViewModel = ViewModelProvider(this)[GradeViewModel::class.java]
        val gradeId = intent.getIntExtra("gradeId", -1)

        gradeViewModel.allGrades.observe(this) { grades ->
            val grade = grades.find { it.id == gradeId }
            if (grade != null) {
                findViewById<EditText>(R.id.NameEditText).setText(grade.subject)
                findViewById<EditText>(R.id.GradeEditText).setText(grade.grade.toString())

                findViewById<Button>(R.id.SaveEditButton).setOnClickListener {
                    val subject = findViewById<EditText>(R.id.NameEditText).text.toString()
                    val newGrade = findViewById<EditText>(R.id.GradeEditText).text.toString().toDouble()
                    gradeViewModel.update(Grade(id = grade.id, subject = subject, grade = newGrade))
                    finish()
                }

                findViewById<Button>(R.id.DeleteButton).setOnClickListener {
                    gradeViewModel.delete(grade)
                    finish()
                }
            }
        }
    }
}
