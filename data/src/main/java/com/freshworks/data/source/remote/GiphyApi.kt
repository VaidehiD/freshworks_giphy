package com.freshworks.data.source.remote

import com.freshworks.data.entity.trending.request.TrendingPaginationRequestMap
import com.freshworks.data.entity.trending.response.TrendingGifsInfoResponseEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface GiphyApi {

    @GET("trending")
    suspend fun getTrendingGifs(
        @QueryMap paginationRequestParams: TrendingPaginationRequestMap
    ): Response<TrendingGifsInfoResponseEntity>

}