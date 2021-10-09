package com.freshworks.giphy.ui.trending

import androidx.lifecycle.ViewModel
import com.freshworks.domain.usecase.GetTrendingGifs

class TrendingGifsViewModel(val useCase: GetTrendingGifs): ViewModel() {

    fun getTrendingGifs() {}
}