package by.baranovdev.testgithub.data.api

import by.baranovdev.testgithub.data.dto.RepositoriesResponseDto
import by.baranovdev.testgithub.data.dto.UserResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users")
    suspend fun getUsers(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 10
    ): UserResponseDto

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("per_page") perPage: Int = 10
    ): RepositoriesResponseDto

}