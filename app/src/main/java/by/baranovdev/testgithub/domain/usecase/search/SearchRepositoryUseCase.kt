package by.baranovdev.testgithub.domain.usecase.search

import android.util.Log
import by.baranovdev.testgithub.domain.entity.params.GetRepositoriesParams
import by.baranovdev.testgithub.domain.entity.response.RepositoriesResponse
import by.baranovdev.testgithub.domain.repository.GithubRepository
import by.baranovdev.testgithub.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class SearchRepositoryUseCase @Inject constructor(
    private val repository: GithubRepository
): UseCase<GetRepositoriesParams, Result<RepositoriesResponse>> {
    override fun execute(input: GetRepositoriesParams): Flow<Result<RepositoriesResponse>> = flow {
        val data = repository.getRepositories(input)
        Log.d("NETWORK", "SearchRepositoryUseCase --- result: $data")
        emit(Result.success(data))
    }.catch { e ->
        emit(Result.failure(e))
    }.onCompletion {
        Log.d("NETWORK", "SearchRepositoryUseCase --- Complete")
    }

}