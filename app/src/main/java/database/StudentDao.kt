package database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.maimoona.exam.Students
import java.util.*

@Dao
interface StudentDao {
    @Query("SELECT * FROM Students")
    fun getStudents(): List<Students>

    @Query("SELECT * FROM Students WHERE id=(:id)")
    fun getStudent(id: UUID): Students?

    @Insert
    fun addStudent(student: Students)

    @Update
    fun updateStudent(student: Students)

}