package com.maimoona.exam

import androidx.lifecycle.ViewModel

class StudentsListViewModel : ViewModel() {

    val students = mutableListOf<Students>()

    /*init {
        for (i in 0 until 100) {
            val student = Students()
            student.stdNmae = "Student #$i"
            student.stdNo = i
            student.stdPass =if( i % 2 == 0) true else false
            students += student
        }
    }*/

    fun addStudent(student: Students) {
        students.add(student)
    }


    fun deleteStudent(index : Int){
        students.removeAt(index)
    }
}