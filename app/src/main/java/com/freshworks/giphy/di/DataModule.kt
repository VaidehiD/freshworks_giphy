package com.freshworks.giphy.di

import com.freshworks.data.repository.GifRepositoryImpl
import com.freshworks.data.source.remote.GiphyApi
import com.freshworks.data.source.remote.trending.GifsRemoteSource
import com.freshworks.data.source.remote.trending.GifsRemoteSourceImpl
import com.freshworks.data.source.remote.trending.mapper.GifsRemoteMapper
import com.freshworks.data.source.remote.trending.mapper.GifsRemoteMapperImpl
import com.freshworks.domain.repository.GifRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single<GifsRemoteMapper> {
        GifsRemoteMapperImpl()
    }

    single<GifsRemoteSource> {
        GifsRemoteSourceImpl(get(), get())
    }

    single<GifRepository> {
        GifRepositoryImpl(get())
    }
}