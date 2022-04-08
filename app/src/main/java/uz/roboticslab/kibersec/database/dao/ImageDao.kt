package uz.roboticslab.kibersec.database.dao

import androidx.room.*
import uz.roboticslab.kibersec.database.entity.ImageEntity

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImageList(lessonData: List<ImageEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(lessonData: ImageEntity)

    @Query("select * from ImageEntity")
    fun getImageList(): List<ImageEntity>

    @Query("select * from ImageEntity where type=:type")
    fun getImageListType(type:String): List<ImageEntity>

    @Query("select * from ImageEntity where id=:id")
    fun getImage(id: Long): ImageEntity

}