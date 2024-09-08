package by.baranovdev.testgithub.domain.entity.response

import by.baranovdev.testgithub.domain.entity.SearchResultItem

sealed class SearchResult(val data: List<SearchResultItem>)