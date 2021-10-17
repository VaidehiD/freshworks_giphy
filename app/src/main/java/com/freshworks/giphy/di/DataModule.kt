package com.freshworks.giphy.di

import com.freshworks.data.repository.GifRepositoryImpl
import com.freshworks.data.source.local.favorite.GifsLocalSource
import com.freshworks.data.source.local.favorite.GifsLocalSourceImpl
import com.freshworks.data.source.local.favorite.mapper.GifsLocalMapper
import com.freshworks.data.source.local.favorite.mapper.GifsLocalMapperImpl
import com.freshworks.data.source.remote.trending.GifsRemoteSource
import com.freshworks.data.source.remote.trending.GifsRemoteSourceImpl
import com.freshworks.data.source.remote.trending.mapper.GifsRemoteMapper
import com.freshworks.data.source.remote.trending.mapper.GifsRemoteMapperImpl
import com.freshworks.domain.repository.GifRepository
import org.koin.dsl.module

val dataModule = module {

    single<GifsRemoteMapper> {
        GifsRemoteMapperImpl()
    }

    single<GifsRemoteSource> {
        GifsRemoteSourceImpl(get(), get())
    }

    single<GifsLocalMapper> {
        GifsLocalMapperImpl()
    }

    single<GifsLocalSource> {
        GifsLocalSourceImpl(get(), get())
    }

    single<GifRepository> {
        GifRepositoryImpl(get(), get())
    }
}