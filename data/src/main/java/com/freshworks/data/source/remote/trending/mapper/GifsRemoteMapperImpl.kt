package com.freshworks.data.source.remote.trending.mapper

import com.freshworks.data.entity.trending.request.TrendingPaginationRequest
import com.freshworks.data.entity.trending.request.TrendingPaginationRequestMap
import com.freshworks.data.entity.trending.response.GifsResponseEntity
import com.freshworks.data.entity.trending.response.TrendingGifsInfoResponseEntity
import com.freshworks.data.entity.trending.response.TrendingGifsPaginationEntity
import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel
import com.freshworks.domain.model.gifs.TrendingGifsPaginationModel

class GifsRemoteMapperImpl: GifsRemoteMapper {

    override fun toRequest(limit: Int, rating: String) =
            TrendingPaginationRequestMap().requestParams(TrendingPaginationRequest(limit, rating))

    override fun toModel(entity: TrendingGifsInfoResponseEntity?) =
        TrendingGifsInfoResponseModel(
            gifsResponse = entity?.gifsResponse?.map { toModel(it) } ?: listOf(),
            pagination = toModel(entity?.pagination)
        )

    override fun toModel(gifsResponseEntity: GifsResponseEntity) = GifsResponseModel(
        id = gifsResponseEntity.id.orEmpty(),
        url = gifsResponseEntity.url.orEmpty()
    )

    override fun toModel(gifsPaginationEntity: TrendingGifsPaginationEntity?) = TrendingGifsPaginationModel (
        offset = gifsPaginationEntity?.offset ?: 0
    )

}