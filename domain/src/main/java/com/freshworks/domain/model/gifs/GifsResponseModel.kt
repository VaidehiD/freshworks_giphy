package com.freshworks.domain.model.gifs

data class GifsResponseModel(
    val id: String = "",
    val url: String = "",
    val title: String = "",
    val images: GifsImageModel = GifsImageModel()
)
