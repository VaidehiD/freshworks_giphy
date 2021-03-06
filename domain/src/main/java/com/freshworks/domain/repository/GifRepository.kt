package com.freshworks.domain.repository

import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel

interface GifRepository {

    suspend fun getTrendingGifs(limit: Int, rating: String): TrendingGifsInfoResponseModel

    suspend fun getFavoriteGifs(): List<GifsResponseModel>

    suspend fun setFavoriteGif(gif: GifsResponseModel)

    suspend fun removeFavoriteGif(gif: GifsResponseModel)
}