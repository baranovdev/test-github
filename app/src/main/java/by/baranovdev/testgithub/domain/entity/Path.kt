package by.baranovdev.testgithub.domain.entity

data class Path(
    val list: List<PathEntity>
)

data class PathEntity(
    val size: Int,
    val htmlUrl: String,
    val name: String,
    val downloadUrl: String,
    val type: PathEntityType,
    val url: String
)

enum class PathEntityType{
    DIR, FILE, UNKNOWN
}
