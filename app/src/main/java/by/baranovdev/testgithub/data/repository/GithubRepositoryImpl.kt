package by.baranovdev.testgithub.data.repository

import android.util.Log
import by.baranovdev.testgithub.BuildConfig
import by.baranovdev.testgithub.data.api.GithubApi
import by.baranovdev.testgithub.data.database.dao.PathDao
import by.baranovdev.testgithub.data.dto.contents.PathResponseDto
import by.baranovdev.testgithub.data.mapper.toDatabaseEntities
import by.baranovdev.testgithub.data.mapper.toUiModel
import by.baranovdev.testgithub.domain.entity.PathEntity
import by.baranovdev.testgithub.domain.entity.params.GetPathParams
import by.baranovdev.testgithub.domain.entity.params.GetRepositoriesParams
import by.baranovdev.testgithub.domain.entity.params.GetUserParams
import by.baranovdev.testgithub.domain.entity.response.RepositoriesResponse
import by.baranovdev.testgithub.domain.entity.response.UsersResponse
import by.baranovdev.testgithub.domain.repository.GithubRepository
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val pathDao: PathDao
) : GithubRepository {
    override suspend fun getUsers(params: GetUserParams): UsersResponse {
        return githubApi.getUsers(params.query).toUiModel()
    }

    override suspend fun getRepositories(params: GetRepositoriesParams): RepositoriesResponse {
        return githubApi.getRepositories(params.query).toUiModel()
    }

    override suspend fun getPath(params: GetPathParams): List<PathEntity> {
        val pathDBEList = try {
            pathDao.findByParentUrl(params.path.replace(BuildConfig.BASE_URL, "")).mapNotNull {
                it?.toUiModel()
            }
        } catch (e: Exception) {
            emptyList<PathEntity>()
        } finally {
            emptyList<PathEntity>()
        }

        return if (pathDBEList.isNotEmpty()) pathDBEList else {
            val response = PathResponseDto(githubApi.getPath(params.path))
            response.toDatabaseEntities(parentUrl = params.path).forEach {
                pathDao.insert(
                    it
                )
            }
            response.toUiModel().list
        }
    }
}