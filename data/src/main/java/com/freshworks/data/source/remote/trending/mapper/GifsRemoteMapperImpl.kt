package com.freshworks.data.source.remote.trending.mapper

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

    override fun toModel(gifsResponseEntity: GifsResponseEntity) = GifsResponseModel(
        id = gifsResponseEntity.id.orEmpty(),
        url = gifsResponseEntity.url.orEmpty(),
        title = gifsResponseEntity.title.orEmpty(),
        images = toModel(gifsResponseEntity.images)
    )

    override fun toModel(gifsPaginationEntity: TrendingGifsPaginationEntity?) =
        TrendingGifsPaginationModel(
            offset = gifsPaginationEntity?.offset ?: 0
        )

    private fun toModel(gifsImageEntity: GifsImageEntity?) =
        gifsImageEntity?.let {
            GifsImageModel(fixedHeightModel = toModel(it.fixed_width))
        } ?: GifsImageModel()

    private fun toModel(gifFixedHeightEntity: GifFixedHeightEntity?) =
        gifFixedHeightEntity?.let {
            GifFixedHeightModel(
                height = it.height ?: 0,
                width = it.width ?: 0,
                webpUrl = it.url.orEmpty()
            )
        } ?: GifFixedHeightModel()

}