package com.freshworks.data.source.local.favorite

import com.freshworks.data.source.local.favorite.mapper.GifsLocalMapper
import com.freshworks.domain.model.gifs.GifsResponseModel

class GifsLocalSourceImpl(
    private val dao: FavoriteGifDao,
    private val localMapper: GifsLocalMapper
) : GifsLocalSource {

    override suspend fun getFavoriteGifs(): List<GifsResponseModel> {
        return dao.getAllFavoriteGifs().map {
            localMapper.toModel(it)
        }
    }

    override suspend fun setFavoriteGif(gif: GifsResponseModel) {
        dao.insert(localMapper.toEntity(gif))
    }

    override suspend fun removeFavoriteGif(gif: GifsResponseModel) {
        dao.delete(localMapper.toEntity(gif))
    }
}