package com.sun.unsplash_01.app

import android.app.Application
import com.sun.unsplash_01.module.apiModule
import com.sun.unsplash_01.module.repositoryModule
import com.sun.unsplash_01.module.retrofitModule
import com.sun.unsplash_01.module.viewModelModule
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                apiModule,
                retrofitModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}
