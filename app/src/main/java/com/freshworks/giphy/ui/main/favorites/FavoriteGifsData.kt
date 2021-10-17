package com.freshworks.giphy.ui.main.favorites

import androidx.lifecycle.MutableLiveData
import com.freshworks.giphy.ui.main.list.GifsData

data class FavoriteGifsData(
    val isEmpty: MutableLiveData<Boolean> = MutableLiveData(false),
    val isSearching: MutableLiveData<Boolean> = MutableLiveData(false),
    val searchString: MutableLiveData<String> = MutableLiveData(""),
    val showError: MutableLiveData<Boolean> = MutableLiveData(false),
    val showList: MutableLiveData<Boolean> = MutableLiveData(false),
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(false),
    val emptyText: MutableLiveData<String> = MutableLiveData(""),
    val listItems: MutableLiveData<MutableList<GifsData>> = MutableLiveData(mutableListOf())
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

    internal fun success(gifDataList: MutableList<GifsData>) {
        isLoading.postValue(false)
        isEmpty.postValue(false)
        showList.postValue(true)
        showError.postValue(false)

        listItems.postValue(gifDataList)
    }
}