package com.freshworks.data.source.local.favorite

import androidx.room.*
import com.freshworks.data.entity.favorite.FavoriteGif

@Dao
interface FavoriteGifDao {

    @Query("SELECT * FROM favorite_gif")
    suspend fun getAllFavoriteGifs(): List<FavoriteGif>

    @Insert
    suspend fun insert(favoriteGif: FavoriteGif)

    @Delete
    suspend fun delete(favoriteGif: FavoriteGif)
}