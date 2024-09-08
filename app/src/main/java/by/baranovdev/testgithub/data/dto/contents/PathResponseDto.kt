package by.baranovdev.testgithub.data.dto.contents

import com.google.gson.annotations.SerializedName

data class PathResponseDto(

	@field:SerializedName("PathResponseDto")
	val pathResponseDto: List<PathResponseDtoItem?>? = null
)

data class PathResponseDtoItem(

	@field:SerializedName("size")
	val size: Int? = null,

	@field:SerializedName("html_url")
	val htmlUrl: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("download_url")
	val downloadUrl: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
