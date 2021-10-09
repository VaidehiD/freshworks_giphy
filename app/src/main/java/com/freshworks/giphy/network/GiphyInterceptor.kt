package com.freshworks.giphy.network


import com.freshworks.giphy.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val GIPHY_API_KEY = "api_key"

class GiphyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl = original.url()

        val url = originalHttpUrl.newBuilder().apply {
            setQueryParameters(this)
        }.build()

        val requestBuilder = original.newBuilder().url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun setQueryParameters(httpUrlBuilder: HttpUrl.Builder) =
        httpUrlBuilder.apply {
            addQueryParameter(GIPHY_API_KEY, BuildConfig.GIPHY_API_KEY)
        }
}