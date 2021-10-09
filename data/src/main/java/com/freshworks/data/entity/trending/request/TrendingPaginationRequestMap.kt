package com.freshworks.data.entity.trending.request

class TrendingPaginationRequestMap: HashMap<String, String>() {

    fun requestParams(request: TrendingPaginationRequest? = null):
            TrendingPaginationRequestMap {
        request?.limit?.let { this.put("limit", it.toString()) }
        request?.rating?.let { this.put("rating", it) }
        return this
    }
}