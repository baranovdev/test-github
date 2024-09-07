package by.baranovdev.testgithub.domain.entity.response

import by.baranovdev.testgithub.domain.entity.GithubReposirory

data class RepositoriesResponse(
    val list: List<GithubReposirory>
)