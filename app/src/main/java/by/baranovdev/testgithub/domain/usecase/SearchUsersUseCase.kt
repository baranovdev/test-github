package by.baranovdev.testgithub.domain.usecase

import android.util.Log
import by.baranovdev.testgithub.domain.entity.params.GetUserParams
import by.baranovdev.testgithub.domain.entity.response.UsersResponse
import by.baranovdev.testgithub.domain.repository.GithubRepository
import by.baranovdev.testgithub.domain.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val repository: GithubRepository
) : UseCase<GetUserParams, UsersResponse> {

    override fun execute(input: GetUserParams): Flow<UsersResponse> = flow {
        val data = repository.getUsers(input)
        Log.d("NETWORK", "SearchUsersUseCase --- result: $data")
        emit(data)
    }.onCompletion {
        Log.d("NETWORK", "SearchUsersUseCase --- Complete")
    }


}