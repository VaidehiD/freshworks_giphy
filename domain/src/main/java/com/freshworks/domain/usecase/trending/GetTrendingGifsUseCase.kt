package com.freshworks.domain.usecase.trending

import com.freshworks.domain.model.gifs.RequestRating
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel

interface GetTrendingGifsUseCase {

    data class RequestParams(val limit: Int = 25, val rating: String = RequestRating.G.name)

    suspend fun call(requestParams: RequestParams): TrendingGifsInfoResponseModel
}