package com.freshworks.data.source.remote.trending

import com.freshworks.data.source.remote.GiphyApi
import com.freshworks.data.source.remote.trending.mapper.GifsRemoteMapper
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel

class GifsRemoteSourceImpl(
    private val api: GiphyApi,
    private val remoteMapper: GifsRemoteMapper
) : GifsRemoteSource {

    override suspend fun getTrendingGifs(
        limit: Int,
        rating: String
    ): TrendingGifsInfoResponseModel {
        return remoteMapper.toModel(
            api.getTrendingGifs(remoteMapper.toRequest(limit, rating)).body()
        )
    }

}