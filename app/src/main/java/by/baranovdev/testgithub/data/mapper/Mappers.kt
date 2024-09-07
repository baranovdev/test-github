package by.baranovdev.testgithub.data.mapper

import by.baranovdev.testgithub.data.dto.RepositoriesResponseDto
import by.baranovdev.testgithub.data.dto.UserResponseDto
import by.baranovdev.testgithub.domain.entity.GithubReposirory
import by.baranovdev.testgithub.domain.entity.User
import by.baranovdev.testgithub.domain.entity.response.RepositoriesResponse
import by.baranovdev.testgithub.domain.entity.response.UsersResponse


fun UserResponseDto.toUiModel(): UsersResponse =
    UsersResponse(items?.mapNotNull {
        User(it?.login.orEmpty())
    } ?: emptyList())

fun RepositoriesResponseDto.toUiModel(): RepositoriesResponse =
    RepositoriesResponse(
        items?.mapNotNull {
            GithubReposirory(
                it?.name.orEmpty()
            )
        }.orEmpty()
    )