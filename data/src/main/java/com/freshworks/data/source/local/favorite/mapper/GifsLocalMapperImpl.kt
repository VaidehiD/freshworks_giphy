package com.freshworks.data.source.local.favorite.mapper

import androidx.lifecycle.MutableLiveData
import com.freshworks.data.entity.favorite.FavoriteGif
import com.freshworks.domain.model.gifs.GifsResponseModel

class GifsLocalMapperImpl: GifsLocalMapper {

    override fun toModel(favoriteGif: FavoriteGif) =
        with(favoriteGif) {
            GifsResponseModel(gifId, gifTitle, gifUrl, true)
        }

    override fun toEntity(gif: GifsResponseModel) =
        with(gif) {
            FavoriteGif(gifId = id, gifTitle = title, gifUrl = webpUrl)
        }
}