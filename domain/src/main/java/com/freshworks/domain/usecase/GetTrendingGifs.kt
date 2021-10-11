package com.freshworks.domain.usecase

import com.freshworks.domain.model.gifs.RequestRating
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel

interface GetTrendingGifs {

    data class RequestParams(val limit: Int = 25, val rating: String = RequestRating.G.name)

    suspend fun call(requestParams: RequestParams): TrendingGifsInfoResponseModel
}