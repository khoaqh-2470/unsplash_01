package com.sun.unsplash_01.app

import android.app.Application
import com.sun.unsplash_01.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                apiModule,
                storageModule,
                retrofitModule,
                viewModelModule,
                repositoryModule,
                dataSourceModule
            )
        }
    }
}
