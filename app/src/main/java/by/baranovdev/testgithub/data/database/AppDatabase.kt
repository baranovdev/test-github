package by.baranovdev.testgithub.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import by.baranovdev.testgithub.data.database.dao.PathDao
import by.baranovdev.testgithub.data.database.entity.PathDBE

@Database(entities = [PathDBE::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pathDao(): PathDao
}