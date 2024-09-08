package by.baranovdev.testgithub.data.dto

import com.google.gson.annotations.SerializedName

data class RepositoriesResponseDto(

	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("items")
	val items: List<RepositoryItem?>? = null
)

data class RepositoryItem(

	@field:SerializedName("forks_count")
	val forksCount: Int? = null,

	@field:SerializedName("stargazers_count")
	val stargazersCount: Int? = null,

	@field:SerializedName("topics")
	val topics: List<Any?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("watchers_count")
	val watchersCount: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("contents_url")
	val contentsUrl: String? = null
)
