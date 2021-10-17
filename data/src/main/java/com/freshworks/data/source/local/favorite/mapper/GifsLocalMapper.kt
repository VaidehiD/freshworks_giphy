package com.freshworks.data.source.local.favorite.mapper

import com.freshworks.data.entity.favorite.FavoriteGif
import com.freshworks.domain.model.gifs.GifsResponseModel

interface GifsLocalMapper {

    fun toModel(favoriteGif: FavoriteGif): GifsResponseModel

    fun toEntity(gif: GifsResponseModel): FavoriteGif
}