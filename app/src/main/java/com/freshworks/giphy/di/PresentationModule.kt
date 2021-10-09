package com.freshworks.giphy.di

import com.freshworks.giphy.ui.trending.TrendingGifsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
     viewModel {
            TrendingGifsViewModel(useCase = get())
        }
}