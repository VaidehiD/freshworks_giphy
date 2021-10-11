package com.freshworks.giphy.ui.trending

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freshworks.domain.model.gifs.GifsResponseModel
import com.freshworks.domain.model.gifs.TrendingGifsInfoResponseModel
import com.freshworks.domain.usecase.GetTrendingGifs
import com.freshworks.giphy.ui.trending.list.OnFavoriteButtonClickedListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class TrendingGifsViewModel(
    val useCase: GetTrendingGifs,
    val data: TrendingGifsData): ViewModel(), KoinComponent {

    fun initialize() {
        getTrendingGifs()
    }

    fun bindData(binding: ViewDataBinding, dataBR: Int) {
        binding.setVariable(dataBR, data)
    }

    fun getTrendingGifs() {
        viewModelScope.launch {
            data.showLoading()
            try {
                CoroutineScope(Dispatchers.IO).launch {
                    val response = useCase.call(GetTrendingGifs.RequestParams())
                    handleSuccess(response)
                }
            }
            catch (e: Exception) {
                println("Error : ${e.message}")
            }
            println("Inside viewModel scope")
        }
    }

    fun handleSuccess(response: TrendingGifsInfoResponseModel) {
        response.gifsResponse.map {
            println("ID = ${it.id} URL = ${it.url}")
        }
        data.success(response)
        if (response.gifsResponse.isEmpty()) {
            data.showEmpty()
        } else {
            data.success(response)
        }
    }

    val favouriteButtonClicked = object : OnFavoriteButtonClickedListener {
        override fun onClick(gifResponseModel: GifsResponseModel) {
            println("Selected id ${gifResponseModel.id}")
        }
    }
}