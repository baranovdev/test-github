package by.baranovdev.testgithub.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "path")
data class PathDBE(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("size")
    val size: Int? = null,

    @ColumnInfo("html_url")
    val htmlUrl: String? = null,

    @ColumnInfo("name")
    val name: String? = null,

    @ColumnInfo("download_url")
    val downloadUrl: String? = null,

    @ColumnInfo("type")
    val type: String? = null,

    @ColumnInfo("url")
    val url: String? = null,

    @ColumnInfo("parent_url")
    val parentUrl: String? = null
)