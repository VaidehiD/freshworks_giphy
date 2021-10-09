package com.freshworks.data.source.remote.trending.mapper

import com.freshworks.data.entity.trending.request.TrendingPaginationRequestMap
import com.freshworks.data.entity.trending.response.GifsResponseEntity
import com.freshworks.data.entity.trending.response.TrendingGifsInfoResponseEntity
import com.freshworks.data.entity.trending.response.TrendingGifsPaginationEntity
import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel
import com.freshworks.domain.model.gifs.TrendingGifsPaginationModel

interface GifsRemoteMapper {

    fun toRequest(limit: Int, rating: String): TrendingPaginationRequestMap

    fun toModel(response: TrendingGifsInfoResponseEntity?): TrendingGifsInfoResponseModel

    fun toModel(gifsResponseEntity: GifsResponseEntity): GifsResponseModel

    fun toModel(gifsPaginationEntity: TrendingGifsPaginationEntity?): TrendingGifsPaginationModel
}