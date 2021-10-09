package com.freshworks.giphy

import android.app.Application
import com.freshworks.giphy.di.dataModule
import com.freshworks.giphy.di.domainModule
import com.freshworks.giphy.di.networkModule
import com.freshworks.giphy.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class GiphyApplication: Application() {

    var modules = module {
        getModulesList()
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GiphyApplication)
            modules(getModulesList())
        }
    }

    private fun getModulesList() =
        listOf(dataModule) + listOf(domainModule) + listOf(presentationModule) + listOf(networkModule)
}