package com.freshworks.domain.usecase.favorite

import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.domain.repository.GifRepository

class GetFavoriteGifsImpl(val gifRepository: GifRepository): GetFavoriteGifs {

    override suspend fun call(): List<GifsResponseModel> {
        return gifRepository.getFavoriteGifs()
    }
}