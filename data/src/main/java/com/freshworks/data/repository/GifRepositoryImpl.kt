package com.freshworks.data.repository

import com.freshworks.data.source.remote.trending.GifsRemoteSource
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel
import com.freshworks.domain.repository.GifRepository

class GifRepositoryImpl(private val remoteSource: GifsRemoteSource): GifRepository {

    override suspend fun getTrendingGifs(limit: Int, rating: String): TrendingGifsInfoResponseModel {
        return remoteSource.getTrendingGifs(limit, rating)
    }
}