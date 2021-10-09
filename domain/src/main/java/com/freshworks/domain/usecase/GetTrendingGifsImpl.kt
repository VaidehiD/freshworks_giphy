package com.freshworks.domain.usecase

import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel
import com.freshworks.domain.repository.GifRepository

class GetTrendingGifsImpl(val repository: GifRepository): GetTrendingGifs {

    override suspend fun call(requestParams: GetTrendingGifs.requestParams): TrendingGifsInfoResponseModel {
        return repository.getTrendingGifs(requestParams.limit, requestParams.rating)
    }

}