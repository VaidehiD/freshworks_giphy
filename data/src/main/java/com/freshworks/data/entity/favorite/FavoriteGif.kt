package com.freshworks.data.entity.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_gif")
data class FavoriteGif(
     @PrimaryKey val gifId: String,
     @ColumnInfo(name = "gif_url") val gifUrl: String,
     @ColumnInfo(name = "gif_title") val gifTitle: String
)