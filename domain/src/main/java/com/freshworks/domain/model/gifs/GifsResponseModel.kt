package com.freshworks.domain.model.gifs

data class GifsResponseModel(
    val id: String = "",
    val title: String = "",
    val webpUrl: String = "",
    var isFavorite: Boolean = false
)
