package com.freshworks.domain.usecase.trending

import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel
import com.freshworks.domain.repository.GifRepository

class GetTrendingGifsUseCaseImpl(val repository: GifRepository): GetTrendingGifsUseCase {

    override suspend fun call(requestParams: GetTrendingGifsUseCase.RequestParams): TrendingGifsInfoResponseModel {

        val trendingGifs = repository.getTrendingGifs(requestParams.limit, requestParams.rating)

        val favoriteGifs = repository.getFavoriteGifs()

        trendingGifs.gifsResponse.map { trendingGif ->
            val isFavoritePresent = favoriteGifs.firstOrNull() {
                it.id == trendingGif.id
            }

            isFavoritePresent?.let {
                trendingGif.isFavorite = true
            }
        }

        return trendingGifs
    }

}