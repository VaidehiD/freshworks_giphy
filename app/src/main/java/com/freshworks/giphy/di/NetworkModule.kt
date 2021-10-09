package com.freshworks.giphy.di

import com.freshworks.data.source.remote.GiphyApi
import com.freshworks.giphy.network.GiphyInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT = 60L
private const val READ_TIMEOUT = 60L
private const val WRITE_TIMEOUT = 60L

val networkModule = module {

    fun getRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl("https://api.giphy.com/v1/gifs/")
        .addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().setFieldNamingPolicy(
                    FieldNamingPolicy.IDENTITY
                ).create()
            )
        )
        .client(okHttpClient)
        .build()

    fun getGiphyApi(retrofit: Retrofit): GiphyApi {
        return retrofit.create(GiphyApi::class.java)
    }

    fun getRetrofitClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .apply {
                connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                addInterceptor(GiphyInterceptor())
            }.build()
    }

    single { GiphyInterceptor() }
    single { getRetrofitClient() }
    single { getRetrofit( get() ) }
    single { getGiphyApi( get()) }
}

