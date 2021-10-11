package com.freshworks.data.source.remote.trending.mapper

import com.freshworks.data.entity.trending.request.TrendingPaginationRequestMap
import com.freshworks.data.entity.trending.response.*
import com.freshworks.domain.model.gifs.*

interface GifsRemoteMapper {

    fun toRequest(limit: Int, rating: String): TrendingPaginationRequestMap

    fun toModel(response: TrendingGifsInfoResponseEntity?): TrendingGifsInfoResponseModel

    fun toModel(gifsResponseEntity: GifsResponseEntity): GifsResponseModel

    fun toModel(gifsPaginationEntity: TrendingGifsPaginationEntity?): TrendingGifsPaginationModel

}