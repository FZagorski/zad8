package com.example.zad8

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class GradeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: GradeRepository
    val allGrades: LiveData<List<Grade>>

    init {
        val gradeDao = GradeDatabase.getDatabase(application).gradeDao()
        repository = GradeRepository(gradeDao)
        allGrades = repository.allGrades
    }

    fun insert(grade: Grade) = viewModelScope.launch {
        repository.insert(grade)
    }

    fun update(grade: Grade) = viewModelScope.launch {
        repository.update(grade)
    }

    fun delete(grade: Grade) = viewModelScope.launch {
        repository.delete(grade)
    }
}
