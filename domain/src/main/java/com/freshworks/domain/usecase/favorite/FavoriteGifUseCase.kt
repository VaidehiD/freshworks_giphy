package com.freshworks.domain.usecase.favorite

import com.freshworks.domain.model.gifs.GifsResponseModel

interface FavoriteGifUseCase {

    suspend fun save(gif: GifsResponseModel)

    suspend fun remove(gif: GifsResponseModel)

    suspend fun getAllFavoriteGifs(): List<GifsResponseModel>
}