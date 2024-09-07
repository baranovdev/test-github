package by.baranovdev.testgithub.domain.usecase.base

import kotlinx.coroutines.flow.Flow

interface UseCase<in Input, out Output> {
    fun execute(input: Input): Flow<Output>
}