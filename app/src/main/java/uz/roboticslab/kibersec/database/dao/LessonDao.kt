package uz.roboticslab.kibersec.database.dao

import androidx.room.*
import uz.roboticslab.kibersec.database.entity.LessonEntity

@Dao
interface LessonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLessonList(lessonData: List<LessonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLesson(lessonData: LessonEntity)

    @Query("select * from LessonEntity")
    fun getLessonList(): List<LessonEntity>

    @Query("select * from LessonEntity where type=:type and theme=:theme")
    fun getLessonType(type: String, theme: String): List<LessonEntity>

    @Query("select * from LessonEntity where id=:id")
    fun getLesson(id: Long): LessonEntity

    @Delete
    fun deleteLesson(lessonEntity: LessonEntity)

}