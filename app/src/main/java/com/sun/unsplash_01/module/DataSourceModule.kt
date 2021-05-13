package com.sun.unsplash_01.module

import com.sun.unsplash_01.data.source.PhotoDataSource
import com.sun.unsplash_01.data.source.remote.PhotoRemoteDataSource
import org.koin.dsl.bind
import org.koin.dsl.module

val dataSourceModule = module {

    single { PhotoRemoteDataSource(get()) } bind (PhotoDataSource.Remote::class)
}
