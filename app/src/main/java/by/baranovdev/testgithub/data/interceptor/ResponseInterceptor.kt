package by.baranovdev.testgithub.data.interceptor
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ResponseInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originalUrl = request.url.toString()

        val fixedUrl = originalUrl.replace("%3F", "?")

        val newUrl = fixedUrl.toHttpUrlOrNull() ?: throw IllegalArgumentException("Invalid URL: $fixedUrl")

        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()
        println("Request Url: " + chain.request().url.toUrl().toString())
        val response = chain.proceed(newRequest)

        if (response.isSuccessful) {
            println("Success!: ${response.body}")
        } else {
            when (response.code) {
                401 -> {
                    println("Unauthorized! Status code: ${response.code}")
                    throw IOException("Error ${response.code}: Unauthorized access!")
                }
                404 -> {
                    println("Unauthorized! Status code: ${response.code}")
                    throw IOException("Error ${response.code}: Unauthorized access!")
                }
                500 -> {
                    println("Server error! Status code: ${response.code}")
                    throw IOException("Error ${response.code}: Server error!")
                }
                else -> {
                    println("Error! Status code: ${response.code}")
                    throw IOException("Error ${response.code}: Unexpected error with status code ${response.code}")
                }
            }
        }
        return response
    }
}