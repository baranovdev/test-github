package by.baranovdev.testgithub.domain.entity.response

import by.baranovdev.testgithub.domain.entity.User

data class UsersResponse(
    val list: List<User>
): SearchResult(list)