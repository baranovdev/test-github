package by.baranovdev.testgithub.domain.entity

data class User(
    val name: String,
    val imageUrl: String,
    val score: Double,
    val profileUrl: String
): SearchResultItem(name)