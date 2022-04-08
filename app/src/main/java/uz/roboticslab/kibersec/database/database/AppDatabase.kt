package uz.roboticslab.kibersec.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.roboticslab.kibersec.database.dao.ImageDao
import uz.roboticslab.kibersec.database.dao.LessonDao
import uz.roboticslab.kibersec.database.entity.ImageEntity
import uz.roboticslab.kibersec.database.entity.LessonEntity

@Database(entities = [LessonEntity::class, ImageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun lessonDao(): LessonDao
    abstract fun imageDao(): ImageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}