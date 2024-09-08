package by.baranovdev.testgithub.domain.entity

data class GithubRepositoryEntity(
    val forksCount: Int,
    val stargazersCount: Int,
    val topics: List<Any?>,
    val name: String,
    val description: String,
    val language: String,
    val id: Int,
    val watchersCount: Int,
    val url: String,
    val contentsUrl: String
): SearchResultItem(name)