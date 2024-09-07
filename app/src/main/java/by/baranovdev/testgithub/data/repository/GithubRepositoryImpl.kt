package by.baranovdev.testgithub.data.repository

import by.baranovdev.testgithub.data.api.GithubApi
import by.baranovdev.testgithub.data.mapper.toUiModel
import by.baranovdev.testgithub.domain.entity.params.GetRepositoriesParams
import by.baranovdev.testgithub.domain.entity.params.GetUserParams
import by.baranovdev.testgithub.domain.entity.response.RepositoriesResponse
import by.baranovdev.testgithub.domain.entity.response.UsersResponse
import by.baranovdev.testgithub.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi
): GithubRepository {
    override suspend fun getUsers(params: GetUserParams): UsersResponse {
        return githubApi.getUsers(params.query).toUiModel()
    }

    override suspend fun getRepositories(params: GetRepositoriesParams): RepositoriesResponse {
        return githubApi.getRepositories(params.query).toUiModel()
    }
}