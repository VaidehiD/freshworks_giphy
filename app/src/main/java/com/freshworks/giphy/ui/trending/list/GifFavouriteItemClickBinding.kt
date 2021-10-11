package com.freshworks.giphy.ui.trending.list

import com.freshworks.domain.model.gifs.GifsResponseModel

class GifFavouriteItemClickBinding(
    listener: OnFavoriteButtonClickedListener? = null,
    responseData: GifsResponseModel? = null
) {
    val onFavoriteButtonClicked: () -> Unit = {
        responseData?.let {
            listener?.onClick(it)
        }
    }
}