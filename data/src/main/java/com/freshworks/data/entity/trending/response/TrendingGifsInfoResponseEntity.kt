package com.freshworks.data.entity.trending.response

import com.google.gson.annotations.SerializedName

data class TrendingGifsInfoResponseEntity(
    @SerializedName("data")
    val gifsResponse: List<GifsResponseEntity>,
    @SerializedName("pagination")
    val pagination: TrendingGifsPaginationEntity
)