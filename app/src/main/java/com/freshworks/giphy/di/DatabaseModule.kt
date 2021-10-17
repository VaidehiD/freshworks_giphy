package com.freshworks.giphy.di

import android.content.Context
import androidx.room.Room
import com.freshworks.data.source.local.GifDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun getDatabase(context: Context) =
        Room.databaseBuilder(context, GifDatabase::class.java, "gifs")
            .fallbackToDestructiveMigration().build()

    fun getFavoriteDao(database: GifDatabase) = database.favoriteGifDao()

    single {
        getDatabase(context = androidApplication())
    }
    single {
        getFavoriteDao(database = get())
    }
}