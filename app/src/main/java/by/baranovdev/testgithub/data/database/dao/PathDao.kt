package by.baranovdev.testgithub.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import by.baranovdev.testgithub.data.database.entity.PathDBE

@Dao
interface PathDao  {
    @Insert
    suspend fun insert(path: PathDBE)

    @Query("SELECT * FROM path WHERE parent_url LIKE '%' || :parentUrl || '%'")
    suspend fun findByParentUrl(parentUrl: String): List<PathDBE?>
}