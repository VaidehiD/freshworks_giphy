package com.freshworks.giphy.ui.main.trending

sealed class TrendingGifsState {

    object Success
    object Error
    object Empty
}
