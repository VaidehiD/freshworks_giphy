package com.freshworks.giphy.di

import com.freshworks.giphy.ui.main.trending.TrendingGifsData
import com.freshworks.giphy.ui.main.GifsViewModel
import com.freshworks.giphy.ui.main.favorites.FavoriteGifsData
import com.freshworks.giphy.ui.main.favorites.FavoriteGifsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
     viewModel {
            GifsViewModel(useCase = get(),
            data = get(),
            favoriteUseCase = get())
        }

    viewModel {
        FavoriteGifsViewModel(favoriteUseCase = get(),
            data = get())
    }

    single {
        FavoriteGifsData()
    }

    single {
        TrendingGifsData()
    }
}