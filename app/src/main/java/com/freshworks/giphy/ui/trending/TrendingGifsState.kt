package com.freshworks.giphy.ui.trending

sealed class TrendingGifsState {

    object Success
    object Error
    object Empty
}
