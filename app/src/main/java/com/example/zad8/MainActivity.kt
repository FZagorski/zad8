package com.example.zad8

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var gradeViewModel: GradeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.gradesRecyclerView)
        val adapter = GradeAdapter { grade ->
            val intent = Intent(this, EditActivity::class.java)
            intent.putExtra("gradeId", grade.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        gradeViewModel = ViewModelProvider(this)[GradeViewModel::class.java]
        gradeViewModel.allGrades.observe(this) { grades ->
            adapter.submitList(grades)
        }

        val addGradeButton: Button = findViewById(R.id.addGradeButton)
        addGradeButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }
}
