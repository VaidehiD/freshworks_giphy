package com.freshworks.domain.model.gifs

data class TrendingGifsInfoResponseModel(
    val gifsResponse: List<GifsResponseModel>,
    val pagination: TrendingGifsPaginationModel
)