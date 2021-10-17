package com.freshworks.data.source.local.favorite

import com.freshworks.domain.model.gifs.GifsResponseModel

interface GifsLocalSource {

    suspend fun getFavoriteGifs(): List<GifsResponseModel>

    suspend fun setFavoriteGif(gif: GifsResponseModel)

    suspend fun removeFavoriteGif(gif: GifsResponseModel)
}