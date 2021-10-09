package com.freshworks.domain.repository

import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel

interface GifRepository {

    suspend fun getTrendingGifs(limit: Int, rating: String): TrendingGifsInfoResponseModel
}