package uz.roboticslab.kibersec.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LessonEntity(
    @PrimaryKey
    val id: Long = 8724,
    val text: String = "",
    val audio: String = "",
    val type: String = "",
    val theme: String = ""
)