package by.baranovdev.testgithub.data.mapper

import by.baranovdev.testgithub.data.database.entity.PathDBE
import by.baranovdev.testgithub.data.dto.RepositoriesResponseDto
import by.baranovdev.testgithub.data.dto.UserResponseDto
import by.baranovdev.testgithub.data.dto.contents.PathResponseDto
import by.baranovdev.testgithub.domain.entity.GithubRepositoryEntity
import by.baranovdev.testgithub.domain.entity.Path
import by.baranovdev.testgithub.domain.entity.PathEntity
import by.baranovdev.testgithub.domain.entity.PathEntityType
import by.baranovdev.testgithub.domain.entity.User
import by.baranovdev.testgithub.domain.entity.response.RepositoriesResponse
import by.baranovdev.testgithub.domain.entity.response.UsersResponse


fun UserResponseDto.toUiModel(): UsersResponse =
    UsersResponse(items?.mapNotNull {
        User(it?.login.orEmpty(), it?.avatarUrl.orEmpty(), it?.score ?: .0, it?.url.orEmpty())
    } ?: emptyList())

fun RepositoriesResponseDto.toUiModel(): RepositoriesResponse =
    RepositoriesResponse(
        items?.mapNotNull {
            GithubRepositoryEntity(
                forksCount = it?.forksCount ?: 0,
                stargazersCount = it?.stargazersCount ?: 0,
                topics = it?.topics.orEmpty(),
                name = it?.name.orEmpty(),
                description = it?.description.orEmpty(),
                language = it?.language.orEmpty(),
                id = it?.id ?: 0,
                watchersCount = it?.watchersCount ?: 0,
                url = it?.url.orEmpty(),
                contentsUrl = it?.contentsUrl.orEmpty()
            )
        }.orEmpty()
    )

fun PathResponseDto.toUiModel() = Path(
    list = this.pathResponseDto?.mapNotNull {
        PathEntity(
            size = it?.size ?: 0,
            htmlUrl = it?.htmlUrl.orEmpty(),
            name = it?.name.orEmpty(),
            downloadUrl = it?.downloadUrl.orEmpty(),
            type = when {
                it?.type.equals("dir") -> PathEntityType.DIR
                it?.type.equals("file") -> PathEntityType.FILE
                else -> PathEntityType.UNKNOWN
            },
            url = it?.url.orEmpty()
        )
    } ?: emptyList()
)

fun PathResponseDto.toDatabaseEntities(parentUrl: String) =
    this.pathResponseDto?.mapNotNull {
        PathDBE(
            size = it?.size ?: 0,
            htmlUrl = it?.htmlUrl.orEmpty(),
            name = it?.name.orEmpty(),
            downloadUrl = it?.downloadUrl.orEmpty(),
            type = it?.type.orEmpty(),
            url = it?.url.orEmpty(),
            parentUrl = parentUrl
        )
    } ?: emptyList()

fun PathDBE.toUiModel() = PathEntity(
    size = this.size ?: 0,
    htmlUrl = this.htmlUrl.orEmpty(),
    name = this.name.orEmpty(),
    downloadUrl = this.downloadUrl.orEmpty(),
    type = when {
        this.type.equals("dir") -> PathEntityType.DIR
        this.type.equals("file") -> PathEntityType.FILE
        else -> PathEntityType.UNKNOWN
    },
    url = this.url.orEmpty()
)