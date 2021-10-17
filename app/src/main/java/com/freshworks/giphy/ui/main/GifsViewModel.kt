package com.freshworks.giphy.ui.main

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel
import com.freshworks.domain.usecase.favorite.FavoriteGifUseCase
import com.freshworks.domain.usecase.trending.GetTrendingGifsUseCase
import com.freshworks.giphy.R
import com.freshworks.giphy.ui.main.list.GifsData
import com.freshworks.giphy.ui.main.trending.TrendingGifsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GifsViewModel(
    open val useCase: GetTrendingGifsUseCase,
    open val favoriteUseCase: FavoriteGifUseCase,
    open val data: TrendingGifsData
): ViewModel() {

    fun initialize() {
        getGifs()
    }

    fun bindData(binding: ViewDataBinding, dataBR: Int) {
        binding.setVariable(dataBR, data)
    }

    val onFavoriteClickListener: (position: Int, gifData : GifsData) -> Unit = { position, gifData ->
        viewModelScope.launch {
            gifData.apply {
                if (isFavorite.value == true) {
                    favoriteUseCase.remove(mapGifData(gifData))
                    isFavorite.value = false
                } else {
                    favoriteUseCase.save(mapGifData(gifData))
                    isFavorite.value = true
                }
            }
        }
    }


    fun mapResponseData(trendingGifs: TrendingGifsInfoResponseModel) =
        trendingGifs.gifsResponse.map {
            with(it) {
                GifsData(
                    MutableLiveData(id),
                    MutableLiveData(title),
                    MutableLiveData(webpUrl),
                    MutableLiveData(isFavorite)
                )
            }
        }.toMutableList()

    fun mapGifData(gifData: GifsData) =
        with(gifData) {
            GifsResponseModel(
                id = id.value.orEmpty(),
                title = title.value.orEmpty(),
                webpUrl = webpUrl.value.orEmpty(),
                isFavorite = isFavorite.value ?: false
            )
        }

    fun getGifs() {
        viewModelScope.launch {
            data.showLoading()
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val response = useCase.call(GetTrendingGifsUseCase.RequestParams())
                    handleSuccess(response)
                }
            }
            catch (e: Exception) {
                println("Error : ${e.message}")
            }
        }
    }

    fun handleSuccess(response: TrendingGifsInfoResponseModel) {
        if (response.gifsResponse.isEmpty()) {
            data.showEmpty()
        } else {
            data.success(mapResponseData(response))
        }
    }

    fun update(position: Int) {
        val originalGif = data.listItems.value?.get(position)
        val isFavoriteGif = originalGif?.isFavorite?.value
        originalGif?.isFavorite = MutableLiveData(isFavoriteGif?.not())
        val updatedGif = GifsData(
            id = MutableLiveData(originalGif?.id?.value.orEmpty()),
            title = MutableLiveData(originalGif?.title?.value.orEmpty()),
            webpUrl = MutableLiveData(originalGif?.webpUrl?.value.orEmpty()),
            isFavorite = MutableLiveData(isFavoriteGif?.not()),
            favoriteIcon = MutableLiveData(getDrawableRes(isFavoriteGif?.not() ?: false))
        )
        data.listItems.value?.removeAt(position)
        data.listItems.value?.add(
            position,
            updatedGif
        )
    }

    fun getDrawableRes(isFavorite: Boolean) =
        if (isFavorite) {
            R.drawable.favorite
        } else {
            R.drawable.favorite_border
        }

}