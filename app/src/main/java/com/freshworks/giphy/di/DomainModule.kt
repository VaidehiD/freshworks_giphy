package com.freshworks.giphy.di

import com.freshworks.domain.usecase.favorite.FavoriteGifUseCase
import com.freshworks.domain.usecase.favorite.FavoriteGifUseCaseImpl
import com.freshworks.domain.usecase.trending.GetTrendingGifsUseCase
import com.freshworks.domain.usecase.trending.GetTrendingGifsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory <GetTrendingGifsUseCase> {
        GetTrendingGifsUseCaseImpl(get())
    }

    factory <FavoriteGifUseCase> {
        FavoriteGifUseCaseImpl(get())
    }

}