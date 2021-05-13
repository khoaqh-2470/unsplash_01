package com.sun.unsplash_01.module

import com.sun.unsplash_01.data.repository.CollectionRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { CollectionRepository(get()) }
}
