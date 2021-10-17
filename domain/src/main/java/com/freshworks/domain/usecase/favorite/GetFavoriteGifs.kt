package com.freshworks.domain.usecase.favorite

import com.freshworks.domain.model.gifs.GifsResponseModel

interface GetFavoriteGifs {

    suspend fun call(): List<GifsResponseModel>
}