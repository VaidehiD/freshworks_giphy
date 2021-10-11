package com.freshworks.giphy.ui.trending.list

import com.freshworks.domain.model.gifs.GifsResponseModel

interface OnFavoriteButtonClickedListener {
    fun onClick(gifResponseModel: GifsResponseModel)
}