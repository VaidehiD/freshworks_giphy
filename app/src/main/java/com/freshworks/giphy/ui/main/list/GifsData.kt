package com.freshworks.giphy.ui.main.list

import androidx.lifecycle.MutableLiveData
import com.freshworks.giphy.R

data class GifsData(
    val id: MutableLiveData<String> = MutableLiveData(""),
    val title: MutableLiveData<String> = MutableLiveData(""),
    val webpUrl: MutableLiveData<String> = MutableLiveData(""),
    var isFavorite: MutableLiveData<Boolean> = MutableLiveData(false),
    var favoriteIcon: MutableLiveData<Int> = MutableLiveData(R.drawable.favorite_border)
)
