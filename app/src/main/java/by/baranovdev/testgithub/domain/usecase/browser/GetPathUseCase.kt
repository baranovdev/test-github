package by.baranovdev.testgithub.domain.usecase.browser

import android.util.Log
import by.baranovdev.testgithub.domain.entity.PathEntity
import by.baranovdev.testgithub.domain.entity.params.GetPathParams
import by.baranovdev.testgithub.domain.repository.GithubRepository
import by.baranovdev.testgithub.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class GetPathUseCase @Inject constructor(
    private val repository: GithubRepository
): UseCase<GetPathParams, Result<List<PathEntity>>> {
    override fun execute(input: GetPathParams): Flow<Result<List<PathEntity>>> = flow{
        val data = repository.getPath(input)
        Log.d("NETWORK", "GetPathUseCase --- result: $data")
        emit(Result.success(data))
    }.catch { e ->
        emit(Result.failure(e))
    }.onCompletion {
        Log.d("NETWORK", "GetPathUseCase --- Complete")
    }

}