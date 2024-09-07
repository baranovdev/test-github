package by.baranovdev.testgithub.domain.repository

import by.baranovdev.testgithub.domain.entity.params.GetRepositoriesParams
import by.baranovdev.testgithub.domain.entity.params.GetUserParams
import by.baranovdev.testgithub.domain.entity.response.RepositoriesResponse
import by.baranovdev.testgithub.domain.entity.response.UsersResponse

interface GithubRepository {

    suspend fun getUsers(params: GetUserParams): UsersResponse
    suspend fun getRepositories(params: GetRepositoriesParams): RepositoriesResponse

}