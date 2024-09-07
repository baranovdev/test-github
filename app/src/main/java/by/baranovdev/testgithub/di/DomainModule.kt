package by.baranovdev.testgithub.di

import by.baranovdev.testgithub.data.repository.GithubRepositoryImpl
import by.baranovdev.testgithub.domain.repository.GithubRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @Binds
    @Singleton
    abstract fun bindGithubRepository(
        repositoryImpl: GithubRepositoryImpl
    ): GithubRepository
}