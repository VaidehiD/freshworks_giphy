package com.freshworks.giphy.di

import com.freshworks.domain.usecase.GetTrendingGifs
import com.freshworks.domain.usecase.GetTrendingGifsImpl
import org.koin.dsl.module

val domainModule = module {

    factory <GetTrendingGifs> {
        GetTrendingGifsImpl(get())
    }
}