package com.freshworks.domain.usecase

import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel

interface GetTrendingGifs {

    data class requestParams(val limit: Int, val rating: String)

    suspend fun call(requestParams: requestParams): TrendingGifsInfoResponseModel
}