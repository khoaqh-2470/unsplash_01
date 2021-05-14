package com.sun.unsplash_01.module

import com.sun.unsplash_01.data.repository.PhotoRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { PhotoRepository(get()) }
}
