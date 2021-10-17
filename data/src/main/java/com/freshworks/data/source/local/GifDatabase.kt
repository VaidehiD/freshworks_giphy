package com.freshworks.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.freshworks.data.entity.favorite.FavoriteGif
import com.freshworks.data.source.local.favorite.FavoriteGifDao

@Database(entities = [FavoriteGif::class], version = 1)
abstract class GifDatabase: RoomDatabase() {
    abstract fun favoriteGifDao(): FavoriteGifDao
}