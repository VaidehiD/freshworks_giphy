package com.freshworks.data.entity.trending.request

data class TrendingPaginationRequest(
    val limit: Int? = null,
    val rating: String? = null
)