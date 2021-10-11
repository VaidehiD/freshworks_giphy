package com.freshworks.giphy.ui.trending

import androidx.lifecycle.MutableLiveData
import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel

data class TrendingGifsData(
    val isEmpty: MutableLiveData<Boolean> = MutableLiveData(false),
    val isSearching: MutableLiveData<Boolean> = MutableLiveData(false),
    val searchString: MutableLiveData<String> = MutableLiveData(""),
    val showError: MutableLiveData<Boolean> = MutableLiveData(false),
    val showList: MutableLiveData<Boolean> = MutableLiveData(false),
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false),
    val emptyText: MutableLiveData<String> = MutableLiveData(""),
    val listItems: MutableLiveData<List<GifsResponseModel>> = MutableLiveData(listOf())
) {

    internal fun showEmpty() {
        isEmpty.postValue(true)
        showList.postValue(false)
        showError.postValue(false)
        isLoading.postValue(false)
    }

    internal fun showLoading() {
        isEmpty.postValue(false)
        showList.postValue(false)
        showError.postValue(false)
        isLoading.postValue(true)
    }

    internal fun success(response: TrendingGifsInfoResponseModel) {
        isLoading.postValue(false)
        isEmpty.postValue(false)
        showList.postValue(true)
        showError.postValue(false)

        listItems.postValue(response.gifsResponse)
    }
}