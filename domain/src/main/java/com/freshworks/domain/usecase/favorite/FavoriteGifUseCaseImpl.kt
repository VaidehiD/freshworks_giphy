package com.freshworks.domain.usecase.favorite

import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.domain.repository.GifRepository

class FavoriteGifUseCaseImpl(private val gifRepository: GifRepository): FavoriteGifUseCase {

    override suspend fun save(gif: GifsResponseModel) {
        gifRepository.setFavoriteGif(gif)
    }

    override suspend fun remove(gif: GifsResponseModel) {
        gifRepository.removeFavoriteGif(gif)
    }

    override suspend fun getAllFavoriteGifs() = gifRepository.getFavoriteGifs()
}