package com.example.zad8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GradeAdapter(private val onGradeClick: (Grade) -> Unit) :
    RecyclerView.Adapter<GradeAdapter.GradeViewHolder>() {

    private var grades = listOf<Grade>()

    fun submitList(grades: List<Grade>) {
        this.grades = grades
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grade, parent, false)
        return GradeViewHolder(view)
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val grade = grades[position]
        holder.bind(grade)
    }

    override fun getItemCount(): Int {
        return grades.size
    }

    inner class GradeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val subjectTextView: TextView = itemView.findViewById(R.id.subjectText)
        private val gradeTextView: TextView = itemView.findViewById(R.id.gradeText)

        fun bind(grade: Grade) {
            subjectTextView.text = grade.subject
            gradeTextView.text = grade.grade.toString()

            itemView.setOnClickListener {
                onGradeClick(grade)
            }
        }
    }
}

