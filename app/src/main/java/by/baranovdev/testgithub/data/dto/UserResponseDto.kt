package by.baranovdev.testgithub.data.dto

import com.google.gson.annotations.SerializedName

data class UserResponseDto(
	@field:SerializedName("total_count")
	val totalCount: Int? = null,

	@field:SerializedName("items")
	val items: List<UserItem?>? = null
)

data class UserItem(

	@field:SerializedName("score")
	val score: Any? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("repos_url")
	val reposUrl: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
