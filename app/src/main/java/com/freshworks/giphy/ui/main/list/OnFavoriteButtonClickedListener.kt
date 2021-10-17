package com.freshworks.giphy.ui.main.list

import com.freshworks.domain.model.gifs.GifsResponseModel

interface OnFavoriteButtonClickedListener {
    fun onClick(gifResponseModel: GifsResponseModel)
}