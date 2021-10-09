package com.freshworks.data.source.remote.trending

import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel

interface GifsRemoteSource {

    suspend fun getTrendingGifs(limit: Int, rating: String): TrendingGifsInfoResponseModel
}