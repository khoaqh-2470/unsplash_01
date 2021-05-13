package com.sun.unsplash_01.module

import com.sun.unsplash_01.ui.collection.CollectionViewModel
import com.sun.unsplash_01.ui.photo_collection.PhotoCollectionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { CollectionViewModel(get()) }
    viewModel { PhotoCollectionViewModel(get()) }
}
