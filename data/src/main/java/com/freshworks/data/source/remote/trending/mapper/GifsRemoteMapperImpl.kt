package com.freshworks.data.source.remote.trending.mapper

import androidx.lifecycle.MutableLiveData
import com.freshworks.data.entity.trending.request.TrendingPaginationRequest
import com.freshworks.data.entity.trending.request.TrendingPaginationRequestMap
import com.freshworks.data.entity.trending.response.*
import com.freshworks.domain.model.gifs.*

class GifsRemoteMapperImpl : GifsRemoteMapper {

    override fun toRequest(limit: Int, rating: String) =
        TrendingPaginationRequestMap().requestParams(TrendingPaginationRequest(limit, rating))

    override fun toModel(entity: TrendingGifsInfoResponseEntity?) =
        TrendingGifsInfoResponseModel(
            gifsResponse = entity?.gifsResponse?.map { toModel(it) } ?: listOf(),
            pagination = toModel(entity?.pagination)
        )

    override fun toModel(gifsResponseEntity: GifsResponseEntity) =
        with (gifsResponseEntity) {
            GifsResponseModel(
                id = id.orEmpty(),
                title = gifsResponseEntity.title.orEmpty(),
                webpUrl = gifsResponseEntity.images?.fixed_width?.url.orEmpty()
            )
        }

    override fun toModel(gifsPaginationEntity: TrendingGifsPaginationEntity?) =
        TrendingGifsPaginationModel(
            offset = gifsPaginationEntity?.offset ?: 0
        )
}