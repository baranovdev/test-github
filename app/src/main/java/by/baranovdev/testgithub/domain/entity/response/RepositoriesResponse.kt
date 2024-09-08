package by.baranovdev.testgithub.domain.entity.response

import by.baranovdev.testgithub.domain.entity.GithubRepositoryEntity

data class RepositoriesResponse(
    val list: List<GithubRepositoryEntity>
): SearchResult(list)