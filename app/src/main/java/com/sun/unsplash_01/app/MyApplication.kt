package com.sun.unsplash_01.app

import android.app.Application
import com.sun.unsplash_01.module.*
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                apiModule,
                storageModule,
                retrofitModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}
