package com.freshworks.data.repository

import com.freshworks.data.source.local.favorite.GifsLocalSource
import com.freshworks.data.source.remote.trending.GifsRemoteSource
import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel
import com.freshworks.domain.repository.GifRepository

class GifRepositoryImpl(private val remoteSource: GifsRemoteSource, private val localSource: GifsLocalSource): GifRepository {

    override suspend fun getTrendingGifs(limit: Int, rating: String): TrendingGifsInfoResponseModel {
        return remoteSource.getTrendingGifs(limit, rating)
    }

    override suspend fun getFavoriteGifs(): List<GifsResponseModel> {
        return localSource.getFavoriteGifs()
    }

    override suspend fun setFavoriteGif(gif: GifsResponseModel) {
        localSource.setFavoriteGif(gif)
    }

    override suspend fun removeFavoriteGif(gif: GifsResponseModel) {
        localSource.removeFavoriteGif(gif)
    }
}