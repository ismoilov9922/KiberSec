package uz.roboticslab.kibersec.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey
    val id: Long = 378,
    val imageUrl: String = "",
    val type: String = "",
    val theme: String = "",
)
